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
  
    // @LINE:8
    def getMinAttribute: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.getMinAttribute",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "minAttribute"})
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
  
    // @LINE:6
    def home: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Prova.home",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
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
