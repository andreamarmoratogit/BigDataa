package Spark

import algebra.instances.all.catsKernelStdOrderForString
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.evaluation.{ClusteringEvaluator, RegressionEvaluator}
import org.apache.spark.ml.feature.{Imputer, MaxAbsScaler, MinMaxScaler, VectorAssembler}
import org.apache.spark.ml.regression.{DecisionTreeRegressor, LinearRegression, RandomForestRegressor}
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, Encoders, Row, SaveMode, SparkSession, functions}
import spire.implicits.eqOps

import scala.Seq

class Gestore (sc : SparkContext, session: SparkSession,configurazione: SparkConf ) {
  import session.implicits._

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
    var daily =session.read.option("header", "true").option("inferSchema", "true")
      .csv("Dataset\\QCLCD" + inizio.year+inizio.month + "\\" + inizio.year+inizio.month+ "daily.txt")
      .select("WBAN", "Date", "CodeSum", "PrecipTotal", "SnowFall")
    var hourly = session.read.option("header", "true").option("inferSchema", "true")
      .csv("Dataset\\QCLCD" + inizio.year+inizio.month + "\\" + inizio.year+inizio.month  + "hourly.txt")
      .select("WBAN","Date","SkyCondition")
    for (i <- (inizio.year+inizio.month).toInt+1 to (fine.year+fine.month).toInt) {
      daily = daily.union(session.read.option("header", "true").option("inferSchema", "true")
        .csv("Dataset\\QCLCD" + i + "\\" + i + "daily.txt").select("WBAN", "Date", "CodeSum", "PrecipTotal", "SnowFall"))
      hourly = hourly.union(session.read.option("header", "true").option("inferSchema", "true")
        .csv("Dataset\\QCLCD" + i+ "\\" + i + "hourly.txt").select("WBAN","Date","SkyCondition"))
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
    val d=session.read.option("header", "true").option("delimiter",";").option("inferSchema", "true").csv("Dataset\\MonthlyMerge2.csv")

    //per rimpiazzare i valori nulli con la media riferita alla data
    val stations = d.select("YearMonth").distinct.collect.flatMap(_.toSeq)
    val byStationArray = stations.map(state => d.where($"YearMonth" <=> state))
    val imputer = new Imputer()
      .setInputCols(d.columns)
      .setOutputCols(d.columns.map(c => s"${c}"))
      .setStrategy("mean")

    val d2=byStationArray.map(x=>imputer.fit(x).transform(x))
    val d3=d2.reduce(_ union _).groupBy("WBAN").agg(avg("AvgMaxTemp") as ("AvgMaxTemp") ,avg("AvgMinTemp")as("AvgMinTemp"),
      avg("AvgTemp")as("AvgTemp"),avg("MeanStationPressure")as("MeanStationPressure"),avg("AvgWindSpeed")as("AvgWindSpeed"),
      avg("TotalMonthlyPrecip")as("TotalMonthlyPrecip")).sort("WBAN")
    d3.cache()
    val v=new VectorAssembler().setInputCols(Array("AvgMinTemp","AvgMaxTemp","AvgTemp","TotalMonthlyPrecip")).setOutputCol("features")
    val features= v.transform(d3).select("WBAN","features")
    val maScaler =new MinMaxScaler().setOutputCol("ScaledFeatures").setInputCol("features").setMin(0).setMax(10)
    val scaledModel=maScaler.fit(features)
    val scaledFeat=scaledModel.transform(features)
    scaledFeat.show()
    /*val kmeans = new KMeans().setSeed(1L).setFeaturesCol("ScaledFeatures")
    val paramGrid= new ParamGridBuilder().addGrid(kmeans.k,Array(8,6)).build()
    val eval=new ClusteringEvaluator().setFeaturesCol("ScaledFeatures")
    val cv= new CrossValidator().setEstimator(kmeans).setEvaluator(eval).setEstimatorParamMaps(paramGrid).setNumFolds(4)

    val model=cv.fit(scaledFeat)
    val ret =model.transform(scaledFeat)
    ret.show()
    println(eval.evaluate(ret))
    println(model.bestModel.extractParamMap())
    println(model.bestModel)
    println(model.bestModel.getParam("k"))*/

    val kmeans= new KMeans().setK(6).setFeaturesCol("ScaledFeatures")
    val model = kmeans.fit(scaledFeat)
    val ret=model.transform(scaledFeat)
    val eval=new ClusteringEvaluator()
    println(eval.evaluate(ret))
    //val predictions = model.transform(features)
    //predictions.show(100)
    //d.show(500)*/
  }

  def predictor(dataIn:DataF, dataFin:DataF): Unit ={
    val d = predictor2("Tmax").join(predictor2("Tmin"), Seq("WBAN","YearMonthDay") , "left_semi")
      .filter(col("YearMonthDay")<=dataFin.year+dataFin.month+dataFin.day).filter(col("YearMonthDay")>=dataIn.year+dataIn.month+dataIn.day)
  }

