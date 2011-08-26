package hudson.plugins.simpleupdatesite;

import hudson.model.Hudson;
import hudson.plugins.simpleupdatesite.model.RssEntry;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sun.syndication.io.FeedException;

import static org.hamcrest.CoreMatchers.notNullValue;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Hudson.class })
@PowerMockIgnore("javax.*")
public class RSSTest {
	@Mock
	Hudson hudson;

	@Test
	public void testRSS() throws IllegalArgumentException, FeedException, IOException, InterruptedException {
		when(hudson.getRootUrl()).thenReturn("/wow/");
		PowerMockito.mockStatic(Hudson.class);
		when(Hudson.getInstance()).thenReturn(hudson);

		SimpleUpdateSitePlugIn widget = new SimpleUpdateSitePlugIn() {
			@Override
			public String getNewsRssUrl() {
				return "http://www.sten.or.kr/bbs/rss.php?bo_table=journal";
			}
		};
		for (RssEntry entry : widget.getRssEntryReference()) {
			assertThat(entry, notNullValue());
		}
	}

}
