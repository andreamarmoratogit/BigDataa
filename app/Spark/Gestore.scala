package Spark

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.evaluation.{ClusteringEvaluator}
import org.apache.spark.ml.feature.{Imputer,  MinMaxScaler, VectorAssembler}
import org.apache.spark.ml.regression.{ GBTRegressionModel, GBTRegressor, GeneralizedLinearRegression, LinearRegression, RandomForestRegressor}

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SparkSession, functions}
case class Minmax(name:String,lat:Double,lon:Double){}

class Gestore (sc : SparkContext, session: SparkSession,configurazione: SparkConf ) {
  import session.implicits._

  //cerca le stazioni che hanno registrato la misura massima nel mese fissato
  def maxM(mese: String,misura:String) : Array[Row] ={

    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD2013"+mese+"\\2013"+mese+"daily.txt")
    val colonnaT = dataframe.withColumn("Tmax", col(misura).cast("int")).agg(functions.max(misura) ).first().get(0)
    val colonna2T= dataframe.filter(dataframe(misura)===colonnaT).select("WBAN","Date",misura)
    colonna2T.collect()
  }
  def minM(mese: String,misura:String) : DataFrame ={

    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD2013"+mese+"\\2013"+mese+"daily.txt")
    val stations = session.read.option("header","true").option("delimiter","|").csv("Dataset\\QCLCD2013"+mese+"\\2013"+mese+"station.txt")
      .select("WBAN","latitude","longitude").withColumnRenamed("WBAN","name")
      .withColumnRenamed("latitude","lat").withColumnRenamed("longitude","lon")
    val colonnaT = dataframe.withColumn("Tmin", col(misura).cast("int")).agg(functions.min(misura) ).first().get(0)
    val colonna2T= dataframe.filter(dataframe(misura)===colonnaT).select("WBAN","Date",misura)
    val c=colonna2T.join(stations,colonna2T("WBAN")===stations("name"),"left").drop("WBAN")
    c.withColumnRenamed(misura,"misura")
  }

  // periodo temporale di una determinata misura di una stazione
  // ES: in 3 giorni ---> 70% vento, 10% pioggia, 20% nuvole
  // DAILY :codesum, snowfall, precipTotal,
  // FZRA,SN,TS,RA
  def percentuale(stazione:String, inizio:DataF, fine:DataF) : DataFrame = {
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
    val q=daily5.map(row=>{(row(0).toString,(row(1).toString.toInt*100)/giorniTot)}).withColumnRenamed("_1","name").withColumnRenamed("_2","y")
     // .withColumn("id",lit(0)).groupBy("id").pivot("_1").sum("_2").drop("id")
    q
  }

  def getStations():DataFrame={
    val d13=session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\stationsMerge.txt")
    d13.select("name" ,"lat", "lon").distinct()
  }

  def time_series(stazione:String,misura:String):DataFrame={
    val d=session.read.option("header", "true").option("inferSchema", "true").option("delimiter",";").
      csv("Dataset\\dailyMerge.csv").select("WBAN","YearMonthDay",misura).filter(col("WBAN")===stazione).withColumnRenamed("WBAN","name")
      .withColumnRenamed("YearMonthDay","x").withColumnRenamed(misura,"y")
    val d2= d.show()
    d
  }

//AvgMaxTemp AvgMinTemp AvgTemp TotalMonthlyPrecip ResultantWindSpeed AvgWindSpeed
  def stagioni(stazione:String,misura:String):DataFrame= {
    val monthly=session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\Monthly")
      .groupBy("WBAN").agg(avg(misura).alias(misura))
    monthly.cache()
    val m=monthly.agg(max(misura),min(misura))
    monthly.join(m, monthly("WBAN") === stazione)
  }

  def getStationsM():DataFrame={
    session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\Stations")
  }

