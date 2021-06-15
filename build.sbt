name := "test1"

version := "0.1"

scalaVersion := "2.12.10"

lazy val akkaVersion     = "2.5.23"
lazy val akkaHttpVersion = "10.1.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-actor"      % akkaVersion,
  "com.typesafe.akka"  %% "akka-slf4j"      % akkaVersion,
  "com.typesafe.akka"  %% "akka-http"       % akkaHttpVersion,
  "de.heikoseeberger"  %% "akka-http-json4s"% "1.20.1",
  "com.typesafe.akka"  %% "akka-stream"     % akkaVersion,
)