package co.tackmann.jpa.domain.products;

import javax.persistence.Column;
import javax.persistence.Entity;

import co.tackmann.jpa.domain.Product;

@Entity
public class Music extends Product {
	private static final long serialVersionUID = -7667588122293716765L;

	@Column(nullable = false)
	private String artist;

	@Column(nullable = false)
	private int tracks;

	@Column(nullable = false)
	private int playtime;

    public String getArtist() {
		return artist;
	}

    public int getPlaytime() {
		return playtime;
	}

	public int getTracks() {
		return tracks;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	public void setTracks(int tracks) {
		this.tracks = tracks;
	}
}
