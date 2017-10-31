import skinny.micro.{WebApp, WebServer}
import skinny.util._
import MorphologicalAnalysis._
import scala.util.parsing.json._

/**
  * Created by yuga on 2017/10/31.
  */
object MorphologicalAPI extends WebApp {

  get("/basic") {

    JSONObject(Map("sentence" -> getBasicForm(params.getOrElse("sentence", "")))).toString

  }

  get("/analysis") {

    JSONObject(Map("sentence" -> getBasicForm(params.getOrElse("sentence", "")))).toString

  }

}
