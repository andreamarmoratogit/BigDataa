
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
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<script>
    // A point click event that uses the Renderer to draw a label next to the point
    // On subsequent clicks, move the existing label instead of creating a new one.
    Highcharts.addEvent(Highcharts.Point, 'click', function () """),format.raw/*5.64*/("""{"""),format.raw/*5.65*/("""
        """),format.raw/*6.9*/("""if (this.series.options.className.indexOf('popup-on-click') !== -1) """),format.raw/*6.77*/("""{"""),format.raw/*6.78*/("""
            """),format.raw/*7.13*/("""const chart = this.series.chart;
            const date = Highcharts.dateFormat('%A, %b %e, %Y', this.x);
            const text = `<b>$"""),format.raw/*9.31*/("""{"""),format.raw/*9.32*/("""date"""),format.raw/*9.36*/("""}"""),format.raw/*9.37*/("""</b><br/>$"""),format.raw/*9.47*/("""{"""),format.raw/*9.48*/("""this.y"""),format.raw/*9.54*/("""}"""),format.raw/*9.55*/(""" """),format.raw/*9.56*/("""$"""),format.raw/*9.57*/("""{"""),format.raw/*9.58*/("""this.series.name"""),format.raw/*9.74*/("""}"""),format.raw/*9.75*/("""`;

            const anchorX = this.plotX + this.series.xAxis.pos;
            const anchorY = this.plotY + this.series.yAxis.pos;
            const align = anchorX < chart.chartWidth - 200 ? 'left' : 'right';
            const x = align === 'left' ? anchorX + 10 : anchorX - 10;
            const y = anchorY - 30;
            if (!chart.sticky) """),format.raw/*16.32*/("""{"""),format.raw/*16.33*/("""
                """),format.raw/*17.17*/("""chart.sticky = chart.renderer
                        .label(text, x, y, 'callout',  anchorX, anchorY)
                        .attr("""),format.raw/*19.31*/("""{"""),format.raw/*19.32*/("""
                            """),format.raw/*20.29*/("""align,
                            fill: 'rgba(0, 0, 0, 0.75)',
                            padding: 10,
                            zIndex: 7 // Above series, below tooltip
                        """),format.raw/*24.25*/("""}"""),format.raw/*24.26*/(""")
                        .css("""),format.raw/*25.30*/("""{"""),format.raw/*25.31*/("""
                            """),format.raw/*26.29*/("""color: 'white'
                        """),format.raw/*27.25*/("""}"""),format.raw/*27.26*/(""")
                        .on('click', function () """),format.raw/*28.50*/("""{"""),format.raw/*28.51*/("""
                            """),format.raw/*29.29*/("""chart.sticky = chart.sticky.destroy();
                        """),format.raw/*30.25*/("""}"""),format.raw/*30.26*/(""")
                        .add();
            """),format.raw/*32.13*/("""}"""),format.raw/*32.14*/(""" """),format.raw/*32.15*/("""else """),format.raw/*32.20*/("""{"""),format.raw/*32.21*/("""
                """),format.raw/*33.17*/("""chart.sticky
                        .attr("""),format.raw/*34.31*/("""{"""),format.raw/*34.32*/(""" """),format.raw/*34.33*/("""align, text """),format.raw/*34.45*/("""}"""),format.raw/*34.46*/(""")
                        .animate("""),format.raw/*35.34*/("""{"""),format.raw/*35.35*/(""" """),format.raw/*35.36*/("""anchorX, anchorY, x, y """),format.raw/*35.59*/("""}"""),format.raw/*35.60*/(""", """),format.raw/*35.62*/("""{"""),format.raw/*35.63*/(""" """),format.raw/*35.64*/("""duration: 250 """),format.raw/*35.78*/("""}"""),format.raw/*35.79*/(""");
            """),format.raw/*36.13*/("""}"""),format.raw/*36.14*/("""
        """),format.raw/*37.9*/("""}"""),format.raw/*37.10*/("""
    """),format.raw/*38.5*/("""}"""),format.raw/*38.6*/(""");

    var d=[["Day Index","Tmax Eff","Tmax Pred","Tmin Eff","Tmin Pred"]]

    Highcharts.chart('container2', """),format.raw/*42.36*/("""{"""),format.raw/*42.37*/("""
        """),format.raw/*43.9*/("""chart: """),format.raw/*43.16*/("""{"""),format.raw/*43.17*/("""
            """),format.raw/*44.13*/("""scrollablePlotArea: """),format.raw/*44.33*/("""{"""),format.raw/*44.34*/("""
                """),format.raw/*45.17*/("""minWidth: 700
            """),format.raw/*46.13*/("""}"""),format.raw/*46.14*/("""
        """),format.raw/*47.9*/("""}"""),format.raw/*47.10*/(""",

        data: """),format.raw/*49.15*/("""{"""),format.raw/*49.16*/("""
            """),format.raw/*50.13*/("""column: d,
            dateFormat:"YYYYmmdd",
            beforeParse: function (csv) """),format.raw/*52.41*/("""{"""),format.raw/*52.42*/("""
                """),format.raw/*53.17*/("""return csv.replace(/\n\n/g, '\n');
            """),format.raw/*54.13*/("""}"""),format.raw/*54.14*/("""
        """),format.raw/*55.9*/("""}"""),format.raw/*55.10*/(""",

        title: """),format.raw/*57.16*/("""{"""),format.raw/*57.17*/("""
            """),format.raw/*58.13*/("""text: 'Daily sessions at www.highcharts.com'
        """),format.raw/*59.9*/("""}"""),format.raw/*59.10*/(""",

        subtitle: """),format.raw/*61.19*/("""{"""),format.raw/*61.20*/("""
            """),format.raw/*62.13*/("""text: 'Source: Google Analytics'
        """),format.raw/*63.9*/("""}"""),format.raw/*63.10*/(""",

        xAxis: """),format.raw/*65.16*/("""{"""),format.raw/*65.17*/("""
            """),format.raw/*66.13*/("""tickInterval: 7 * 24 * 3600 * 1000, // one week
            tickWidth: 0,
            gridLineWidth: 1,
            labels: """),format.raw/*69.21*/("""{"""),format.raw/*69.22*/("""
                """),format.raw/*70.17*/("""align: 'left',
                x: 3,
                y: -3
            """),format.raw/*73.13*/("""}"""),format.raw/*73.14*/("""
        """),format.raw/*74.9*/("""}"""),format.raw/*74.10*/(""",

        yAxis: ["""),format.raw/*76.17*/("""{"""),format.raw/*76.18*/(""" """),format.raw/*76.19*/("""// left y axis
            title: """),format.raw/*77.20*/("""{"""),format.raw/*77.21*/("""
                """),format.raw/*78.17*/("""text: null
            """),format.raw/*79.13*/("""}"""),format.raw/*79.14*/(""",
            labels: """),format.raw/*80.21*/("""{"""),format.raw/*80.22*/("""
                """),format.raw/*81.17*/("""align: 'left',
                x: 3,
                y: 16,
                format: '"""),format.raw/*84.26*/("""{"""),format.raw/*84.27*/("""value:.,0f"""),format.raw/*84.37*/("""}"""),format.raw/*84.38*/("""'
            """),format.raw/*85.13*/("""}"""),format.raw/*85.14*/(""",
            showFirstLabel: false
        """),format.raw/*87.9*/("""}"""),format.raw/*87.10*/(""", """),format.raw/*87.12*/("""{"""),format.raw/*87.13*/(""" """),format.raw/*87.14*/("""// right y axis
            linkedTo: 0,
            gridLineWidth: 0,
            opposite: true,
            title: """),format.raw/*91.20*/("""{"""),format.raw/*91.21*/("""
                """),format.raw/*92.17*/("""text: null
            """),format.raw/*93.13*/("""}"""),format.raw/*93.14*/(""",
            labels: """),format.raw/*94.21*/("""{"""),format.raw/*94.22*/("""
                """),format.raw/*95.17*/("""align: 'right',
                x: -3,
                y: 16,
                format: '"""),format.raw/*98.26*/("""{"""),format.raw/*98.27*/("""value:.,0f"""),format.raw/*98.37*/("""}"""),format.raw/*98.38*/("""'
            """),format.raw/*99.13*/("""}"""),format.raw/*99.14*/(""",
            showFirstLabel: false
        """),format.raw/*101.9*/("""}"""),format.raw/*101.10*/("""],

        legend: """),format.raw/*103.17*/("""{"""),format.raw/*103.18*/("""
            """),format.raw/*104.13*/("""align: 'left',
            verticalAlign: 'top',
            borderWidth: 0
        """),format.raw/*107.9*/("""}"""),format.raw/*107.10*/(""",

        tooltip: """),format.raw/*109.18*/("""{"""),format.raw/*109.19*/("""
            """),format.raw/*110.13*/("""shared: true,
            crosshairs: true
        """),format.raw/*112.9*/("""}"""),format.raw/*112.10*/(""",

        plotOptions: """),format.raw/*114.22*/("""{"""),format.raw/*114.23*/("""
            """),format.raw/*115.13*/("""series: """),format.raw/*115.21*/("""{"""),format.raw/*115.22*/("""
                """),format.raw/*116.17*/("""cursor: 'pointer',
                className: 'popup-on-click',
                marker: """),format.raw/*118.25*/("""{"""),format.raw/*118.26*/("""
                    """),format.raw/*119.21*/("""lineWidth: 1
                """),format.raw/*120.17*/("""}"""),format.raw/*120.18*/("""
            """),format.raw/*121.13*/("""}"""),format.raw/*121.14*/("""
        """),format.raw/*122.9*/("""}"""),format.raw/*122.10*/(""",

        series: ["""),format.raw/*124.18*/("""{"""),format.raw/*124.19*/("""
            """),format.raw/*125.13*/("""name: 'All sessions',
            lineWidth: 4,
            marker: """),format.raw/*127.21*/("""{"""),format.raw/*127.22*/("""
                """),format.raw/*128.17*/("""radius: 4
            """),format.raw/*129.13*/("""}"""),format.raw/*129.14*/("""
        """),format.raw/*130.9*/("""}"""),format.raw/*130.10*/(""", """),format.raw/*130.12*/("""{"""),format.raw/*130.13*/("""
            """),format.raw/*131.13*/("""name: 'New users'
        """),format.raw/*132.9*/("""}"""),format.raw/*132.10*/("""]
    """),format.raw/*133.5*/("""}"""),format.raw/*133.6*/(""");

