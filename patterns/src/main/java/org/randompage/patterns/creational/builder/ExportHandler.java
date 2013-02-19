package org.randompage.patterns.creational.builder;

import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.File;

// TODO: UML and note about better code with command pattern for report type
/**
 * @author Lars Tackmann
 *         Date: Jan 12, 2008
 */
public class ExportHandler {
    private File sourceFile;
    private File targetFile;
    private File xslFile;

    public void export(Exporter exporter) {
        exporter.setXSL(xslFile);
        exporter.setSource(sourceFile);
        exporter.setTarget(targetFile);
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public File getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(File targetFile) {
        this.targetFile = targetFile;
    }

    public File getXslFile() {
        return xslFile;
    }

    public void setXslFile(File xslFile) {
        this.xslFile = xslFile;
    }
}
