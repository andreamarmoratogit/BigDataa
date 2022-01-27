package spark

import org.apache.spark.sql.catalyst.encoders.RowEncoder
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Encoders, Row, SQLContext, SparkSession, functions}
import org.apache.spark.sql.functions.{col, collect_list, concat_ws, max, min}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

import java.lang.Integer
import java.lang.Integer.parseInt





class Gestore (sc : SparkContext, session: SparkSession,configurazione: SparkConf ) {


  def maxM(mese: String,misura:String) : DataFrame ={ // data la misura e il periodo, ritorna un dataframe con la stazione che ha
                                                     // registrato la misura max in quel determinato periodo
    /*
    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    dataframe.show()
    val colonnaT = dataframe.select("WBAN",misura,"YearMonthDay")
    colonnaT.show()
    val query= colonnaT.createOrReplaceTempView("TempMassima")
    //
    val result = session.sql("SELECT TempMassima."+misura+",TempMassima.WBAN,TempMassima.YearMonthDay " +
                                     "FROM TempMassima"+
                                    " WHERE  " +
      "TempMassima."+misura+"=(SELECT MAX(TempMassima."+misura+") FROM TempMassima WHERE TempMassima."+misura+" <> 'M')")
    result.show()
    return result
     */
    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    val colonnaT = dataframe.withColumn("Tmax", col(misura).cast("int")).agg(functions.max(misura) ).first().get(0)
    val colonna2T= dataframe.filter(dataframe(misura)===colonnaT).select("WBAN","Date",misura)
    colonna2T.show(10)
    return colonna2T
  }

  def minM(mese: String,misura:String) : DataFrame ={
    /*
    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    dataframe.show()
    val colonnaT = dataframe.select("WBAN",misura,"YearMonthDay")
    colonnaT.show()
    val query= colonnaT.createOrReplaceTempView("TempMassima")
    //
    val result = session.sql("SELECT TempMassima."+misura+",TempMassima.WBAN,TempMassima.YearMonthDay " +
      "FROM TempMassima"+
      " WHERE  " +
      "TempMassima."+misura+"=(SELECT MAX(TempMassima."+misura+") FROM TempMassima WHERE TempMassima."+misura+" <> 'M')")
    result.show()
    return result
     */
    val dataframe = session.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    val colonnaT = dataframe.withColumn("Tmin", col(misura).cast("int")).agg(functions.min(misura) ).first().get(0)
    val colonna2T= dataframe.filter(dataframe(misura)===colonnaT).select("WBAN","Date",misura)
    colonna2T.show(10)
    return colonna2T
  }
// periodo temporale di una determinata misura di una stazione
// ES: in 3 giorni ---> 70% vento, 10% pioggia, 20% nuvole
  // DAILY :codesum, snowfall, precipTotal,
  // FZRA,SN,TS,RA
 def percentuale(stazione:String, inizio:DataF, fine:DataF) : DataFrame ={

  val daily = session.read.option("header","true").csv("Dataset\\QCLCD"+inizio.year+inizio.month+"\\"+inizio.year+inizio.month+"daily.txt").select("WBAN","Date","CodeSum","PrecipTotal","SnowFall")
  val hourly = session.read.option("header","true").csv("Dataset\\QCLCD"+inizio.year+inizio.month+"\\"+inizio.year+inizio.month+"hourly.txt")
  val hourly2=hourly.select("WBAN","Date","SkyCondition").groupBy("WBAN","Date").agg(concat_ws(" ",collect_list("SkyCondition")) as "Condizione cielo")
  val daily2= daily.join(hourly2,Seq("WBAN","Date"))
  val daily3 = daily2.filter(col("WBAN")===stazione).toDF()
  daily3.sort("WBAN","Date")
  //val encoder = RowEncoder(StructType(Seq(StructField("id",StringType),StructField("name",IntegerType))))
  import session.implicits._
  val daily4 = daily3.sort("WBAN","Date").map(row=> {if(row(2).toString.contains("SN")) ("Neve", 1)
  else if(row(2).toString.contains("RA")) ("Pioggia", 1)
  else if(row(2).toString.contains("TS")) ("Temporale", 1)
  else if(row(2).toString.contains("FZRA")) ("Grandine", 1)
  else if(!row(3).toString.contains("T") && !row(3).toString.equals("M") && row(3).toString.toDouble>0) ("Pioggia",1)
  else if(!row(4).toString.contains("T") && !row(4).toString.equals("M") && row(4).toString.toDouble>0) ("Neve",1)
  else { val v1 = row(5).toString.split(" ")
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
  }).groupBy("_1").sum("_2").toDF().show()


  //else row(5).toString.split(" ").map(el=>{if(el.equals("CLR"))("Sereno",1) else List("Nuvoloso",1)})




  return null
 }


  def conta(cond:String): Boolean ={

    val v1 = cond.split(" ")
    var c = 0
    var n=0
    for(i<-v1) {
      if (i.contains("CLR"))
          c+=1
      else n+=1
      }
    if(c>n)return true
    return false
    }



}
