package compiler;

import javax.tools.*;

public class SystemCompilerTest {
    public static void main(String[] args) {
        String fileToCompile = "test" + java.io.File.separator + "C.java";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, fileToCompile);
        if (compilationResult == 0) {
            System.out.println("Compilation is successful");
        } else {
            System.out.println("Compilation Failed");
        }
    }
}