  def getTempForMap():DataFrame={
    val d2=session.read.option("header", "true").option("delimiter",",").csv("Dataset\\Stations.txt").select("name","State")
    val d=session.read.option("header", "true").option("delimiter",";").csv("Dataset\\MonthlyMerge2.csv").select("WBAN","YearMonth","AvgTemp")
      .filter(col("AvgTemp")=!="M").withColumn("AvgTemp", col("AvgTemp").cast(DoubleType) alias("AvgTemp")).groupBy("WBAN" ).agg(avg("AvgTemp") alias("media annuale")).sort("WBAN")
    // per ogni stazione prendo la sua media annuale

    // faccio la join con tutte le presenti; raggruppo per stato facendo la media sulle temeprature medie annuali di ogni stazione in uno stato
    val d3 = d.join(d2).where(d("WBAN")===d2("name")).drop(d("WBAN")).withColumn("State",concat(lit("us-"),col("State"))).groupBy("State").agg(avg("media annuale") alias("media"))
    val d4 = d3.withColumn("State",lower(col("State")))
    d4
  }

  def kMeans():String={
    val d=session.read.option("header", "true").option("inferSchema", "true").csv("Dataset\\KmeansScaledFeatures")

    //per rimpiazzare i valori nulli con la media riferita alla data
   /* val stations = d.select("YearMonth").distinct.collect.flatMap(_.toSeq)
    val byStationArray = stations.map(state => d.where($"YearMonth" <=> state))
    val imputer = new Imputer()
      .setInputCols(d.columns)
      .setOutputCols(d.columns.map(c => s"${c}"))
      .setStrategy("mean")

    val d2=byStationArray.map(x=>imputer.fit(x).transform(x))
    val d3=d2.reduce(_ union _).groupBy("WBAN").agg(avg("AvgMaxTemp") as ("AvgMaxTemp") ,avg("AvgMinTemp")as("AvgMinTemp"),
      avg("AvgTemp")as("AvgTemp"),avg("MeanStationPressure")as("MeanStationPressure"),avg("AvgWindSpeed")as("AvgWindSpeed"),
      avg("TotalMonthlyPrecip")as("TotalMonthlyPrecip")).sort("WBAN")
    d3.cache()*/

    val v=new VectorAssembler().setInputCols(Array("AvgMinTemp","AvgMaxTemp","AvgTemp","TotalMonthlyPrecip")).setOutputCol("features")
    val features= v.transform(d).select("WBAN","features")
    val maScaler =new MinMaxScaler().setOutputCol("ScaledFeatures").setInputCol("features").setMin(0).setMax(10)
    val scaledModel=maScaler.fit(features)
    val scaledFeat=scaledModel.transform(features)

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
    val kmeans= new KMeans().setK(6).setInitSteps(4).setTol(0.001).setFeaturesCol("ScaledFeatures")
    val model = kmeans.fit(scaledFeat)
    val pred=model.transform(scaledFeat)
    pred.cache()
    val eval=new ClusteringEvaluator()
    println(eval.evaluate(pred))
    val st= session.read.option("header", "true").option("inferSchema", "true")
      .csv("C:\\Users\\marmo\\BigDataa\\Dataset\\stationsMerge.txt").select("name" ,"lat", "lon").distinct()
    val pred2=pred.select("WBAN","prediction").join(st,pred("WBAN")===st("name"),"left").select("name","lat","lon","prediction")
      .withColumnRenamed("prediction", "cluster")
    pred2.toJSON.collectAsList().toString
  }

  def predictor(stazione:String,dataIn:DataF, dataFin:DataF):DataFrame ={

    val x=predictor2("Tmax").join(predictor2("Tmin"), Seq("WBAN","YearMonthDay") , "left")//.sort("WBAN","YearMonthDay")
      .filter(col("YearMonthDay")<=dataFin.year+dataFin.month+dataFin.day)
      .filter(col("YearMonthDay")>=dataIn.year+dataIn.month+dataIn.day)
      .filter(col("WBAN")===stazione).drop("WBAN").sort("YearMonthDay")
      .withColumn("YearMonthDay", to_date(col("YearMonthDay").cast("string"), "yyyyMMdd"))
      .withColumn("Y",substring(col("YearMonthDay"),1,4).cast(IntegerType))
      .withColumn("M",substring(col("YearMonthDay"),6,2).cast(IntegerType))
      .withColumn("D",substring(col("YearMonthDay"),9,2).cast(IntegerType))
    x.show()
    x.drop("YearMonthDay")
  }//.withColumn("YearMonthDay",regexp_replace(col("YearMonthDay"),"-",";"))


