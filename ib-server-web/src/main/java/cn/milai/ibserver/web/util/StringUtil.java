package cn.milai.ibserver.web.util;

import java.util.Random;

public class StringUtil {

	private static final char[] chs = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final Random rand = new Random();
	
	
	public static String random(int len) {
		char[] arr = new char[len];
		for(int i=0; i<arr.length; i++) {
			arr[i] = chs[rand.nextInt(chs.length)];
		}
		return new String(arr);
	}
	
}
