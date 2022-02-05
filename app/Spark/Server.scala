package Spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Server extends App {
  val conf=new SparkConf().setMaster("local[*]").setAppName("Meteo").set("spark.driver.memory","6g")
    .set("spark.sql.autoBroadcastJoinThreshold","-1")
  val sc=new SparkContext(conf)
  val ss=SparkSession.builder().getOrCreate()
  val g=new Gestore(sc,ss,conf)
  g.predictor2("Tmax")
}
