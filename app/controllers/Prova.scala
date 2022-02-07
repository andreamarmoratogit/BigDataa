package controllers

import Spark.{Gestore, Minmax}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import play.api.data.Form
import play.api.data.Forms._
import play.api.inject.ApplicationLifecycle
import play.api.mvc._


import javax.inject.{Inject, Singleton}
import scala.concurrent.Future


case class Query (mese: String,misura: String){}

@Singleton
class Prova @Inject()(cc:MessagesControllerComponents,lifeCicle:ApplicationLifecycle)  extends MessagesAbstractController(cc){
  val conf=new SparkConf().setMaster("local[*]").setAppName("Meteo").set("spark.driver.memory","6g")
    .set("spark.sql.autoBroadcastJoinThreshold","-1")
  val sc=new SparkContext(conf)
  val ss=SparkSession.builder().getOrCreate()
  val g=new Gestore(sc,ss,conf)
  lifeCicle.addStopHook{()=>
    Future.successful(sc.stop(),ss.stop())
  }
  val minMaxAttr: Form[Query] = Form(mapping(
    "mese" -> text,
    "misura" -> text,
  )(Query.apply)(Query.unapply))



  def home(): Action[AnyContent] = Action { implicit Request =>
    Ok(views.html.home())
  }
  def getMinAttribute(): Action[AnyContent] = Action { implicit Request =>
    Ok(views.html.minAttribute(minMaxAttr)("[]"))
  }
  def postMinAttribute(): Action[AnyContent] = Action{ implicit Request =>
    minMaxAttr.bindFromRequest().fold(
      error => BadRequest(""),
      q => {
        var mese=" "
        q.mese match {
          case "Gennaio"=>mese="01"
          case "Febbraio" =>mese="02"
        }
        var misura=""
        q.misura match {
          case "Temperatura Max" => misura="Tmax"
        }
        /*implicit val MinmaxWrites = new Writes[Minmax] {
          def writes(q:Minmax) = Json.obj(
            "name"->q.name,
            "lat"-> q.lat,
            "lon"-> q.lon
          )
        }*/
        //val ret=g.minM(mese,misura).reduce((a1,a2)=>a1+","+a2)
        val ret =g.minM(mese,misura).toJSON.collectAsList().toString
        //println(ret)
        //val ret = Minmax("94059",48.4887,-105.2096)
        //val json=Json.toJson(ret)

        Ok(views.html.minAttribute(minMaxAttr)(ret))
      })
  }

  def getMeteoTemporale(): Action[AnyContent] =Action{ implicit Request =>
    val s=g.getStations().toJSON.collectAsList().toString
    Ok(views.html.meteo_tempo(minMaxAttr)(s))
  }
  def postMeteoTemporale(station:String): Action[AnyContent] =Action{ implicit Request =>
    val s=g.getStations().toJSON.collectAsList().toString
    Ok(views.html.meteo_tempo(minMaxAttr)(s))
  }

  def p3(): Action[AnyContent] = Action { implicit Request =>
    minMaxAttr.bindFromRequest().fold(
      error => BadRequest(""),
      q => Ok(views.html.prova())
    )
  }


}
