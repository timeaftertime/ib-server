package cn.milai.ibserver.web.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cn.milai.ibserver.web.util.MD5Util;

public class MD5UtilTest {

	private static final String[] raws = {"infinity_battle", "ib"};
	private static final String[] expecteds = {
			"91d4f2095dcd43ff0e0e05e9cf9ae95c",
			"a132c5cf175a7333483a34f2cd28110b",};

	@Test
	public void createMd5() {
		for (int i = 0; i < raws.length; i++) {
			assertEquals(expecteds[i], MD5Util.md5(raws[i]));
		}
	}

}
