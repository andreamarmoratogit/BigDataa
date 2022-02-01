
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
<html>
    <head>
        <script type='text/javascript' src="https://www.gstatic.com/charts/loader.js"></script>
        <script type='text/javascript'>
                google.charts.load('current', """),format.raw/*7.47*/("""{"""),format.raw/*7.48*/("""
                    """),format.raw/*8.21*/("""'packages': ['geochart'],
                    // Note: Because markers require geocoding, you'll need a mapsApiKey.
                    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
                    'mapsApiKey': 'AIzaSyDCbtHkiH8ftf1b4_eCxqCZcsr7Nwe2lcw'
                """),format.raw/*12.17*/("""}"""),format.raw/*12.18*/(""");
                google.charts.setOnLoadCallback(drawMarkersMap);

                function drawMarkersMap() """),format.raw/*15.43*/("""{"""),format.raw/*15.44*/("""
                    """),format.raw/*16.21*/("""var data = google.visualization.arrayToDataTable([
                        ['City',   'Population', 'Area'],
                        ['Rome',      2761477,    1285.31],
                        ['Milan',     1324110,    181.76],
                        ['Naples',    959574,     117.27],
                        ['Turin',     907563,     130.17],
                        ['Palermo',   655875,     158.9],
                        ['Genoa',     607906,     243.60],
                        ['Bologna',   380181,     140.7],
                        ['Florence',  371282,     102.41],
                        ['Fiumicino', 67370,      213.44],
                        ['Anzio',     52192,      43.43],
                        ['Ciampino',  38262,      11]
                    ]);

                    var options = """),format.raw/*31.35*/("""{"""),format.raw/*31.36*/("""
                        """),format.raw/*32.25*/("""region: 'IT',
                        displayMode: 'markers',
                        colorAxis: """),format.raw/*34.36*/("""{"""),format.raw/*34.37*/("""colors: ['green', 'blue']"""),format.raw/*34.62*/("""}"""),format.raw/*34.63*/("""
                    """),format.raw/*35.21*/("""}"""),format.raw/*35.22*/(""";

                    var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
                    chart.draw(data, options);
                """),format.raw/*39.17*/("""}"""),format.raw/*39.18*/(""";
        </script>
    </head>
    <body>
        <div id="chart_div" style="width: 900px; height: 500px;"></div>
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
                  HASH: 1cf021afc31477b80ab023ba013cae6a41c2ca74
                  MATRIX: 722->1|818->3|846->5|1094->226|1122->227|1171->249|1522->572|1551->573|1693->687|1722->688|1772->710|2625->1535|2654->1536|2708->1562|2835->1661|2864->1662|2917->1687|2946->1688|2996->1710|3025->1711|3228->1886|3257->1887
                  LINES: 21->1|26->1|27->2|32->7|32->7|33->8|37->12|37->12|40->15|40->15|41->16|56->31|56->31|57->32|59->34|59->34|59->34|59->34|60->35|60->35|64->39|64->39
                  -- GENERATED --
              */
          