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

  
    // @LINE:6
    def p1(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:7
    def p2(): Call = {
    
      () match {
      
        // @LINE:7
        case ()  =>
          
          Call("POST", _prefix + { _defaultPrefix } + "p2")
      
      }
    
    }
  
    // @LINE:9
    def p4(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "p4")
    }
  
  }

  // @LINE:16
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
