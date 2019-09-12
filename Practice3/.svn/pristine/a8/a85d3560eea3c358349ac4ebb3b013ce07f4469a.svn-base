package ua.nure.storozhuk.practice3;

import java.security.*;

public class Part4 {
	public static String hash(String input, String algorithm) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(input.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static void main(String...strings) {
		if(strings.length == 2) {
			System.out.println(hash(strings[0], strings[1]));
		}
		System.out.println(hash("asdf", "MD5"));
		System.out.println(hash("asdf", "SHA-256"));
	}

}
