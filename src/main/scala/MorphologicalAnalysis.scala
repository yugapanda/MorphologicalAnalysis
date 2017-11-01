/**
  * Created by yuga on 2017/10/31.
  */

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token
import skinny.micro.WebServer



object MorphologicalAnalysis extends App{


  def init: Map[String,Int] = {

    val br01 = new BufferedReader(new InputStreamReader(new FileInputStream("assets/pn01.txt"), "UTF-8"))
    val pn01 = try {
      Iterator.continually(br01.readLine()).takeWhile(_ != null).toList
    } finally {
      br01.close()
    }


    val br02 = new BufferedReader(new InputStreamReader(new FileInputStream("assets/pn02.txt"), "UTF-8"))
    val pn02 = try {
      Iterator.continually(br02.readLine()).takeWhile(_ != null).toList
    } finally {
      br02.close()
    }


    def judgeDict01(word: String): Int = word match {
      case x if x == "e" => 0
      case x if x == "n" => -1
      case x if x == "p" => 1
      case _ => 0
    }

    def judgeDict02(word: String): Int = word match {
      case x if x == "ネガ（経験）" || x == "ネガ（評価）" => -1
      case x if x == "ポジ（評価）" || x == "ポジ（経験）" => 1
      case _ => 0
    }


    val dict01 = pn01.map(x => x.split('\t'))
      .map(x => Map(x(0) -> judgeDict01(x(1))))
      .foldLeft(Map[String, Int]())((acc, x) => acc ++ x)

    val dict02 = pn02.map(x => x.split('\t'))
      .filter(x => x.length > 1)
      .map(x => Map(x(1) -> judgeDict02(x(0))))
      .foldLeft(Map[String, Int]())((acc, x) => acc ++ x)

    pn02.map(x => x.split('\t')).filter(x => x.length <= 1).foreach(x => println(x(0)))



    dict01 ++ dict02
  }

  val dict = init

  WebServer.mount(MorphologicalAPI).port(4567).start()

  def getBasicForm(sentence: String): Array[String] = {

    val tokenizer = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build

    val tokens = tokenizer.tokenize(sentence).toArray

    tokens.map(x => x.asInstanceOf[Token].getBaseForm)

  }

  def getScore(tokens: Array[String]): Double = tokens.filter(dict.contains).map(x => dict(x)).sum


}
