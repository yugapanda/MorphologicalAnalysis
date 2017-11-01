import skinny.micro.{WebApp, WebServer}
import skinny.util._
import MorphologicalAnalysis._
import scala.util.parsing.json._

import skinny.micro._
import skinny.micro.contrib.ScalateSupport
import skinny.micro.contrib.jackson.JSONSupport
import scala.util._


/**
  * Created by yuga on 2017/10/31.
  */
object App extends WebApp {

  error {
    case e =>
      e.printStackTrace
      throw e
  }

  get("/") {
    "home!"
  }


  get("/basic") {

    JSONObject(Map("sentence" -> getBasicForm(params.getOrElse("sentence", "")).toList))

  }

  get("/analysis") {

    JSONObject(Map("score" -> getScore(getBasicForm(params.getOrElse("sentence", "")))))

  }

}
