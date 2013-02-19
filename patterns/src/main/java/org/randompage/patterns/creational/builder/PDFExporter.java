package org.randompage.patterns.creational.builder;

import org.apache.fop.apps.*;

import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
 * @author Lars Tackmann
 *         Date: Jan 12, 2008
 */
public class PDFExporter extends BaseExporter implements Exporter {
    public void setXSL(File xslFile) {
        super.xslFile = xslFile;
    }

    public void save(InputStream in, File target) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream(target);

        FopFactory fopFactory = FopFactory.newInstance();
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
        } catch (FOPException e) {
            e.printStackTrace();
        }

        //Result res = new SAXResult(fop.getDefaultHandler());

        //transformer.transform(src, res);

        //sendPDF(out.toByteArray(), response);

    }


}