  def predictor2(misura:String): DataFrame ={
    val v=new VectorAssembler().setInputCols(Array("WBAN","MonthDay","Tmax","Tmin","Tavg","PrecipTotal","AvgSpeed")).setOutputCol("features")
/*
    val d13f= session.read
     .option("header", "true").option("inferSchema", "true")
     .csv("C:\\Users\\marmo\\BigDataa\\Dataset\\predictor").withColumn("MonthDay",col("YearMonthDay")-20130000)
    val features= v.transform(d13f).select("WBAN","features",misura+"Pred")
    val lr=new GBTRegressor().setLabelCol(misura+"Pred").setMaxIter(30).setMaxDepth(5).setMaxBins(40).setLossType("squared")
      .setMinInstancesPerNode(3)

    //val paramGrid= new ParamGridBuilder().addGrid(lr.maxBins,Array(40,30)).addGrid(lr.minInfoGain,Array(0.1,0.01,0))
     // .addGrid(lr.minInstancesPerNode,Array(1,2,3)).addGrid(lr.lossType,Array("absolute","squared")).build()
    val eval= new RegressionEvaluator().setLabelCol(misura+"Pred")
    //val cv= new CrossValidator().setEstimator(lr).setEvaluator(eval).setEstimatorParamMaps(paramGrid).setNumFolds(4)

    val model=lr.fit(features)
    model.write.overwrite().save("Dataset\\Regression"+misura)

*/
    val model = GBTRegressionModel.load("Dataset\\Regression"+misura)
    val d14= session.read
      .option("header", "true").option("inferSchema", "true")
      .csv("C:\\Users\\marmo\\BigDataa\\Dataset\\Dataset2014").drop("CodeSum").withColumn("MonthDay",col("YearMonthDay")-20140000)

    val features2=v.transform(d14)
    val ret =model.transform(features2).withColumnRenamed("prediction",misura+"P").select("WBAN","YearMonthDay",misura+"Pred",misura+"P")
    //println(eval.evaluate(ret))
    //println(model.bestModel.extractParamMap())
    //println(model.bestModel)
    ret

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
    d13f.coalesce(1).write.format("com.databricks.spark.csv")
      .option("header", "true")
      .save("C:\\Users\\marmo\\BigDataa\\Dataset\\predictor")
    d13f
  }

  def preprocessing(dataFrame: DataFrame,nome:String): DataFrame ={
    val stations = dataFrame.select("YearMonth").distinct.collect.flatMap(_.toSeq)
    val byStationArray = stations.map(state => dataFrame.where($"YearMonth" <=> state))
    val imputer = new Imputer()
      .setInputCols(dataFrame.columns)
      .setOutputCols(dataFrame.columns.map(c => s"${c}"))
      .setStrategy("mean")
    val d13r=byStationArray.map(x=>imputer.fit(x).transform(x))
    val d13f=d13r.reduce(_ union _).sort("WBAN","YearMonth")
    d13f.coalesce(1).write.format("com.databricks.spark.csv")
      .option("header", "true")
      .save("C:\\Users\\marmo\\BigDataa\\Dataset\\"+nome)
    d13f
  }

  def appendCol(dataFrame: DataFrame,nome:String): DataFrame ={
    val pred=dataFrame.select("Tmax","Tmin").withColumnRenamed("Tmax","TmaxPred").withColumnRenamed("Tmin","TminPred")
    val tail=dataFrame.tail(1).head
    val d2=dataFrame.filter(row => row!=tail).withColumn("id",monotonically_increasing_id())
    d2.show()
    val head=pred.head()
    val pred2=pred.filter(row=>row!=head).withColumn("id",monotonically_increasing_id())
    pred2.show()
    val d3=d2.join(pred2,d2("id")===pred2("id"),"inner").drop("id").filter(col("YearMonthDay")=!="20141231").sort("WBAN","YearMonthDay")
    d3.show()
    d3.coalesce(1).write.format("com.databricks.spark.csv")
      .option("header", "true")
      .save("C:\\Users\\marmo\\BigDataa\\Dataset\\"+nome)
    d3
  }


}

