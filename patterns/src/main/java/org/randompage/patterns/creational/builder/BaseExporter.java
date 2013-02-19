package org.randompage.patterns.creational.builder;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.net.URL;

/**
 * @author lt
 *         Date: Jan 13, 2008
 */
abstract class BaseExporter {
    protected File xslFile;

    protected Transformer getTransformer() {
        // prepare xml transformdf
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer(new StreamSource(xslFile));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return transformer;
    }
}
