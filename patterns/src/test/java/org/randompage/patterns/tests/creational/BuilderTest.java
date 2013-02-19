package org.randompage.patterns.tests.creational;

import org.junit.Test;
import static org.junit.Assert.fail;
import org.randompage.patterns.creational.builder.ExportHandler;
import org.randompage.patterns.creational.builder.Exporter;
import org.randompage.patterns.creational.builder.PDFExporter;
import org.randompage.patterns.creational.builder.HTMLExporter;

import java.io.File;
import java.net.URL;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

/**
 * @author Lars Tackmann
 *         Date: Jan 13, 2008
 */
public class BuilderTest {
    private ExportHandler director;

    private File getFileFromClassLoader(String filename) {
        URL url = ClassLoader.getSystemResource(filename);
        return new File(url.getFile());
    }

    /**
     * Test builder via abstraction
     *
     * @throws Exception
     */
    @Test
    public void builderTest() throws Exception {
        director = new ExportHandler();
        // TODO add jaxp validation        
        director.setSourceFile(getFileFromClassLoader("books.xml"));
        director.setXslFile(getFileFromClassLoader("books.xsl"));

        builderTest(new HTMLExporter(), new File("/tmp/books.html"));
        builderTest(new PDFExporter(), new File("/tmp/books.html"));
    }

    private void builderTest(Exporter builder, File target) {
        // clean up
        if (target.exists()) {
            target.delete();
        }
        director.setTargetFile(target);

        // test builders
        director.export(builder);
        File file = builder.construct();
        assertFileType(file);
    }

    // match file using mime info
    private void assertFileType(File file) {
        MagicMatch matcher = null;
        try {
            matcher = Magic.getMagicMatch(file, false);
        } catch (Exception e) {
            fail("unable to retrieve mime info for: " + file.getAbsolutePath());
        }
        fail("got mime type: " + matcher.getMimeType());
        /*
        String path = target.getAbsolutePath();
        // get file extension
        Pattern pattern = Pattern.compile(".*\\.(\\w+)");
        Matcher matcher = pattern.matcher(path);
        if (!matcher.find()) {
            throw new IllegalArgumentException("output report: " + path
                    + " has no valid extension, supported extensions are: "
                    + extensions.keySet().toString());
        }

        // build report according to its extension
        String extension = matcher.group(1).toUpperCase();
        Exporter exporter = extensions.get(extension);
        if (exporter == null) {
            throw new UnsupportedOperationException("Unsupported report type: " + extension
                    + " valid types are: " + extensions.keySet().toString());

        } */
    }
}