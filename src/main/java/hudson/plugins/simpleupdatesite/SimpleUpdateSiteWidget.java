/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
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

	public String getNewsRssSiteFailCause() {
		return getPlugin().getNewsRssSiteFailCause()
			+ String.format("<a href='%sconfigure'>Hudson configuration</a>", Hudson.getInstance().getRootUrlFromRequest());
	}

	public boolean isUpdateSiteValid() {
		if (isRestartNecessary()) {
			return false;
		}
		return getPlugin().isUpdateSiteValid();
	}

	public String getUpdateSiteFailCause() {
		if (isRestartNecessary()) {
			return String.format("<b><a href='%srestart'>Restart Hudson</a> to apply updated plugins</b>", Hudson.getInstance()
				.getRootUrlFromRequest());
		}
		return getPlugin().getUpdateSiteFailCause()
			+ String.format("<a href='%sconfigure'>Hudson configuration</a>", Hudson.getInstance().getRootUrlFromRequest());
	}

	public boolean hasUpdatedPluginEntries() {
		return getPluginEntries().size() != 0;
	}
}
