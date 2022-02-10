// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseProva(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def postMinAttribute: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.postMinAttribute",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "minAttribute"})
        }
      """
    )
  
    // @LINE:17
    def postTime: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.postTime",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "time"})
        }
      """
    )
  
    // @LINE:12
    def postMediaAnnuale: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.postMediaAnnuale",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mediaAnnuale"})
        }
      """
    )
  
    // @LINE:18
    def getTemp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getTemp",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "temp"})
        }
      """
    )
  
    // @LINE:14
    def getPredictTemp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getPredictTemp",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "predictTemp"})
        }
      """
    )
  
    // @LINE:8
    def getMinAttribute: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getMinAttribute",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "minAttribute"})
        }
      """
    )
  
    // @LINE:11
    def getMediaAnnuale: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getMediaAnnuale",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mediaAnnuale"})
        }
      """
    )
  
    // @LINE:10
    def postMeteoTemporale: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.postMeteoTemporale",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "meteoTempo"})
        }
      """
    )
  
    // @LINE:9
    def getMeteoTemporale: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getMeteoTemporale",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "meteoTempo"})
        }
      """
    )
  
    // @LINE:13
    def getKMeans: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getKMeans",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kMeans"})
        }
      """
    )
  
    // @LINE:15
    def postPredictTemp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.postPredictTemp",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "predictTemp"})
        }
      """
    )
  
    // @LINE:6
    def home: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.home",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:16
    def getTime: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getTime",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "time"})
        }
      """
    )
  
  }

  // @LINE:21
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:21
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
