package encrypted;

import java.util.Scanner;

public class Encrypted {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//String msg = sc.nextLine();
		String msg = "we found a treasure!";
		msg = msg.toLowerCase();
		System.out.println(encrypt(msg));
	}
	
	static String encrypt(String msg) {
		String res = "";
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z') {
				int ch = (int)(msg.charAt(i)) - 97;
				ch = 122 - ch;
				res += (char)ch;
			}
			else 
				res += msg.charAt(i);
		}
		return res;
	}
}
