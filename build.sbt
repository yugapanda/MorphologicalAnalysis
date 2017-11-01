name := "MorphologicalAnalysis"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq("org.atilika.kuromoji" % "kuromoji" % "0.7.7",
  "org.skinny-framework" %% "skinny-micro-server" % "1.2.7"

)

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

