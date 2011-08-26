/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
 */
package hudson.plugins.simpleupdatesite;

import hudson.Extension;
import hudson.model.AsyncPeriodicWork;
import hudson.model.TaskListener;
import hudson.model.Hudson;

import java.io.IOException;

/**
 * Class for async peridocally download update site content.
 * 
 * @author JunHo Yoon
 */
@Extension
public class AsyncSimplUpdateSiteDownloader extends AsyncPeriodicWork {
	public AsyncSimplUpdateSiteDownloader() {
		super(AsyncSimplUpdateSiteDownloader.class.getName());
	}

	@Override
	public void execute(TaskListener listener) throws IOException, InterruptedException {
		SimpleUpdateSitePlugIn plugin = getPlugin();
		plugin.downloadUpdateSiteJSON();
	}

	@Override
	public long getRecurrencePeriod() {
		return Constant.UPDATESITE_REFRESH_RATE;
	}

	@Override
	public long getInitialDelay() {
		return 1000;
	}

	protected SimpleUpdateSitePlugIn getPlugin() {
		return Hudson.getInstance().getPlugin(SimpleUpdateSitePlugIn.class);
	}

}
