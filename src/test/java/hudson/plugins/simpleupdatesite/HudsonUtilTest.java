package hudson.plugins.simpleupdatesite;

import hudson.plugins.simpleupdatesite.util.HudsonUtil;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class HudsonUtilTest {
	@Test
	public void testLengthOnString() {
		assertThat(new HudsonUtil().length("안녕하세요"), is(new Integer(10)));
		assertThat(new HudsonUtil().length("HELLO"), is(new Integer(5)));
		assertThat(new HudsonUtil().length(""), is(new Integer(0)));
	}
}
