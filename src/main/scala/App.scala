import skinny.micro._
import skinny.micro.contrib.ScalateSupport
import skinny.micro.contrib.jackson.JSONSupport

import scala.util._
import scala.util.parsing.json.JSONObject
import MorphologicalAnalysis._


object App extends AsyncWebApp with JSONSupport with ScalateSupport {

  error {
    case e =>
      e.printStackTrace
      throw e
  }

  get("/") { implicit ctx =>
    contentType = "text/html"
    ssp("/index.ssp")
  }

  get("/basic") { implicit ctx =>

    JSONObject(Map("sentence" -> getBasicForm(params.getOrElse("sentence", "")).toList))

  }

  get("/analysis") {

    JSONObject(Map("score" -> getScore(getBasicForm(params.getOrElse("sentence", "")))))

  }

  post("/minify") { implicit ctx =>
    contentType = "application/json"
    fromJSONString[Map[String, Any]](request.body) match {
      case Success(mapValue) => Ok(toJSONStringAsIs(mapValue))
      case _ => BadRequest(toJSONString(Map("error" -> "JSON parse error")))
    }
  }

  post("/prettify") { implicit ctx =>
    contentType = "application/json"
    fromJSONString[Map[String, Any]](request.body) match {
      case Success(mapValue) => Ok(toPrettyJSONStringAsIs(mapValue))
      case _ => BadRequest(toJSONString(Map("error" -> "JSON parse error")))
    }
  }

}