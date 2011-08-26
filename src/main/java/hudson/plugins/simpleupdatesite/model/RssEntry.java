/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
 */
package hudson.plugins.simpleupdatesite.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * Rss entry model
 * 
 * @author JunHo Yoon
 */
public class RssEntry {
	private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("MM/dd");
	private String title;
	private String url;
	private Date updatedDate;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Check if the entry is new
	 * 
	 * @return Return true if the news is updated after 2 week ago.
	 */
	public boolean isNew() {
		Date previousDate = DateUtils.addDays(new Date(), -14);
		return previousDate.before(getUpdatedDate());
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public String getFormatedDate() {
		return RssEntry.SIMPLEDATEFORMAT.format(getUpdatedDate());
	}

	@Override
	public String toString() {
		return "RssEntry [title=" + getTitle() + ", updatedDate=" + getUpdatedDate() + ", url=" + getUrl() + "]";
	}

}
