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
package hudson.plugins.simpleupdatesite;

import hudson.Extension;
import hudson.model.Hudson;
import hudson.plugins.simpleupdatesite.model.PluginEntry;
import hudson.plugins.simpleupdatesite.model.RssEntry;
import hudson.widgets.Widget;

import java.util.List;

/**
 * Widget plugin which is shown in the left-bottom side of the top page.
 * 
 * @author JunHo Yoon
 */
@Extension
public class SimpleUpdateSiteWidget extends Widget {

	public SimpleUpdateSiteWidget() {
	}

	public boolean isRestartNecessary() {
		return Hudson.getInstance().getPluginManager().isPluginUploaded();
	}

	public List<PluginEntry> getPluginEntries() {
		return getPlugin().getShownPluginEntryReference();
	}

	protected SimpleUpdateSitePlugIn getPlugin() {
		return Hudson.getInstance().getPlugin(SimpleUpdateSitePlugIn.class);
	}

	public List<RssEntry> getRssEntries() {
		return getPlugin().getRssEntryReference();
	}

	public boolean isNewsRssSiteValid() {
		return getPlugin().isNewsRssSiteValid();
	}

	public String getSupportUrl() {
		return getPlugin().getSupportUrl();
	}

	public String getNewsRssSiteFailCause() {
		return getPlugin().getNewsRssSiteFailCause()
			+ String.format("<a href='%sconfigure'>Jenkins configuration</a>", Hudson.getInstance().getRootUrlFromRequest());
	}

	public boolean isUpdateSiteValid() {
		if (isRestartNecessary()) {
			return false;
		}
		return getPlugin().isUpdateSiteValid();
	}

	public String getUpdateSiteFailCause() {
		if (isRestartNecessary()) {
			return String.format("<b><a href='%srestart'>Restart Jenkins</a> to apply updated plugins</b>", Hudson.getInstance()
				.getRootUrlFromRequest());
		}
		return getPlugin().getUpdateSiteFailCause()
			+ String.format("<a href='%sconfigure'>Jenkins configuration</a>", Hudson.getInstance().getRootUrlFromRequest());
	}

	public boolean hasUpdatedPluginEntries() {
		return getPluginEntries().size() != 0;
	}
}