</script>
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
                  HASH: c14bc31e09ae76225a990fcd343fa7a29a0f42d6
                  MATRIX: 811->2|1081->245|1109->246|1145->256|1240->324|1268->325|1309->339|1474->477|1502->478|1533->482|1561->483|1598->493|1626->494|1659->500|1687->501|1715->502|1743->503|1771->504|1814->520|1842->521|2225->876|2254->877|2300->895|2463->1030|2492->1031|2550->1061|2780->1263|2809->1264|2869->1296|2898->1297|2956->1327|3024->1367|3053->1368|3133->1420|3162->1421|3220->1451|3312->1515|3341->1516|3417->1564|3446->1565|3475->1566|3508->1571|3537->1572|3583->1590|3655->1634|3684->1635|3713->1636|3753->1648|3782->1649|3846->1685|3875->1686|3904->1687|3955->1710|3984->1711|4014->1713|4043->1714|4072->1715|4114->1729|4143->1730|4187->1746|4216->1747|4253->1757|4282->1758|4315->1764|4343->1765|4487->1881|4516->1882|4553->1892|4588->1899|4617->1900|4659->1914|4707->1934|4736->1935|4782->1953|4837->1980|4866->1981|4903->1991|4932->1992|4979->2011|5008->2012|5050->2026|5166->2114|5195->2115|5241->2133|5317->2181|5346->2182|5383->2192|5412->2193|5460->2213|5489->2214|5531->2228|5612->2282|5641->2283|5692->2306|5721->2307|5763->2321|5832->2363|5861->2364|5909->2384|5938->2385|5980->2399|6135->2526|6164->2527|6210->2545|6312->2619|6341->2620|6378->2630|6407->2631|6456->2652|6485->2653|6514->2654|6577->2689|6606->2690|6652->2708|6704->2732|6733->2733|6784->2756|6813->2757|6859->2775|6975->2863|7004->2864|7042->2874|7071->2875|7114->2890|7143->2891|7216->2937|7245->2938|7275->2940|7304->2941|7333->2942|7483->3064|7512->3065|7558->3083|7610->3107|7639->3108|7690->3131|7719->3132|7765->3150|7883->3240|7912->3241|7950->3251|7979->3252|8022->3267|8051->3268|8125->3314|8155->3315|8206->3337|8236->3338|8279->3352|8394->3439|8424->3440|8475->3462|8505->3463|8548->3477|8629->3530|8659->3531|8714->3557|8744->3558|8787->3572|8824->3580|8854->3581|8901->3599|9020->3689|9050->3690|9101->3712|9160->3742|9190->3743|9233->3757|9263->3758|9301->3768|9331->3769|9382->3791|9412->3792|9455->3806|9554->3876|9584->3877|9631->3895|9683->3918|9713->3919|9751->3929|9781->3930|9812->3932|9842->3933|9885->3947|9940->3974|9970->3975|10005->3982|10034->3983
                  LINES: 26->2|29->5|29->5|30->6|30->6|30->6|31->7|33->9|33->9|33->9|33->9|33->9|33->9|33->9|33->9|33->9|33->9|33->9|33->9|33->9|40->16|40->16|41->17|43->19|43->19|44->20|48->24|48->24|49->25|49->25|50->26|51->27|51->27|52->28|52->28|53->29|54->30|54->30|56->32|56->32|56->32|56->32|56->32|57->33|58->34|58->34|58->34|58->34|58->34|59->35|59->35|59->35|59->35|59->35|59->35|59->35|59->35|59->35|59->35|60->36|60->36|61->37|61->37|62->38|62->38|66->42|66->42|67->43|67->43|67->43|68->44|68->44|68->44|69->45|70->46|70->46|71->47|71->47|73->49|73->49|74->50|76->52|76->52|77->53|78->54|78->54|79->55|79->55|81->57|81->57|82->58|83->59|83->59|85->61|85->61|86->62|87->63|87->63|89->65|89->65|90->66|93->69|93->69|94->70|97->73|97->73|98->74|98->74|100->76|100->76|100->76|101->77|101->77|102->78|103->79|103->79|104->80|104->80|105->81|108->84|108->84|108->84|108->84|109->85|109->85|111->87|111->87|111->87|111->87|111->87|115->91|115->91|116->92|117->93|117->93|118->94|118->94|119->95|122->98|122->98|122->98|122->98|123->99|123->99|125->101|125->101|127->103|127->103|128->104|131->107|131->107|133->109|133->109|134->110|136->112|136->112|138->114|138->114|139->115|139->115|139->115|140->116|142->118|142->118|143->119|144->120|144->120|145->121|145->121|146->122|146->122|148->124|148->124|149->125|151->127|151->127|152->128|153->129|153->129|154->130|154->130|154->130|154->130|155->131|156->132|156->132|157->133|157->133
                  -- GENERATED --
              */
          