  def predictor2(misura:String): DataFrame ={
    val d13f= session.read
     .option("header", "true").option("inferSchema", "true")
     .csv("C:\\Users\\marmo\\BigDataa\\Dataset\\predictor")
    val d13c=d13f.withColumn("WBAN",col("WBAN").cast("int")).withColumn("YearMonthDay",col("YearMonthDay").cast("int"))
      .withColumn("Tmax",col("Tmax").cast("double")).withColumn("Tmin",col("Tmin").cast("double"))
      .withColumn("Tavg",col("Tavg").cast("double")).withColumn("PrecipTotal",col("PrecipTotal").cast("double"))
      .withColumn("AvgSpeed",col("AvgSpeed").cast("double")).withColumn("TmaxPred",col("TmaxPred").cast("double"))
      .withColumn("TminPred",col("TminPred").cast("double"))
    val v=new VectorAssembler().setInputCols(Array("WBAN","YearMonthDay","Tmax","Tmin","Tavg","PrecipTotal","AvgSpeed")).setOutputCol("features")
    val features= v.transform(d13c).select("WBAN","features",misura+"Pred")
    features.cache()
    val lr=new DecisionTreeRegressor().setLabelCol(misura+"Pred")

    val paramGrid= new ParamGridBuilder().addGrid(lr.maxDepth,Array(14,22)).addGrid(lr.maxBins,Array(35)).addGrid(lr.minInfoGain,Array(0.1)).build()
    val eval= new RegressionEvaluator().setLabelCol(misura+"Pred")
    val cv= new CrossValidator().setEstimator(lr).setEvaluator(eval).setEstimatorParamMaps(paramGrid).setNumFolds(4)

    val model=cv.fit(features)
    val d14= session.read
      .option("header", "true").option("inferSchema", "true")
      .csv("C:\\Users\\marmo\\BigDataa\\Dataset\\Dataset2014").drop("CodeSum")

    val features2=v.transform(d14)
    val ret =model.transform(features2)
    ret.show()
    println(eval.evaluate(ret))
    println(model.bestModel.extractParamMap())
    println(model.bestModel)
    ret.select("prediction")
    /*val tree=new LinearRegression().setLabelCol("TmaxPred").setSolver("normal")
    val model=tree.fit(features)
    val prediction=model.transform(features)
    prediction.show()
    val eval= new RegressionEvaluator().setLabelCol("TmaxPred")
    println(eval.evaluate(prediction))*/
    null
  }

  def datasetPred(): DataFrame ={
    val d13=session.read.option("header", "true").option("delimiter",";").option("inferSchema", "true").csv("Dataset\\dailyMerge.csv").drop("CodeSum")
    val d14=session.read.option("header", "true").option("delimiter",";").option("inferSchema", "true").csv("Dataset\\daily2014.csv").drop("CodeSum")
    val d13_2=d13.join(d14, Seq("WBAN") , "left_semi")
    val pred=d13_2.select("Tmax","Tmin").withColumnRenamed("Tmax","TmaxPred").withColumnRenamed("Tmin","TminPred")
    val tail=d13_2.tail(1).head
    val d13_3=d13.filter(row => row!=tail).withColumn("id",monotonically_increasing_id())
    val head=pred.head()
    val pred2=pred.filter(row=>row!=head).withColumn("id",monotonically_increasing_id())
    val d13_4=d13_3.join(pred2,d13_3("id")===pred2("id"),"inner").drop("id").filter(col("YearMonthDay")=!="20131231")
    d13_4.cache()
    val stations = d13_4.select("YearMonthDay").distinct.collect.flatMap(_.toSeq)
    val byStationArray = stations.map(state => d13_4.where($"YearMonthDay" <=> state))
    val imputer = new Imputer()
      .setInputCols(d13_4.columns)
      .setOutputCols(d13_4.columns.map(c => s"${c}"))
      .setStrategy("mean")
    val d13r=byStationArray.map(x=>imputer.fit(x).transform(x))
    val d13f=d13r.reduce(_ union _).sort("WBAN","YearMonthDay")
    d13f.cache()
    val save= d13f.coalesce(1).write.format("com.databricks.spark.csv")
      .option("header", "true")
      .save("C:\\Users\\marmo\\BigDataa\\Dataset\\predictor")
    d13f
  }

  def preprocessing(dataFrame: DataFrame,nome:String): DataFrame ={
    val stations = dataFrame.select("YearMonthDay").distinct.collect.flatMap(_.toSeq)
    val byStationArray = stations.map(state => dataFrame.where($"YearMonthDay" <=> state))
    val imputer = new Imputer()
      .setInputCols(dataFrame.columns)
      .setOutputCols(dataFrame.columns.map(c => s"${c}"))
      .setStrategy("mean")
    val d13r=byStationArray.map(x=>imputer.fit(x).transform(x))
    val d13f=d13r.reduce(_ union _).sort("WBAN","YearMonthDay")
    d13f.cache()
    val save= d13f.coalesce(1).write.format("com.databricks.spark.csv")
      .option("header", "true")
      .save("C:\\Users\\marmo\\BigDataa\\Dataset\\"+nome)
    d13f
  }

  def appendCol(dataFrame: DataFrame,nome:String): DataFrame ={
    val pred=dataFrame.select("Tmax","Tmin").withColumnRenamed("Tmax","TmaxPred").withColumnRenamed("Tmin","TminPred")
    val tail=dataFrame.tail(1).head
    val d2=dataFrame.filter(row => row!=tail).withColumn("id",monotonically_increasing_id())
    val head=pred.head()
    val pred2=pred.filter(row=>row!=head).withColumn("id",monotonically_increasing_id())
    val d3=d2.join(pred2,d2("id")===pred2("id"),"inner").drop("id").filter(col("YearMonthDay")=!="20141231")
    d3.coalesce(1).write.format("com.databricks.spark.csv")
      .option("header", "true")
      .save("C:\\Users\\marmo\\BigDataa\\Dataset\\"+nome)
    d3
  }


}

