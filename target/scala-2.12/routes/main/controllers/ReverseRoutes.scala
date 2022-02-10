// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseProva(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def postMinAttribute(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "minAttribute")
    }
  
    // @LINE:17
    def postTime(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "time")
    }
  
    // @LINE:12
    def postMediaAnnuale(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mediaAnnuale")
    }
  
    // @LINE:18
    def getTemp(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "temp")
    }
  
    // @LINE:14
    def getPredictTemp(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "predictTemp")
    }
  
    // @LINE:8
    def getMinAttribute(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "minAttribute")
    }
  
    // @LINE:11
    def getMediaAnnuale(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mediaAnnuale")
    }
  
    // @LINE:10
    def postMeteoTemporale(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "meteoTempo")
    }
  
    // @LINE:9
    def getMeteoTemporale(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "meteoTempo")
    }
  
    // @LINE:13
    def getKMeans(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "kMeans")
    }
  
    // @LINE:15
    def postPredictTemp(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "predictTemp")
    }
  
    // @LINE:6
    def home(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:16
    def getTime(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "time")
    }
  
  }

  // @LINE:21
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:21
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
