// project setup
name := "hack-assembler"

organization := "com.github.ltackmann"

version := "0.1"

// scala setup
sbtVersion := "0.11.0"

scalaVersion := "2.9.1"

// dependencies
libraryDependencies ++= Seq(
	// test
	"org.scalatest" %% "scalatest" % "1.6.1" % "test",
	"junit" % "junit" % "4.10" % "test")

