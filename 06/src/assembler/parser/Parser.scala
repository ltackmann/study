package org.randompage.tecs

import java.io.File
import scala.io.{Source=>IO}

object Parser {
  def main(args : Array[String]) : Unit = {
    // TODO grab from main args 
    val filePath = """test/max/MaxL.asm"""
    val sourceFile = new File(filePath)
    if(!sourceFile.exists()) {
      // TODO how to raise exceptions in scala
      // TODO add logger
      return
    } 
    parse(sourceFile)
  }
  
  private def parse(sourceFile: File) = {
    // read file into sequence
    val asmSource = IO.fromFile(sourceFile)
    // TODO fix that fromFile does not handle new lines correctly - add to scala samples
    // http://stackoverflow.com/questions/1284423/read-entire-file-in-scala
    // TODO is there a scalaz thing for this
    asmSource.foreach(println)
  }
}
