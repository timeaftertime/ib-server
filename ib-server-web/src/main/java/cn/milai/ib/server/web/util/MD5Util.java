package cn.milai.ib.server.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static MessageDigest md5;

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String md5(String str) {
		byte[] bs;
		synchronized (md5) {
			md5.update(str.getBytes());
			bs = md5.digest();
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : bs) {
			int ch = (0xf0 & b) >> 4;
			sb.append(numToHex(ch));
			ch = 0xf & b;
			sb.append(numToHex(ch));
		}
		return sb.toString();
	}

	private static char numToHex(int num) {
		if (num > 9)
			return (char) ('a' + num - 10);
		return (char) ('0' + num);
	}

}
