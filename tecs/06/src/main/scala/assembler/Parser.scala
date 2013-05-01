package assembler

import java.io.File
import scala.io._

class Parser(sourceFile: File) {
  def this(sourceFilePath: String) = this(new File(sourceFilePath))
  if (!sourceFile.exists()) {
    throw new java.io.FileNotFoundException(sourceFile.getAbsolutePath())
  }

  var sourceLines: Seq[String] = Seq()

  def parse() = {
    // read file into sequence
    val asmSource = Source.fromFile(sourceFile)
    parseSource(asmSource.getLines)
  }

  def parseSource(lines: Iterator[String]) = {
    val commentRegex = "(^//.*)".r
    val emptyLineRegex = "(^$)".r

    // TODO read in book what the correct term for comments/newlines is (non-term ?)
    // TODO ensure a empty line with spaces is handled correctly (add test for this)
    for (line <- lines) {
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
    // TODO grab file from main args 

  }
}

