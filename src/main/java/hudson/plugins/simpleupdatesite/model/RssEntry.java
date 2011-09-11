/*
 * The MIT License
 *
 * Copyright (c) 2004-, all the contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
