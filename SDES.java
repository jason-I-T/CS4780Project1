package project1;

//import java.util.InputMismatchException;
import java.util.Scanner;

public class SDES {
	
	public static void main(String[] args) {
		getKeys gk = new getKeys();
		Encrypt enc = new Encrypt();
		Scanner scan = new Scanner(System.in);
		String plainText;
		String key;
		int[] cipherText = new int[8];
		
		//try/catch in case we need input validation
		//we could prob delete tho
//		try {
			System.out.print("Enter 8-bit Plaintext : ");
			plainText = scan.next();
			
			
			System.out.println(" \n ");
			
		    System.out.print("Enter 10-bit Key : ");
		    key = scan.next();
		    
			
			System.out.println(" \n ");
			
//			Print.text("\n Key Generation ...\n");
//			Print.text("\n---------------------------------------\n");
			gk.GenerateKeys(key);
			Print.text("\n---------------------------------------\n");
			cipherText = enc.encrypt(plainText ,gk.getK1(),gk.getK2());	
			
			
			Print.text("\n---------------------------------------\n");
			System.out.println(" \n Decryption  ");
			
			System.out.print("Enter 8-bit Ciphertext : ");
			plainText = scan.next();
			
			
			System.out.println(" \n ");
			
		    System.out.print("Enter 10-bit Key : ");
		    key = scan.next();
		    
			
			System.out.println(" \n ");
			
//			Print.text("\n---------------------------------------\n\n");
			gk.GenerateKeys(key);
//			Print.text("\n---------------------------------------\n");
			
			Print.text("this says cipher text but its really the plaintext since\n");
			Print.text("this is decryption. I just used the encryption() method with\n");
			Print.text("the keys in reverse order.");

			Print.text("\n---------------------------------------\n");
			cipherText = enc.encrypt( plainText ,gk.getK2(),gk.getK1());
			Print.text("\n---------------------------------------\n");
			
//			Print.text("\n---------------------------------------\n");	
			
//		} catch(Exception e) {
//			System.out.println("error: " + e);
//		}
	}
	
		

}

//class in order to easy print arrays and text instead of system.out.println
class Print {
  static void array(int[] array, int length) {
    for(int i = 0; i < length; i++) {
      System.out.print(array[i] + " ");
    }
  }
  
  static void text(String text) {
    System.out.print(text);
  }
}




//i found this class on the internet not sure how it works but it does
class BinaryOp {
  /** Gets binary digits as arguments & returns decimal number 
  for example input args [1,0,0] will return 4 **/ 
  static int BinToDec(int...bits) {
		 
	 int temp = 0;
	 int base = 1;
	 for(int i = bits.length-1 ; i >= 0; i--) {
		temp = temp + (bits[i]*base);
		base = base * 2 ;
	 }
	  
	  return temp;
  }
  
  //gets decimal number and turns it to array of equivalent binary number
  //eg DecToBinArray(10) returns [1,0,1,0]
  static int[] DecToBinArray(int number) {
	if(number == 0) {
		int[] zero = new int[2];
		zero[0] = 0;
		zero[1] = 0;
		return zero;	
	}

	int[] temp = new int[10] ;


	int count = 0 ;
	for(int i= 0 ; number != 0 ; i++) {
	  temp[i] = number % 2;
	  number = number/2;
	  count++;
	}


	int[] temp2 = new int[count];


	for(int i = count-1, j = 0; i >= 0 && j<count; i--, j++) {
			temp2[j] = temp[i];
	}

	//we need 2 bits for output so this adds the leading 0
	if(count<2) {
		temp = new int[2];
		temp[0] = 0;
		temp[1] = temp2[0];
		return temp;
	}
	 
	return temp2;
	}
}


	 
