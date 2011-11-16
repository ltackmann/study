package assembler.parser

import java.io.File
import scala.io._

class Parser(sourceFile: File) {
  var sourceLines: Seq[String] = Seq()

  private def parse() = {
    // read file into sequence
    val asmSource = Source.fromFile(sourceFile)

    val commentRegex = "(^//.*)".r
    val emptyLineRegex = "(^$)".r

    // TODO move to function and read in book what the correct term for comments/newlines is (non-term ?)
    // TODO also extract it so it can be tested without a file (i.e. scala multiline string) - 
    // TODO ensure a empty line with spaces is handled correctly
    for (line <- asmSource.getLines) {
      line match {
        case commentRegex(comment) => ()
        case emptyLineRegex(emptyLine) => ()
        case code => sourceLines +:= code
      }
    }
    sourceLines.foreach(println)
  }
}

object Parser {
  def main(args: Array[String]): Unit = {
    // TODO grab from main args 
    val filePath = """src/test/resources/max/MaxL.asm"""
    val sourceFile = new File(filePath)
    if (!sourceFile.exists()) {
      throw new java.io.FileNotFoundException(sourceFile.getAbsolutePath())
    }

    val parser = new Parser(sourceFile)
    parser.parse()
  }
}
