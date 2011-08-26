/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
 */
package hudson.plugins.simpleupdatesite.model;

import hudson.model.UpdateSite.Plugin;
import hudson.plugins.simpleupdatesite.Constant;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Plugin update info model
 * 
 * @author JunHo Yoon
 */
public class PluginEntry {
	private String name;
	private String url;
	private String version;
	private String description;
	private String title;
	private boolean update;
	private String requiredCore;
	private boolean forNewerHudson;
	private boolean installed;
	private static final String PLUGIN_TEMPLATE = "plugin.%s." + Constant.SIMPLE_UPDATESITE_ID;

	public PluginEntry() {
	}

	public PluginEntry(Plugin plugin, boolean hasUpdate) {
		this.name = plugin.name;
		this.setTitle(plugin.title);
		this.version = plugin.version;
		this.description = StringEscapeUtils.unescapeHtml(plugin.excerpt);
		this.url = plugin.wiki;
		this.update = hasUpdate;
		this.requiredCore = plugin.requiredCore;
		this.installed = false;
		this.setForNewerHudson(plugin.isForNewerHudson());
	}

	public PluginEntry(Plugin plugin, boolean hasUpdate, boolean installed) {
		this(plugin, hasUpdate);
		this.installed = installed;
	}

	public String getKey() {
		return String.format(PluginEntry.PLUGIN_TEMPLATE, this.name);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return this.version;
	}

	public String getNormalizedVersion() {
		if (StringUtils.isNotBlank(this.version)) {
			return this.version.replace("-SNAPSHOT", "");
		}
		return this.version;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isUpdate() {
		return this.update;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setRequiredCore(String requiredCore) {
		this.requiredCore = requiredCore;
	}

	public String getRequiredCore() {
		return this.requiredCore;
	}

	public String getTooltip() {
		return "";
	}

	public void setForNewerHudson(boolean forNewerHudson) {
		this.forNewerHudson = forNewerHudson;
	}

	public boolean isForNewerHudson() {
		return this.forNewerHudson;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	public boolean isInstalled() {
		return this.installed;
	}
}
