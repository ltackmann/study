package assembler

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import java.io.File

@RunWith(classOf[JUnitRunner])
class ParserTest extends Spec with ShouldMatchers {
  describe("Parser") {

    it("should parse a file") {
      val sourceFile = new File("""src/test/resources/max/MaxL.asm""")
      val parser = new Parser(sourceFile)
      parser.parse()
      parser.sourceLines.length should (be > 0)
    }
  }
}