package Spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Server extends App {
  val conf=new SparkConf().setMaster("local").setAppName("Meteo")
  val sc=new SparkContext(conf)
  val ss=SparkSession.builder().getOrCreate()
  val g=new Gestore(sc,ss,conf)
  g.max("201301","Tmax")
  //g.start
}
