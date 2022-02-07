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
  // @LINE:14
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Prova_0: controllers.Prova,
    // @LINE:14
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
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """meteoTempo""", """controllers.Prova.postMeteoTemporale(String:String)"""),
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
    Prova_0.postMeteoTemporale(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "postMeteoTemporale",
      Seq(classOf[String]),
      "POST",
      this.prefix + """meteoTempo""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
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
      call(params.fromQuery[String]("String", None)) { (String) =>
        controllers_Prova_postMeteoTemporale4_invoker.call(Prova_0.postMeteoTemporale(String))
      }
  
    // @LINE:14
    case controllers_Assets_versioned5_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
