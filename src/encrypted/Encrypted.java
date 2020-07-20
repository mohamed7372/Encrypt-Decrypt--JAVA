package encrypted;

import java.util.Scanner;

public class Encrypted {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String msg = sc.nextLine();
		int key = sc.nextInt();
		msg = msg.toLowerCase(); 
		System.out.println(msgCrypt(msg, key));
	}
	
	static char encrypt(int key , char ch) {
		int nbrCh = (int)ch - 96;
		int newPos = (nbrCh + key) % 26;
		char chCryp = (char)(newPos + 96);
		return chCryp;
	}
	static String msgCrypt(String msg, int key) {
		String res = "";
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z')
				res += encrypt(key, msg.charAt(i));
			else
				res += msg.charAt(i);
		}
		return res;
	}
}
