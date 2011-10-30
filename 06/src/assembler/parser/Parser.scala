package assembler.parser

import java.io.File
import scala.io._

class Parser(sourceFile: File) {
  var sourceLines: Seq[String] = Seq()

  private def parse() = {
    // read file into sequence
    val asmSource = Source.fromFile(sourceFile)

    // TODO remove empty lines
    val commentRegex = "(^//.*)".r
    val emptyLineRegex = "(^$)".r
    
    for (line <- asmSource.getLines) {
      line match {
        case commentRegex(comment) => println("comment: " + comment)
        case emptyLineRegex(emptyLine) => println("got a empty line")
        case code => println("got code" + code)
      }
    }
  }
}

object Parser {
  def main(args: Array[String]): Unit = {
    // TODO grab from main args 
    val filePath = """test/max/MaxL.asm"""
    val sourceFile = new File(filePath)
    if (!sourceFile.exists()) {
      throw new java.io.FileNotFoundException(filePath)
    }

    val parser = new Parser(sourceFile)
    parser.parse()
  }
}
