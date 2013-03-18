package test;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCompiler2 {
    public static void main(String[] args) throws Exception {
        String program = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("C.java"));
            String str;
            while ((str = in.readLine()) != null) {
                program += str;
            }
            in.close();
        } catch (IOException e) {
        }

        System.out.println(program);

        //
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Iterable<? extends JavaFileObject> fileObjects;
        fileObjects = getJavaSourceFromString(program);

        compiler.getTask(null, null, null, null, null, fileObjects).call();

        Class<?> clazz = Class.forName("C");
        Method m = clazz.getMethod("main", new Class[]{String[].class});
        Object[] _args = new Object[]{new String[0]};
        m.invoke(null, _args);
    }

    static Iterable<JavaSourceFromString> getJavaSourceFromString(String code) {
        final JavaSourceFromString jsfs;
        jsfs = new JavaSourceFromString("code", code);
        return new Iterable<JavaSourceFromString>() {
            public Iterator<JavaSourceFromString> iterator() {
                return new Iterator<JavaSourceFromString>() {
                    boolean isNext = true;

                    public boolean hasNext() {
                        return isNext;
                    }

                    public JavaSourceFromString next() {
                        if (!isNext)
                            throw new NoSuchElementException();
                        isNext = false;
                        return jsfs;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;

    JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}