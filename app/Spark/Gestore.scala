package Spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}

class Gestore(sc: SparkContext,ss:SparkSession,conf:SparkConf) {
  def max(mese: String,misura:String) : DataFrame ={
    val dataframe = ss.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    dataframe.show()
    val colonnaT = dataframe.select("WBAN",misura,"YearMonthDay")
    colonnaT.show()
    val query= colonnaT.createOrReplaceTempView("TempMassima")
    //
    val result = ss.sql("SELECT TempMassima."+misura+",TempMassima.WBAN,TempMassima.YearMonthDay " +
      "FROM TempMassima"+
      " WHERE  " +
      "TempMassima."+misura+"=(SELECT MAX(TempMassima."+misura+") FROM TempMassima WHERE TempMassima."+misura+" <> 'M')")
    result.show()

    return result
  }

  def min(mese: String,misura:String) : DataFrame ={
    val dataframe = ss.read.option("header","true").csv("Dataset\\QCLCD"+mese+"\\"+mese+"daily.txt")
    dataframe.show()
    val colonnaT = dataframe.select("WBAN",misura,"YearMonthDay")
    colonnaT.show()
    val query= colonnaT.createOrReplaceTempView("TempMassima")
    //
    val result = ss.sql("SELECT TempMassima."+misura+",TempMassima.WBAN,TempMassima.YearMonthDay " +
      "FROM TempMassima"+
      " WHERE  " +
      "TempMassima."+misura+"=(SELECT MAX(TempMassima."+misura+") FROM TempMassima WHERE TempMassima."+misura+" <> 'M')")
    result.show()
    return result
  }

  def start:Unit={
    val azione=""
    // prendi azione dalla pagina web

    azione match {
      case "max" =>
      case "min" =>
      case _ =>
    }
    start

  }

}
