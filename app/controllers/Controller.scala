package controllers

import Spark.{DataF, Gestore}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import play.api.data.Form
import play.api.data.Forms._
import play.api.inject.ApplicationLifecycle
import play.api.mvc._
import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

case class MA(stazione:String,misura:String){}
case class Query (mese: String,misura: String){}
case class MTQ(dataIn:String,dataFin:String,stazione:String){}
case class TIME(stazione:String,misura:String){}
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


  val mtForm: Form[MTQ]= Form(mapping(
    "dataIn" -> text,
    "dataFin" -> text,
    "stazione" -> text
  )(MTQ.apply)(MTQ.unapply))

  val maForm: Form[MA]= Form(mapping(
    "stazione" -> text,
    "misura" -> text
  )(MA.apply)(MA.unapply))

  val ptForm:Form[MTQ]=Form(mapping(
    "dataIn" -> text,
    "dataFin" -> text,
    "stazione" -> text
  )(MTQ.apply)(MTQ.unapply))

  val tseries:Form[TIME]=Form(mapping(
    "stazione" -> text,
    "misura" ->text
  )(TIME.apply)(TIME.unapply))

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
    val s=g.getStationsM().toJSON.collectAsList().toString
    Ok(views.html.meteo_tempo(mtForm)(s)("[]"))
  }

  def postMeteoTemporale(): Action[AnyContent] =Action{ implicit Request =>
    val s=g.getStationsM().toJSON.collectAsList().toString
    mtForm.bindFromRequest().fold(
      error => BadRequest(""),
      q => {
        val st=q.dataIn.split("/")
        val st2=q.dataFin.split("/")

        val d=g.percentuale(q.stazione,new DataF(st(0),st(1),st(2)),new DataF(st2(0),st2(1),st2(2)))
        d.show()

        Ok(views.html.meteo_tempo(mtForm)(s)(d.toJSON.collectAsList().toString))}
    )
  }

  def getMediaAnnuale(): Action[AnyContent] = Action { implicit Request =>
    val s=g.getStationsM().toJSON.collectAsList().toString
    Ok(views.html.mediaAnnuale(maForm)(s)(Array(0.0)))

  }

  def postMediaAnnuale(): Action[AnyContent] = Action { implicit Request =>
    import ss.implicits._
    val s=g.getStationsM().toJSON.collectAsList().toString
    maForm.bindFromRequest().fold(
      error => BadRequest(""),
      q => {
        val d=g.stagioni(q.stazione,q.misura).drop("WBAN").as[(Double,Double,Double)]
        d.show()

        Ok(views.html.mediaAnnuale(maForm)(s)(Array(d.head()._3,d.head()._1,d.head()._2)))}
    )
  }

  def getTime():Action[AnyContent] = Action { implicit Request =>
    val s=g.getStationsM().toJSON.collectAsList().toString
    Ok(views.html.time(tseries)(s)((Array((0,0,0,0)))))
  }

  def postTime():Action[AnyContent] = Action { implicit Request =>
    import ss.implicits._
    val s=g.getStationsM().toJSON.collectAsList().toString
    tseries.bindFromRequest().fold(
      error => BadRequest(""),
      q =>{
        var misura=""
        q.misura match {
          case "Temperatura Max" => misura="Tmax"
          case "Temperatura Min" => misura="Tmin"
          case "Neve" => misura="SnowFall"
          case "Pioggia" => misura="PrecipTotal"
          case "Vento" => misura="AvgSpeed"
        }
        val serie =g.time_series(q.stazione,misura).as[(Double,Int,Int,Int)].collect()// misura,anno,mese,giorno
        serie.foreach(println)
        Ok(views.html.time(tseries)(s)(serie))

      })
  }

  def getTemp():Action[AnyContent] = Action { implicit Request =>
    import ss.implicits._
    val s=g.getTempForMap().as[(String,Double)]
    Ok(views.html.temp()(s.collect()))
  }

  def getKMeans(): Action[AnyContent] = Action { implicit Request =>
    val d=g.kMeans()
    Ok(views.html.kmeans(d))
  }

  def getPredictTemp(): Action[AnyContent] = Action { implicit Request =>
    val s=g.getStationsM().toJSON.collectAsList().toString
    Ok(views.html.predictTemp(ptForm)(s)(Array((0,0,0,0,0,0,0))))
  }

  def postPredictTemp(): Action[AnyContent] = Action { implicit Request =>
    import ss.implicits._

    val s=g.getStationsM().toJSON.collectAsList().toString
    ptForm.bindFromRequest().fold(
      error => BadRequest(""),
      q => {
        val st = q.dataIn.split("/")
        val st2 = q.dataFin.split("/")
        val d=g.predictor(q.stazione,new DataF(st(0), st(1), st(2)), new DataF(st2(0), st2(1), st2(2)))
        Ok(views.html.predictTemp(ptForm)(s)(d.as[(Double,Double,Double,Double,Int,Int,Int)].collect()))
      })
  }


}
