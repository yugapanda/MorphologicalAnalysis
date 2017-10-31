/**
  * Created by yuga on 2017/10/31.
  */

import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token
import skinny.micro.WebServer



object MorphologicalAnalysis extends App{

  WebServer.mount(MorphologicalAPI).port(4567).start()

  def getBasicForm(sentence: String): Array[String] = {

    val tokenizer = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build

    val tokens = tokenizer.tokenize(sentence).toArray

    tokens.map(x => x.asInstanceOf[Token].getBaseForm)

  }

}
