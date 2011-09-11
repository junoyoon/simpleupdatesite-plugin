package hudson.plugins.simpleupdatesite;

import hudson.model.Hudson;
import hudson.model.UpdateSite;
import hudson.model.UpdateSite.Plugin;
import hudson.plugins.simpleupdatesite.Constant;
import hudson.plugins.simpleupdatesite.SimpleUpdateSite;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Hudson.class })
@PowerMockIgnore("javax.*")
public class UpdateSiteTest {
	@Mock
	Hudson hudson;

	@Test
	@Ignore
	public void testUpdateSiteLoad() throws Exception {
		when(hudson.getRootDir()).thenReturn(new File("./src/test/resources"));
		PowerMockito.mockStatic(Hudson.class);
		when(Hudson.getInstance()).thenReturn(hudson);
		UpdateSite site = new SimpleUpdateSite(Constant.SIMPLE_UPDATESITE_ID,
				"simpleupdatecenter/");
		Plugin plugin = site.getPlugin("covcomplplot");
		System.out.println(plugin);
		assertThat(plugin.compatibleSinceVersion, is("1.321"));
		assertThat(plugin.requiredCore, is("1.325"));
		assertThat(plugin.url, is("http://wow/plugins/covcomplplot.hpi"));
		assertThat(plugin.sourceId, is("simpleupdatesite"));
		assertThat(plugin.title,
				is("Hudson Coverage/Complexity Scatter Plot PlugIn"));
		assertThat(
				plugin.wiki,
				is("http://wiki.hudson-ci.org/display/HUDSON/Coverage+Complexity+Scatter+Plot+PlugIn"));
		assertThat(plugin.version, is("1.0.2-SNAPSHOT"));
		assertThat(plugin.excerpt, is("HELLOWORLD"));
	}
}
