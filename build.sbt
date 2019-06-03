scalaVersion := "2.12.7"

name := "codingchallenge"
organization := "com.ericrkinzel"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.6.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

mainClass in (Compile, packageBin) := Some("com.ericrkinzel.codingchallenge.Main")