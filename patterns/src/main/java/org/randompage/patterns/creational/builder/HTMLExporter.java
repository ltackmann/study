package org.randompage.patterns.creational.builder;

import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Lars Tackmann
 *         Date: Jan 12, 2008
 */
public class HTMLExporter extends BaseExporter implements Exporter {
    public void setXSL(File xslFile) {
        super.xslFile = xslFile;
    }

    public void save(InputStream in, File target) throws FileNotFoundException {
        // use HTML
        Transformer transformer = getTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "html");

        // read source and prepare target
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(target));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // do transform
        try {
            transformer.transform(new StreamSource(in), new StreamResult(out));
        } catch (TransformerException e) {
            e.printStackTrace();  
        }
    }
}
