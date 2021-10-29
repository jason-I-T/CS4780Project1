package project1;

import java.util.Arrays;
import java.util.Scanner;

// take 8 pit plaintext
	
// take raw key1, and raw key2

// generate keys

//k1, k2 from rawkey 1
//encrypt(pt, k1, k2) --> stage1

//this step requires k2, k1 from rawkey 2
//this is decryption so we call encrypt with the subkeys in reverse order
//encrypt(stage1, k2, k1) --> stage2

//k1, k2 from rawkey 1
//encrypt(stage2, k1, k2) --> ciphertext
public class TSDES {
	
	public static void main(String[] args) {
		getKeys gk = new getKeys();
		Encrypt enc = new Encrypt();
		Scanner scan = new Scanner(System.in);
		
		String plainText;
		String rawKeyOne;
		String rawKeyTwo;

		int[] stage1 = new int[8];
		//holds the string version of the stage1 array
		String stage1_string = "";

		int[] stage2 = new int[8];
		//string version of stage 2 array
		String stage2_string = "";

		int[] cipherText = new int[8];
		
		//try/catch just in case we need input validation
//		try {
			System.out.print("Enter Plaintext: ");
			plainText = scan.next();
			
			System.out.println(" \n ");
			
			System.out.print("Enter Raw Key 1: ");
			rawKeyOne = scan.next();
			System.out.print("Enter Raw Key 2: ");
			rawKeyTwo = scan.next();
			
			System.out.println(" \n ");

			//STAGE 1 ENCRYPT PLAINTEXT USING K1 AND K2 FROM RAWKEY 1
			System.out.println("Key Generation");
			gk.GenerateKeys(rawKeyOne);

			stage1 = enc.encrypt(plainText, gk.getK1(), gk.getK2());
			//STAGE 1 COMPLETE
			
			System.out.println("\n -------- \n");
			//STAGE 2 DECRYPT STAGE 1 USING K2, K1 FROM RAWKEY 2
			gk.GenerateKeys(rawKeyTwo);
			
			//need to convert stage1 array to string eg [1, 0, ..., 0] -> 1001..0
			for(int i = 0; i < stage1.length; i++) {
				stage1_string += stage1[i];
			}

			//notice order of subkeys for decryption eg k2, k1
			stage2 = enc.encrypt(stage1_string, gk.getK2(), gk.getK1());
			//STAGE 2 COMPLETE

			System.out.println("\n -------- \n");
			//STAGE 3 ENCRYPT STAGE 2 USING K1 AND K2 FROM RAWKEY 1
			gk.GenerateKeys(rawKeyOne);
			
			for(int i = 0; i < stage2.length; i++) {
				stage2_string += stage2[i];
			}

			cipherText = enc.encrypt(stage2_string, gk.getK1(), gk.getK2());
			//STAGE 3 COMPLETE CIPHER TEXT GENERATED 
			
			
			
//		} catch(Exception e) {
//			System.out.println("error: " + e);
//		}
	}
}
