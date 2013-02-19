package org.randompage.patterns.creational.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Lars Tackmann
 *         Date: Jan 12, 2008
 */
public interface Exporter {
    void setXSL(File xslFile);
    void setTarget(File target);
    void setSource(File source);
    File construct();
}
