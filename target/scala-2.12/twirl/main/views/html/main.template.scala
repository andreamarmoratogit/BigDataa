
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content:Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<!doctype html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>BigData Project</title>
        <link rel="stylesheet" href=""""),_display_(/*9.39*/routes/*9.45*/.Assets.versioned("stylesheets/jquery-ui.css")),format.raw/*9.91*/("""">
        <link rel="stylesheet" href=""""),_display_(/*10.39*/routes/*10.45*/.Assets.versioned("stylesheets/main.css")),format.raw/*10.86*/("""">
        <link rel="stylesheet" href=""""),_display_(/*11.39*/routes/*11.45*/.Assets.versioned("stylesheets/jquery-ui.theme.css")),format.raw/*11.97*/("""">
        <link rel="stylesheet" href=""""),_display_(/*12.39*/routes/*12.45*/.Assets.versioned("stylesheets/jquery-ui.structure.css")),format.raw/*12.101*/("""">
        <script type="text/javascript" src=""""),_display_(/*13.46*/routes/*13.52*/.Assets.versioned("javascripts/jquery.js")),format.raw/*13.94*/(""""></script>
        <script type="text/javascript" src=""""),_display_(/*14.46*/routes/*14.52*/.Assets.versioned("javascripts/jquery-ui.js")),format.raw/*14.97*/(""""></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" >
            <a class="navbar-brand" >Meteo Project</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href=""""),_display_(/*28.52*/routes/*28.58*/.Prova.home()),format.raw/*28.71*/("""">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Query
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href=""""),_display_(/*35.61*/routes/*35.67*/.Prova.getMinAttribute()),format.raw/*35.91*/("""">Min Attribute</a>
                            <a class="dropdown-item" href="#">Max Attribute</a>
                            <a class="dropdown-item" href=""""),_display_(/*37.61*/routes/*37.67*/.Prova.getMeteoTemporale()),format.raw/*37.93*/("""">Meteo temporale</a>
                            <a class="dropdown-item" href=""""),_display_(/*38.61*/routes/*38.67*/.Prova.getTime()),format.raw/*38.83*/("""">Time Series</a>
                            <a class="dropdown-item" href=""""),_display_(/*39.61*/routes/*39.67*/.Prova.getTemp()),format.raw/*39.83*/("""">Cartina colorata</a>
                            <a class="dropdown-item" href=""""),_display_(/*40.61*/routes/*40.67*/.Prova.getMediaAnnuale()),format.raw/*40.91*/("""">Media Annuale</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Machine Learning
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href=""""),_display_(/*48.61*/routes/*48.67*/.Prova.getKMeans()),format.raw/*48.85*/("""">Clustering</a>
                            <a class="dropdown-item" href=""""),_display_(/*49.61*/routes/*49.67*/.Prova.getPredictTemp()),format.raw/*49.90*/("""">Predittore Temp</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <br>
        """),_display_(/*56.10*/content),format.raw/*56.17*/("""


        """),format.raw/*59.9*/("""<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>


"""))
      }
    }
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: e76e783bf56deaa68f6ee68a05a4edc0b7cd18b4
                  MATRIX: 726->1|834->16|1090->246|1104->252|1170->298|1238->339|1253->345|1315->386|1383->427|1398->433|1471->485|1539->526|1554->532|1632->588|1707->636|1722->642|1785->684|1869->741|1884->747|1950->792|2876->1691|2891->1697|2925->1710|3448->2206|3463->2212|3508->2236|3695->2396|3710->2402|3757->2428|3866->2510|3881->2516|3918->2532|4023->2610|4038->2616|4075->2632|4185->2715|4200->2721|4245->2745|4780->3253|4795->3259|4834->3277|4938->3354|4953->3360|4997->3383|5181->3540|5209->3547|5247->3558
                  LINES: 21->1|26->2|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|52->28|52->28|52->28|59->35|59->35|59->35|61->37|61->37|61->37|62->38|62->38|62->38|63->39|63->39|63->39|64->40|64->40|64->40|72->48|72->48|72->48|73->49|73->49|73->49|80->56|80->56|83->59
                  -- GENERATED --
              */
          