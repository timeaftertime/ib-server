package cn.milai.ibserver.web.util;

import java.util.Random;

public class RandomUtil {

	private static final char[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final Random rand = new Random();
	private static final int DEFAULT_LENGTH = 16;

	public static String randString() {
		return randString(DEFAULT_LENGTH);
	}

	public static String randString(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(CHARS[rand.nextInt(CHARS.length)]);
		}
		return sb.toString();
	}

}
