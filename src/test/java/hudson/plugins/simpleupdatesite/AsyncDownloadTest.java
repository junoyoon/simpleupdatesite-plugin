package hudson.plugins.simpleupdatesite;

import hudson.model.Hudson;

import java.io.File;
import java.io.IOException;

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
@PrepareForTest( { Hudson.class })
@PowerMockIgnore("javax.*")
public class AsyncDownloadTest {
	@Mock
	Hudson hudson;

	@Test
	public void testJSONStripOut() throws IOException {
		SimpleUpdateSitePlugIn plugin = new SimpleUpdateSitePlugIn();
		assertThat(plugin.stripOutCallBackMethod("updateCenter.post(HELLO);"), is("HELLO"));
		assertThat(plugin.stripOutCallBackMethod("  updateCenter.post(HELLO );  "), is("HELLO "));
		assertThat(plugin.stripOutCallBackMethod("  updateCenter.post({(HELLO);} );  "), is("{(HELLO);} "));
		assertThat(plugin.stripOutCallBackMethod("HELLO);"), is("HELLO);"));
	}

	@Test
	public void testAsyncSimpleUpdateSiteDownloder() throws IOException, InterruptedException {
		when(hudson.getRootDir()).thenReturn(new File("./src/test/resources"));
		when(hudson.getPlugin(SimpleUpdateSitePlugIn.class)).thenReturn(new SimpleUpdateSitePlugIn());

		PowerMockito.mockStatic(Hudson.class);
		when(Hudson.getInstance()).thenReturn(hudson);

		AsyncSimplUpdateSiteDownloader downloader = new AsyncSimplUpdateSiteDownloader();
		downloader.doRun();
		downloader.execute(null);
	}
}
