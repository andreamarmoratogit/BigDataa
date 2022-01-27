package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


object Spark {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf();
    conf.setAppName("configurazione");
    conf.setMaster("local[*]") // usa la macchina localmente; * indica che so vogliono usare tutti i core della macchina
    conf.set("spark.driver.allowMultipleContexts", "true")
    Logger.getLogger("org").setLevel(Level.OFF);
    Logger.getLogger("akka").setLevel(Level.OFF);
    val sc = new SparkContext(conf);
    val sessione = SparkSession.builder().getOrCreate()
    val gestore = new Gestore(sc, sessione, conf)
    //gestore.maxM("201304","Tmax")
    //gestore.minM("201301","Tmin")
    gestore.percentuale("03013", new DataF("2013", "01", "01"), new DataF("2013", "01", "03"))

  }

  def wordCount: Array[(String,Int)] = {
    val conf = new SparkConf();
    conf.setAppName("configurazione");
    conf.setMaster("local[*]") // usa la macchina localmente; * indica che so vogliono usare tutti i core della macchina
    conf.set("spark.driver.allowMultipleContexts", "true")
    Logger.getLogger("org").setLevel(Level.OFF);
    Logger.getLogger("akka").setLevel(Level.OFF);
    val sc = new SparkContext(conf);
    val sessione = SparkSession.builder().getOrCreate()
    val lines = sc.textFile("C:\\Users\\Pasquale\\Desktop\\alphabeta.txt"); // caricamento del dataset su lines
    val words = lines.flatMap(line => line.split(' ')) // prendo tutte le parole e le mappo su una flat map
    val wordsKVRdd = words.map(x => (x, 1)) // mappo ogni parola con il conteggio a 1
    val count = wordsKVRdd.reduceByKey((x, y) => x + y).map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1)).take(10)
    return count


  }
}
