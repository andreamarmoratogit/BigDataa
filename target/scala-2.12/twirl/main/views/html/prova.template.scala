
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object prova extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Query,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(query: Query):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.16*/("""
"""),format.raw/*2.1*/("""<!doctype html>

<t1 >"""),_display_(/*4.7*/query/*4.12*/.nameState),format.raw/*4.22*/("""</t1>


"""))
      }
    }
  }

  def render(query:Query): play.twirl.api.HtmlFormat.Appendable = apply(query)

  def f:((Query) => play.twirl.api.HtmlFormat.Appendable) = (query) => apply(query)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/prova.scala.html
                  HASH: 13a3d9eea0856fd2b4d336f861e85503756ec669
                  MATRIX: 728->1|837->15|865->17|915->42|928->47|958->57
                  LINES: 21->1|26->1|27->2|29->4|29->4|29->4
                  -- GENERATED --
              */
          