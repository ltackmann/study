package org.randompage.bookmarking.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "bookmarks")
@DiscriminatorValue("BOOKMARK")
public class Bookmark extends Taggable {
	private static final long serialVersionUID = 685924272024628437L;

	@Lob
	@Column(nullable = false)
	private String uri;

	@Column(name = "uri_hash", nullable = false)
	private String uriMD5;

	@Column(name = "short_description", nullable = false)
	private String shortDescription;

	@Lob
	@Column(name = "long_description")
	private String longDescription;

	@Column(nullable = false)
	private boolean visible;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false)
	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getUri() {
		return uri;
	}

	public String getUriMD5() {
		return uriMD5;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setUri(String uri) {
		this.uri = uri;
		this.uriMD5 = DigestUtils.md5Hex(uri);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
