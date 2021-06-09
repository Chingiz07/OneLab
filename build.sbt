name := "test1"

version := "0.1"

scalaVersion := "2.12.10"

lazy val akkaVersion     = "2.5.23"
lazy val json4sVersion   = "3.5.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-actor"      % akkaVersion,
  "com.typesafe.akka"  %% "akka-slf4j"      % akkaVersion,

  "org.json4s"         %% "json4s-core"     % json4sVersion,
  "org.json4s"         %% "json4s-jackson"  % json4sVersion,
)