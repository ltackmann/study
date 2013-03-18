package org.randompage.samples.jpa.domain.products;

import org.randompage.samples.jpa.domain.Product;
import org.randompage.samples.jpa.domain.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOKS")
public class Book extends Product {
    private static final long serialVersionUID = -7799360724374323234L;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String author;

    private String subTitle;

    @Column(name = "PAGES", nullable = false)
    private int pageCount;

    public Book() {
    }

    public Book(BigDecimal unitPrice, String productName, Supplier supplier, String author, String isbn, int pageCount) {
        super(unitPrice, productName, supplier);
        this.author = author;
        this.isbn = isbn;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
