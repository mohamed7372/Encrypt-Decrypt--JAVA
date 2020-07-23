package encrypted;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypted {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String res;
		String choix = mode(args);
		int key = key(args);
		String msg = data(args);
		if (msg.startsWith("\""))
			msg = msg.substring(1,msg.length() - 1);

		switch (choix) {
		case "enc":
			if ((!inFile(args).isEmpty() && !msg.isEmpty()) || (!msg.isEmpty())) { // prefer data on file
				res = msgCrypt(msg, key).trim();
				showCryp(res, args);
			}
			else if (!inFile(args).isEmpty()) { // from file
				String fileContient = dataFromFile(inFile(args));
				res = msgCrypt(fileContient, key);
				showCryp(res, args);
			}
			break;	
		case "dec":
			if ((!inFile(args).isEmpty() && !msg.isEmpty()) || (!msg.isEmpty())) { // prefer data on file
				res = msgDecrypt(msg, key).trim();
				showCryp(res, args);
			}
			else if (!inFile(args).isEmpty()) { // from file
				String fileContient = dataFromFile(inFile(args));
				res = msgDecrypt(fileContient, key);
				showCryp(res, args);
			}
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
	
	static String inFile(String[] arr) {
		String d = "";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("-in")) {
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
	static String outFile(String[] arr) {
		String d = "";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("-out")) {
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
	static String dataFromFile(String pathIn) {
		String d = "";
		try {
			d = readFile(pathIn);
		} catch (IOException e) {
			System.out.println("cannot read file " + e.getMessage());
		}
		return d;
	}
	static void printerFile (String msg, String path) {
		File f = new File(path);
		try {
			FileWriter w = new FileWriter(f);
			w.write(msg);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static String readFile(String fileName) throws IOException{
		return new String (Files.readAllBytes(Paths.get(fileName)));
	}
	
	static void showCryp (String res, String[] arr) {
		if (outFile(arr).isEmpty()) 
			System.out.println(res);
		else
			printerFile(res, outFile(arr));
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
