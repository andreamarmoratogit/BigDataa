package Spark

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.evaluation.ClusteringEvaluator
import org.apache.spark.ml.feature.{Imputer, VectorAssembler}
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, StringType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, Encoders, Row, SaveMode, SparkSession, functions}

class Gestore (sc : SparkContext, session: SparkSession,configurazione: SparkConf ) {

  //cerca le stazioni che hanno registrato la misura massima nel mese fissato
  def maxM(mese: String,misura:String) : DataFrame ={

    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    val colonnaT = dataframe.withColumn("Tmax", col(misura).cast("int")).agg(functions.max(misura) ).first().get(0)
    val colonna2T= dataframe.filter(dataframe(misura)===colonnaT).select("WBAN","Date",misura)
    colonna2T.show(10)
    return colonna2T
  }

  def minM(mese: String,misura:String) : DataFrame ={

    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    val colonnaT = dataframe.withColumn("Tmin", col(misura).cast("int")).agg(functions.min(misura) ).first().get(0)
    val colonna2T= dataframe.filter(dataframe(misura)===colonnaT).select("WBAN","Date",misura)
    colonna2T.show(10)
    colonna2T
  }

  // periodo temporale di una determinata misura di una stazione
  // ES: in 3 giorni ---> 70% vento, 10% pioggia, 20% nuvole
  // DAILY :codesum, snowfall, precipTotal,
  // FZRA,SN,TS,RA
  def percentuale(stazione:String, inizio:DataF, fine:DataF) : Dataset[(String,Double)] = {
    var daily =session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\QCLCD" + inizio.year+inizio.month + "\\" + inizio.year+inizio.month+ "daily.txt").select("WBAN", "Date", "CodeSum", "PrecipTotal", "SnowFall")
    var hourly = session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\QCLCD" + inizio.year+inizio.month + "\\" + inizio.year+inizio.month  + "hourly.txt").select("WBAN","Date","SkyCondition")
    for (i <- (inizio.year+inizio.month).toInt+1 to (fine.year+fine.month).toInt) {
      daily = daily.union(session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\QCLCD" + i + "\\" + i + "daily.txt").select("WBAN", "Date", "CodeSum", "PrecipTotal", "SnowFall"))
      hourly = hourly.union(session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\QCLCD" + i+ "\\" + i + "hourly.txt").select("WBAN","Date","SkyCondition"))
    }
    val hourly2=hourly.groupBy("WBAN","Date").agg(concat_ws(" ",collect_list("SkyCondition")) as "Condizione cielo")
    val daily2= daily.join(hourly2,Seq("WBAN","Date"))
    val daily3 = daily2.filter(daily2("WBAN")===stazione)
    import session.implicits._
    val daily4=daily3.filter(daily3("Date")>=inizio.toString ).filter(daily3("Date")<=fine.toString ).sort("WBAN","Date")
    val daily5 = daily4.map(row=> {if(row(2).toString.contains("SN")) ("Neve", 1)
    else if(row(2).toString.contains("RA")) ("Pioggia", 1)
    else if(row(2).toString.contains("TS")) ("Temporale", 1)
    else if(row(2).toString.contains("FZRA")) ("Grandine", 1)
    else if(!row(3).toString.contains("T") && !row(3).toString.equals("M") && row(3).toString.toDouble>0) ("Pioggia",1)
    else if(!row(4).toString.contains("T") && !row(4).toString.equals("M") && row(4).toString.toDouble>0) ("Neve",1)
    else {
      val v1 = row(5).toString.split(" ")
      var c = 0
      var n=0
      for(i<-v1) {
        if (i.contains("CLR"))
          c+=1
        else n+=1
      }
      if(c>n) ("Sereno",1)
      else ("Nuvoloso",1)
    }
    }).groupBy("_1").sum("_2" )
    daily5.show()
    daily5.cache
    val giorniTot=daily5.agg(sum("sum(_2)")).first().get(0).toString.toDouble
    val q=daily5.map(row=>{(row(0).toString,(row(1).toString.toInt*100)/giorniTot)})
    q
  }


  def time_series(stazione:String,misura:String):DataFrame={
    session.read.option("header", "true").option("inferSchema", "true").
      csv("Dataset\\QCLCD" + "201308" + "\\" + "201308"+ "daily.txt").select("WBAN","Date",misura).filter(col("WBAN")===stazione)

  }

//AvgMaxTemp AvgMinTemp AvgTemp TotalMonthlyPrecip ResultantWindSpeed AvgWindSpeed
  def stagioni(stazione:String,misura:String):Unit= {
    val monthly=session.read.option("header", "true").csv("Dataset\\QCLCD" + "201301" + "\\" + "201301" + "monthly.txt").union(
      session.read.option("header", "true").csv("Dataset\\QCLCD" + "201307" + "\\" + "201307" + "monthly.txt")
    ).select("WBAN", misura).filter(col(misura)=!="M").withColumn(misura, col(misura).cast(DoubleType) alias(misura))
    .groupBy("WBAN").avg(misura).sort("avg("+misura+")")
    monthly.cache()
    //println(Seq(monthly.filter(monthly("WBAN")===stazione).first(),monthly.first(),monthly.tail(1).head).toString())
    val ret= monthly.filter(monthly("WBAN")===stazione).union(session.createDataFrame(List((monthly.first(),monthly.tail(1).head),monthly.schema)))
  }

  def getTempForMap():Unit={
    session.read.option("header", "true").csv("Dataset\\QCLCD" + "201301" + "\\" + "201301" + "monthly.txt").select("WBAN","AvgTemp")
      .filter(col("AvgTemp")=!="M").withColumn("AvgTemp", col("AvgTemp").cast(DoubleType) alias("AvgTemp"))
  }
  def prova():Unit={
    import session.implicits._
    val d=session.read.option("header", "true").option("delimiter",";").option("inferSchema", "true").csv("Dataset\\MonthlyMerge.csv")
      .drop("DepartureMaxTemp","DepartureMinTemp","DeparturefromNormal","DepartureFromNormalPrecip")
    //val d2= d.withColumn("Tmax2",col("Tmax"))
    //val d2=d.map(row=>{if (row.get(2) == null) {d.filter(d("WBAN")===row(0)).agg(avg("Tmax")).first()} else row(0)
    //})
    /*val imputer = new Imputer()
      .setInputCols(d.drop("CodeSum").drop("PrecipTotal").columns)
      .setOutputCols(d.drop("CodeSum").drop("PrecipTotal").columns.map(c => s"${c}"))
      .setStrategy("mean")

    val d2 = imputer.fit(d).transform(d)*/

    val stations = d.select("YearMonth").distinct.collect.flatMap(_.toSeq)
    val byStationArray = stations.map(state => d.where($"YearMonth" <=> state))
    val imputer = new Imputer()
      .setInputCols(d.drop("DaysWithPrecip>=.01inch","DaysWithPrecip>=.10inch").columns)
      .setOutputCols(d.drop("DaysWithPrecip>=.01inch","DaysWithPrecip>=.10inch").columns.map(c => s"${c}"))
      .setStrategy("mean")

    val d2=byStationArray.map(x=>imputer.fit(x).transform(x))
    val d3=d2.reduce(_ union _).sort("WBAN","YearMonth")


    val v=new VectorAssembler().setInputCols(Array("WBAN","AvgMaxTemp","AvgMinTemp","AvgTemp","AvgWindSpeed","MeanStationPressure")).setOutputCol("features")
    /*val kmeans = new KMeans().setSeed(1L).setFeaturesCol("features")
    val paramGrid= new ParamGridBuilder().addGrid(kmeans.k,Array(5,10)).build()
    val eval=new ClusteringEvaluator().setFeaturesCol("features")
    val cv= new CrossValidator().setEstimator(kmeans).setEvaluator(eval).setEstimatorParamMaps(paramGrid).setNumFolds(3).setParallelism(2)*/
    val features= v.transform(d3)

    val kmeans= new KMeans().setK(50)
    val model = kmeans.fit(features)
    val ret =model.transform(features)
    val eval=new ClusteringEvaluator()
    println(eval.evaluate(ret))
    ret.show(200)

    /*println(model.bestModel.extractParamMap())
    println(model.bestModel)
    println(model.bestModel.getParam("k"))*/

    // Make predictions
    //val predictions = model.transform(features)
    //predictions.show(100)
    //d.show(500)*/
  }


}

