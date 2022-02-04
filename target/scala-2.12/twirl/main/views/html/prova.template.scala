
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

object prova extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<!doctype html>
<html lang="en">
        <head>
                <meta charset="utf-8">
                <link rel="stylesheet" href=""""),_display_(/*6.47*/routes/*6.53*/.Assets.versioned("stylesheets/openlayers.css")),format.raw/*6.100*/("""">
                <style>
      .map """),format.raw/*8.12*/("""{"""),format.raw/*8.13*/("""
              """),format.raw/*9.15*/("""height: 400px;
              width: 100%;
      """),format.raw/*11.7*/("""}"""),format.raw/*11.8*/("""
    """),format.raw/*12.5*/("""</style>
            <script type="text/javascript" src=""""),_display_(/*13.50*/routes/*13.56*/.Assets.versioned("javascripts/openlayers.js")),format.raw/*13.102*/(""""></script>
                <title>OpenLayers example</title>
        </head>
        <body>
                <h2>My Map</h2>
                <div id="map" class="map"></div>
                <script type="text/javascript">
      var map = new ol.Map("""),format.raw/*20.28*/("""{"""),format.raw/*20.29*/("""
              """),format.raw/*21.15*/("""target: 'map',
              layers: [
                      new ol.layer.Tile("""),format.raw/*23.41*/("""{"""),format.raw/*23.42*/("""
                              """),format.raw/*24.31*/("""source: new ol.source.OSM()
                      """),format.raw/*25.23*/("""}"""),format.raw/*25.24*/(""")
              ],
              view: new ol.View("""),format.raw/*27.33*/("""{"""),format.raw/*27.34*/("""
                      """),format.raw/*28.23*/("""center: ol.proj.fromLonLat([37.41, 8.82]),
                      zoom: 4
              """),format.raw/*30.15*/("""}"""),format.raw/*30.16*/(""")
      """),format.raw/*31.7*/("""}"""),format.raw/*31.8*/(""");
    </script>
        </body>
</html>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/prova.scala.html
                  HASH: f57ca00b3a02f2391616b393b265cccc18f23f08
                  MATRIX: 722->1|818->3|846->5|1009->142|1023->148|1091->195|1158->235|1186->236|1229->252|1306->302|1334->303|1367->309|1453->368|1468->374|1536->420|1820->676|1849->677|1893->693|2002->774|2031->775|2091->807|2170->858|2199->859|2280->912|2309->913|2361->937|2478->1026|2507->1027|2543->1036|2571->1037
                  LINES: 21->1|26->1|27->2|31->6|31->6|31->6|33->8|33->8|34->9|36->11|36->11|37->12|38->13|38->13|38->13|45->20|45->20|46->21|48->23|48->23|49->24|50->25|50->25|52->27|52->27|53->28|55->30|55->30|56->31|56->31
                  -- GENERATED --
              */
          