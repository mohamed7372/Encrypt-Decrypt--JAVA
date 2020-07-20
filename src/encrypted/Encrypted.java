package encrypted;

import java.util.Scanner;

public class Encrypted {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String choix = sc.nextLine();
		String msg = sc.nextLine();
		int key = sc.nextInt();
		
		switch (choix) {
		case "enc":
			System.out.println(msgCrypt(msg, key));
			break;	
		case "dec":
			System.out.println(msgDecrypt(msg, key));
			break;
		}
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
