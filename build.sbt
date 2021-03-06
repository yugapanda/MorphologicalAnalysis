lazy val root = (project in file("."))
  .settings(
    name := "morphological-analysis-np",
    version := "0.1",
    scalaVersion := "2.12.2",
    resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
    resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika",
    dependencyOverrides := Set("org.scala-lang" %  "scala-compiler" % scalaVersion.value), // for Scalate
    libraryDependencies ++= Seq(
      "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
      "org.skinny-framework" %% "skinny-micro"         % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-server"  % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-jackson" % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-scalate" % skinnyMicroVersion % Compile,
      "org.eclipse.jetty"    %  "jetty-webapp"         % "9.3.19.v20170502" % "container",
      "org.skinny-framework" %% "skinny-micro-test"    % skinnyMicroVersion % Test
    ),
    mainClass in Compile := Some("skinny.standalone.JettyLauncher"),
    // add src/main/webapp to unmanaged resources for sbt-start-script
    unmanagedResourceDirectories in Compile <++= baseDirectory { base => 
      sys.env.get("LOCAL_DEV").map(_ => Seq.empty).getOrElse(Seq(base / "src/main/webapp"))
    },
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
  )
  .settings(servletSettings)
  .settings(scalariformSettings)
  .settings(com.typesafe.sbt.SbtStartScript.startScriptForClassesSettings)

lazy val skinnyMicroVersion = "1.2.+"
