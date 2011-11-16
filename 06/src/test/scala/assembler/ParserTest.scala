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
      val parser = new Parser("""src/test/resources/max/MaxL.asm""")
      parser.parse()
      parser.sourceLines.length should (be > 0)
    }
    it("should fail when file does not exists") {
      evaluating {
        new Parser("""this/file/does/not/exists.txt""")
      } should produce[java.io.FileNotFoundException]
    }
  }
}