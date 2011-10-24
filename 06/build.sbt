// project setup
name := "hack-assembler"

organization := "com.github.ltackmann"

version := "0.1"

// scala setup
sbtVersion := "0.11.0"

scalaVersion := "2.9.1"

// use another source directory
scalaSource in Compile <<= baseDirectory(_ / "src") 