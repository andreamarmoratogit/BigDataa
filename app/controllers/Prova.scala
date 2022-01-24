package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

import javax.inject.Inject


case class Query (nameState: String,column: String, tipo:String){}

class Prova @Inject()(cc:MessagesControllerComponents)  extends MessagesAbstractController(cc){

  val query: Form[Query] = Form(mapping(
    "nameState" -> text,
    "column" -> text,
    "tipo" -> text
  )(Query.apply)(Query.unapply))


  def p1(): Action[AnyContent] = Action { implicit Request =>
    Ok(views.html.main(query))
  }

  def p2(): Action[AnyContent] = Action{ implicit Request =>
    query.bindFromRequest().fold(
      error => BadRequest(""),
      q => Ok(views.html.prova(q))
    )


  }


}
