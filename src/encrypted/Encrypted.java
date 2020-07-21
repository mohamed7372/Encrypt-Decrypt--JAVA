package encrypted;

import java.util.Scanner;

public class Encrypted {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String choix = mode(args);
		int key = key(args);
		String msg = data(args);
		
		if (msg.startsWith("\""))
			msg = msg.substring(1,msg.length() - 1);
		
		switch (choix) {
		case "enc":
			System.out.println(msgCrypt(msg, key).trim());
			break;	
		case "dec":
			System.out.println(msgDecrypt(msg, key).trim());
			break;
		}
	}
	
	static String mode(String[] arr) {
		String md = "enc";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("-mode")) {
				md = arr[++i];
				break;
			}
		}
		return md;
	}
	static int key(String[] arr) {
		int key = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("-key")) {
				key = Integer.parseInt(arr[++i]);
				break;
			}
		}
		return key;
	}
	static String data(String[] arr) {
		String d = "";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("-data")) {
				i++;
				d += arr[i];
				i++;
				while(i < arr.length) {
					if (!arr[i].startsWith("-")) {
						d = d + " " + arr[i];
						i++;
					}
					else
						break;
				}
				break;
			}
		}
		return d;
	}
	
	static char encrypt(int key , char ch) {
		int nb = (int)ch + key;
		return (char)nb;
	}
	static String msgCrypt(String msg, int key) {
		String res = "";
		for (int i = 0; i < msg.length(); i++) {
			res += encrypt(key, msg.charAt(i));
		}
		return res;
	}
	static char decrypt(int key, char ch) {
		int nb = (int)ch - key;
		return (char)nb;
	}
	static String msgDecrypt(String msg, int key) {
		String res = "";
		for (int i = 0; i < msg.length(); i++) {
			res += decrypt(key, msg.charAt(i));
		}
		return res;
	}
}
