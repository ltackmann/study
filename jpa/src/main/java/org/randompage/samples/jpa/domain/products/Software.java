package org.randompage.samples.jpa.domain.products;

import org.randompage.samples.jpa.domain.Product;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Software extends Product {
    private static final long serialVersionUID = 8639172199671029183L;

    private String vendor;

    @Column(nullable = false)
    private String version;

    @Column(name = "DOWNLOAD_URL")
    private String downloadUrl;

    @Column(name = "MANUAL_URL")
    private String manualUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getManualUrl() {
        return manualUrl;
    }

    public String getVersion() {
        return version;
    }


    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setManualUrl(String manualUrl) {
        this.manualUrl = manualUrl;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
