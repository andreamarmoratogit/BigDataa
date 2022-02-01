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
  // @LINE:16
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Prova_0: controllers.Prova,
    // @LINE:16
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
    ("""GET""", this.prefix, """controllers.Prova.p1()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """p2""", """controllers.Prova.p2()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """p2""", """controllers.Prova.p2()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """p4""", """controllers.Prova.p4()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Prova_p10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Prova_p10_invoker = createInvoker(
    Prova_0.p1(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "p1",
      Nil,
      "GET",
      this.prefix + """""",
      """ Home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Prova_p21_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("p2")))
  )
  private[this] lazy val controllers_Prova_p21_invoker = createInvoker(
    Prova_0.p2(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "p2",
      Nil,
      "POST",
      this.prefix + """p2""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_Prova_p22_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("p2")))
  )
  private[this] lazy val controllers_Prova_p22_invoker = createInvoker(
    Prova_0.p2(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "p2",
      Nil,
      "GET",
      this.prefix + """p2""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Prova_p43_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("p4")))
  )
  private[this] lazy val controllers_Prova_p43_invoker = createInvoker(
    Prova_0.p4(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Prova",
      "p4",
      Nil,
      "GET",
      this.prefix + """p4""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_versioned4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned4_invoker = createInvoker(
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
    case controllers_Prova_p10_route(params@_) =>
      call { 
        controllers_Prova_p10_invoker.call(Prova_0.p1())
      }
  
    // @LINE:7
    case controllers_Prova_p21_route(params@_) =>
      call { 
        controllers_Prova_p21_invoker.call(Prova_0.p2())
      }
  
    // @LINE:8
    case controllers_Prova_p22_route(params@_) =>
      call { 
        controllers_Prova_p22_invoker.call(Prova_0.p2())
      }
  
    // @LINE:9
    case controllers_Prova_p43_route(params@_) =>
      call { 
        controllers_Prova_p43_invoker.call(Prova_0.p4())
      }
  
    // @LINE:16
    case controllers_Assets_versioned4_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned4_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
