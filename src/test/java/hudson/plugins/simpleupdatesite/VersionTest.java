package hudson.plugins.simpleupdatesite;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class VersionTest {
	@Test
	public void testVersion1() {
		SimpleUpdateSite site = new SimpleUpdateSite("ww");
		assertThat(site.isNewerPlugin("1.0.3", "1.0.2-SNAPSHOT (private-10/06/2010 11:57-nhn)"), is(true));
		assertThat(site.isNewerPlugin("1.0.2-SNAPSHOT", "1.0.2-SNAPSHOT (private-10/06/2010 11:57-nhn)"), is(false));
	}
}
