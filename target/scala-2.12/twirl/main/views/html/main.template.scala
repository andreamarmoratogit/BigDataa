
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
        <script type='text/javascript' src=""""),_display_(/*15.46*/routes/*15.52*/.Assets.versioned("javascripts/googlechart.js")),format.raw/*15.99*/(""""></script>
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
                        <a class="nav-link" href=""""),_display_(/*29.52*/routes/*29.58*/.Prova.home()),format.raw/*29.71*/("""">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Query
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href=""""),_display_(/*36.61*/routes/*36.67*/.Prova.getMinAttribute()),format.raw/*36.91*/("""">Min Attribute</a>
                            <a class="dropdown-item" href="#">Max Attribute</a>
                            <a class="dropdown-item" href=""""),_display_(/*38.61*/routes/*38.67*/.Prova.getMeteoTemporale()),format.raw/*38.93*/("""">Meteo temporale</a>
                            <a class="dropdown-item" href="#">Time Series</a>
                            <a class="dropdown-item" href="#">Cartina colorata</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Machine Learning
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">?????</a>
                            <a class="dropdown-item" href="#">?????</a>
                            <a class="dropdown-item" href="#">?????</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <br>
        """),_display_(/*57.10*/content),format.raw/*57.17*/("""


        """),format.raw/*60.9*/("""<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
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
                  HASH: 7886ea418d04c5b21dff151efd87ad75f8a0e94d
                  MATRIX: 726->1|834->16|1090->246|1104->252|1170->298|1238->339|1253->345|1315->386|1383->427|1398->433|1471->485|1539->526|1554->532|1632->588|1707->636|1722->642|1785->684|1869->741|1884->747|1950->792|2034->849|2049->855|2117->902|3043->1801|3058->1807|3092->1820|3615->2316|3630->2322|3675->2346|3862->2506|3877->2512|3924->2538|4913->3500|4941->3507|4979->3518
                  LINES: 21->1|26->2|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|36->12|36->12|36->12|37->13|37->13|37->13|38->14|38->14|38->14|39->15|39->15|39->15|53->29|53->29|53->29|60->36|60->36|60->36|62->38|62->38|62->38|81->57|81->57|84->60
                  -- GENERATED --
              */
          