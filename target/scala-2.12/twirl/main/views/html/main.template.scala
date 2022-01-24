
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Form[Query],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(query:Form[Query])(implicit messages: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<!doctype html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>jQuery UI Autocomplete - Scrollable results</title>
        <link rel="stylesheet" href=""""),_display_(/*9.39*/routes/*9.45*/.Assets.versioned("stylesheets/jquery-ui.css")),format.raw/*9.91*/("""">
        <link rel="stylesheet" href=""""),_display_(/*10.39*/routes/*10.45*/.Assets.versioned("stylesheets/jquery-ui.theme.css")),format.raw/*10.97*/("""">
        <link rel="stylesheet" href=""""),_display_(/*11.39*/routes/*11.45*/.Assets.versioned("stylesheets/jquery-ui.structure.css")),format.raw/*11.101*/("""">
        <style>
                fieldset """),format.raw/*13.26*/("""{"""),format.raw/*13.27*/("""
                    """),format.raw/*14.21*/("""border: 0;
                """),format.raw/*15.17*/("""}"""),format.raw/*15.18*/("""
                """),format.raw/*16.17*/("""label """),format.raw/*16.23*/("""{"""),format.raw/*16.24*/("""
                    """),format.raw/*17.21*/("""display: block;
                    margin: 30px 0 0 0;
                """),format.raw/*19.17*/("""}"""),format.raw/*19.18*/("""
                """),format.raw/*20.17*/(""".overflow """),format.raw/*20.27*/("""{"""),format.raw/*20.28*/("""
                    """),format.raw/*21.21*/("""height: 200px;
                """),format.raw/*22.17*/("""}"""),format.raw/*22.18*/("""
        """),format.raw/*23.9*/("""</style>
        <script>
                $( function() """),format.raw/*25.31*/("""{"""),format.raw/*25.32*/("""
                    """),format.raw/*26.21*/("""var availableTags = [
                        "ActionScript","AppleScript","Asp",
                        "BASIC",
                        "C",
                        "C++",
                        "Clojure"];
                    $( "#tags" ).autocomplete("""),format.raw/*32.47*/("""{"""),format.raw/*32.48*/("""
                        """),format.raw/*33.25*/("""source: availableTags
                    """),format.raw/*34.21*/("""}"""),format.raw/*34.22*/(""");
                """),format.raw/*35.17*/("""}"""),format.raw/*35.18*/(""" """),format.raw/*35.19*/(""");
        </script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
                google.charts.load('current', """),format.raw/*39.47*/("""{"""),format.raw/*39.48*/("""
                    """),format.raw/*40.21*/("""'packages':['geochart'],
                """),format.raw/*41.17*/("""}"""),format.raw/*41.18*/(""");
                google.charts.setOnLoadCallback(drawRegionsMap);

                function drawRegionsMap() """),format.raw/*44.43*/("""{"""),format.raw/*44.44*/("""
                    """),format.raw/*45.21*/("""var data = google.visualization.arrayToDataTable([
                        ['Country', 'Popularity'],
                        ['Germany', 200],
                        ['United States', 300],
                        ['Brazil', 400],
                        ['Canada', 500],
                        ['France', 600],
                        ['RU', 700]
                    ]);

                    var options = """),format.raw/*55.35*/("""{"""),format.raw/*55.36*/("""region:"021""""),format.raw/*55.48*/("""}"""),format.raw/*55.49*/(""";

                    var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

                    chart.draw(data, options);
                """),format.raw/*60.17*/("""}"""),format.raw/*60.18*/("""
        """),format.raw/*61.9*/("""</script>
    </head>
    <body>
        <select id="selectmenu">
            <option>Slower</option>
            <option>Slow</option>
            <option selected="selected">Medium</option>
            <option>Fast</option>
            <option>Faster</option>
        </select>
        <select id="selectmenu2">
            <option>Slower</option>
            <option>Slow</option>
            <option selected="selected">Medium</option>
            <option>Fast</option>
            <option>Faster</option>
        </select>
        <div id="regions_div" style="width: 900px; height: 500px;"></div>
        <form method="post" action=""""),_display_(/*79.38*/routes/*79.44*/.Prova.p2()),format.raw/*79.55*/("""">
            """),_display_(/*80.14*/helper/*80.20*/.CSRF.formField),format.raw/*80.35*/("""
            """),format.raw/*81.13*/("""<div class="ui-widget">
                <label for="nameState">nameState: </label>
                <input name="nameState" id="nameState"> <!-- il tag name deve coincidere con la form -->
            </div>
            <br>
            <div class="ui-widget">
                <label for="column">column: </label>
                <input name="column" id="column">
            </div>
            <br>
            <div class="ui-widget">
                <label for="tipo">tipo: </label>
                <input name="tipo" id="tipo">
            </div>
            <input type="submit">
        </form>
        <div class="widget">
            <h1>Widget Buttons</h1>
            <button>A button element</button>

            <input type="submit" value="A submit button">

            <a href="#">An anchor</a>
        </div>
        <h1>CSS Buttons</h1>
        <button class="ui-button ui-widget ui-corner-all">A button element</button>

        <input class="ui-button ui-widget ui-corner-all" type="submit" value="A submit button">

        <a class="ui-button ui-widget ui-corner-all" href="#">An anchor</a>

        <script type="text/javascript" src=""""),_display_(/*112.46*/routes/*112.52*/.Assets.versioned("javascripts/jquery.js")),format.raw/*112.94*/(""""></script>
        <script type="text/javascript" src=""""),_display_(/*113.46*/routes/*113.52*/.Assets.versioned("javascripts/jquery-ui.js")),format.raw/*113.97*/(""""></script>
        <script>
                $( "#selectmenu" ).selectmenu();
                $( "#selectmenu2" ).selectmenu();
        </script>
    </body>
</html>


"""))
      }
    }
  }

  def render(query:Form[Query],messages:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(query)(messages)

  def f:((Form[Query]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (query) => (messages) => apply(query)(messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: ec77c684d23cf6d39729c043d76ca08784537fbb
                  MATRIX: 755->1|910->63|1194->321|1208->327|1274->373|1342->414|1357->420|1430->472|1498->513|1513->519|1591->575|1663->619|1692->620|1741->641|1796->668|1825->669|1870->686|1904->692|1933->693|1982->714|2082->786|2111->787|2156->804|2194->814|2223->815|2272->836|2331->867|2360->868|2396->877|2480->933|2509->934|2558->955|2843->1212|2872->1213|2925->1238|2995->1280|3024->1281|3071->1300|3100->1301|3129->1302|3360->1505|3389->1506|3438->1527|3507->1568|3536->1569|3675->1680|3704->1681|3753->1702|4191->2112|4220->2113|4260->2125|4289->2126|4491->2300|4520->2301|4556->2310|5222->2949|5237->2955|5269->2966|5312->2982|5327->2988|5363->3003|5404->3016|6588->4172|6604->4178|6668->4220|6753->4277|6769->4283|6836->4328
                  LINES: 21->1|26->2|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|37->13|37->13|38->14|39->15|39->15|40->16|40->16|40->16|41->17|43->19|43->19|44->20|44->20|44->20|45->21|46->22|46->22|47->23|49->25|49->25|50->26|56->32|56->32|57->33|58->34|58->34|59->35|59->35|59->35|63->39|63->39|64->40|65->41|65->41|68->44|68->44|69->45|79->55|79->55|79->55|79->55|84->60|84->60|85->61|103->79|103->79|103->79|104->80|104->80|104->80|105->81|136->112|136->112|136->112|137->113|137->113|137->113
                  -- GENERATED --
              */
          