package org.randompage.samples.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Generic product class
 *
 * @author Lars Tackmann
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PRODUCTS", uniqueConstraints = @UniqueConstraint(columnNames = {"PRODUCT_NAME", "SUPPLIERS_ID"}))
public abstract class Product implements Serializable {
    public static final long serialVersionUID = 8220015462396371247L;

    @Id
    @GeneratedValue
    private long id;

    @Lob
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE", nullable = false)
    private Date releaseDate;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "SUPPLIERS_ID", nullable = false)
    private Supplier supplier;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRODUCT_HAS_CATEGORIES",
            joinColumns = @JoinColumn(name = "PRODUCTS_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORIES_ID"))
    private Set<Category> categories = new HashSet<Category>();

    public Product() {
        releaseDate = new Date();
    }

    protected Product(BigDecimal unitPrice, String productName, Supplier supplier) {
        this();
        this.unitPrice = unitPrice;
        this.productName = productName;
        this.supplier = supplier;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public String getProductName() {
        return productName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}