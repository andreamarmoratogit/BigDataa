// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  Prova_0: controllers.Prova,
  // @LINE:21
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Prova_0: controllers.Prova,
    // @LINE:21
    Assets_1: controllers.Assets
  ) = this(errorHandler, Prova_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Prova_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.Prova.home()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """minAttribute""", """controllers.Prova.postMinAttribute()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """minAttribute""", """controllers.Prova.getMinAttribute()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """meteoTempo""", """controllers.Prova.getMeteoTemporale()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """meteoTempo""", """controllers.Prova.postMeteoTemporale()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mediaAnnuale""", """controllers.Prova.getMediaAnnuale()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mediaAnnuale""", """controllers.Prova.postMediaAnnuale()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """kMeans""", """controllers.Prova.getKMeans()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """predictTemp""", """controllers.Prova.getPredictTemp()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """predictTemp""", """controllers.Prova.postPredictTemp()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """time""", """controllers.Prova.getTime()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """time""", """controllers.Prova.postTime()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """temp""", """controllers.Prova.getTemp()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Prova_home0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Prova_home0_invoker = createInvoker(
    Prova_0.home(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "home",
      Nil,
      "GET",
      this.prefix + """""",
      """ Home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Prova_postMinAttribute1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("minAttribute")))
  )
  private[this] lazy val controllers_Prova_postMinAttribute1_invoker = createInvoker(
    Prova_0.postMinAttribute(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "postMinAttribute",
      Nil,
      "POST",
      this.prefix + """minAttribute""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_Prova_getMinAttribute2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("minAttribute")))
  )
  private[this] lazy val controllers_Prova_getMinAttribute2_invoker = createInvoker(
    Prova_0.getMinAttribute(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getMinAttribute",
      Nil,
      "GET",
      this.prefix + """minAttribute""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Prova_getMeteoTemporale3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("meteoTempo")))
  )
  private[this] lazy val controllers_Prova_getMeteoTemporale3_invoker = createInvoker(
    Prova_0.getMeteoTemporale(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getMeteoTemporale",
      Nil,
      "GET",
      this.prefix + """meteoTempo""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Prova_postMeteoTemporale4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("meteoTempo")))
  )
  private[this] lazy val controllers_Prova_postMeteoTemporale4_invoker = createInvoker(
    Prova_0.postMeteoTemporale(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "postMeteoTemporale",
      Nil,
      "POST",
      this.prefix + """meteoTempo""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Prova_getMediaAnnuale5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mediaAnnuale")))
  )
  private[this] lazy val controllers_Prova_getMediaAnnuale5_invoker = createInvoker(
    Prova_0.getMediaAnnuale(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getMediaAnnuale",
      Nil,
      "GET",
      this.prefix + """mediaAnnuale""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Prova_postMediaAnnuale6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mediaAnnuale")))
  )
  private[this] lazy val controllers_Prova_postMediaAnnuale6_invoker = createInvoker(
    Prova_0.postMediaAnnuale(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "postMediaAnnuale",
      Nil,
      "POST",
      this.prefix + """mediaAnnuale""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Prova_getKMeans7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("kMeans")))
  )
  private[this] lazy val controllers_Prova_getKMeans7_invoker = createInvoker(
    Prova_0.getKMeans(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getKMeans",
      Nil,
      "GET",
      this.prefix + """kMeans""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Prova_getPredictTemp8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("predictTemp")))
  )
  private[this] lazy val controllers_Prova_getPredictTemp8_invoker = createInvoker(
    Prova_0.getPredictTemp(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getPredictTemp",
      Nil,
      "GET",
      this.prefix + """predictTemp""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Prova_postPredictTemp9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("predictTemp")))
  )
  private[this] lazy val controllers_Prova_postPredictTemp9_invoker = createInvoker(
    Prova_0.postPredictTemp(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "postPredictTemp",
      Nil,
      "POST",
      this.prefix + """predictTemp""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Prova_getTime10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("time")))
  )
  private[this] lazy val controllers_Prova_getTime10_invoker = createInvoker(
    Prova_0.getTime(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getTime",
      Nil,
      "GET",
      this.prefix + """time""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Prova_postTime11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("time")))
  )
  private[this] lazy val controllers_Prova_postTime11_invoker = createInvoker(
    Prova_0.postTime(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "postTime",
      Nil,
      "POST",
      this.prefix + """time""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Prova_getTemp12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("temp")))
  )
  private[this] lazy val controllers_Prova_getTemp12_invoker = createInvoker(
    Prova_0.getTemp(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "getTemp",
      Nil,
      "GET",
      this.prefix + """temp""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_Assets_versioned13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned13_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Prova_home0_route(params@_) =>
      call { 
        controllers_Prova_home0_invoker.call(Prova_0.home())
      }
  
    // @LINE:7
    case controllers_Prova_postMinAttribute1_route(params@_) =>
      call { 
        controllers_Prova_postMinAttribute1_invoker.call(Prova_0.postMinAttribute())
      }
  
    // @LINE:8
    case controllers_Prova_getMinAttribute2_route(params@_) =>
      call { 
        controllers_Prova_getMinAttribute2_invoker.call(Prova_0.getMinAttribute())
      }
  
    // @LINE:9
    case controllers_Prova_getMeteoTemporale3_route(params@_) =>
      call { 
        controllers_Prova_getMeteoTemporale3_invoker.call(Prova_0.getMeteoTemporale())
      }
  
    // @LINE:10
    case controllers_Prova_postMeteoTemporale4_route(params@_) =>
      call { 
        controllers_Prova_postMeteoTemporale4_invoker.call(Prova_0.postMeteoTemporale())
      }
  
    // @LINE:11
    case controllers_Prova_getMediaAnnuale5_route(params@_) =>
      call { 
        controllers_Prova_getMediaAnnuale5_invoker.call(Prova_0.getMediaAnnuale())
      }
  
    // @LINE:12
    case controllers_Prova_postMediaAnnuale6_route(params@_) =>
      call { 
        controllers_Prova_postMediaAnnuale6_invoker.call(Prova_0.postMediaAnnuale())
      }
  
    // @LINE:13
    case controllers_Prova_getKMeans7_route(params@_) =>
      call { 
        controllers_Prova_getKMeans7_invoker.call(Prova_0.getKMeans())
      }
  
    // @LINE:14
    case controllers_Prova_getPredictTemp8_route(params@_) =>
      call { 
        controllers_Prova_getPredictTemp8_invoker.call(Prova_0.getPredictTemp())
      }
  
    // @LINE:15
    case controllers_Prova_postPredictTemp9_route(params@_) =>
      call { 
        controllers_Prova_postPredictTemp9_invoker.call(Prova_0.postPredictTemp())
      }
  
    // @LINE:16
    case controllers_Prova_getTime10_route(params@_) =>
      call { 
        controllers_Prova_getTime10_invoker.call(Prova_0.getTime())
      }
  
    // @LINE:17
    case controllers_Prova_postTime11_route(params@_) =>
      call { 
        controllers_Prova_postTime11_invoker.call(Prova_0.postTime())
      }
  
    // @LINE:18
    case controllers_Prova_getTemp12_route(params@_) =>
      call { 
        controllers_Prova_getTemp12_invoker.call(Prova_0.getTemp())
      }
  
    // @LINE:21
    case controllers_Assets_versioned13_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned13_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
