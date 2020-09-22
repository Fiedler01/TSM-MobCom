package com.example.android.rssfeedlibrary;

public class RssEntry {
	private String subtitle;
	private String summary;
	private String link;
	private String title;

	public RssEntry() {
	}

	public RssEntry(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "RssEntry [title=" + title + "]";
	}

}