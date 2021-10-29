package project1;

public class Encrypt {

	private int[] k1 = new int[8];
	private int[] k2 = new int[8];
	private int[] pt = new int[8]; 
	
	//form plaintext array
	void gatherInfo(String plainText, int[] k1, int[] k2) {
		int[] pt = new int[8];
		
		char c1;
		String holder;
		
		try {
			for(int i = 0; i < 8; i ++) {
				c1 = plainText.charAt(i);
				holder = Character.toString(c1);
				pt[i] = Integer.parseInt(holder);
				
				if (pt[i] != 0 && pt[i] != 1) {
					System.out.println("\n invalid pt");
					System.exit(0);
					return;
				}
			}
			
		} catch(Exception e) {
			System.out.println("\n invalid pt");
			System.exit(0);
			return;
		}
		
		//get our plaintext array
		this.pt = pt;
		
//		Print.text("Plaintext array : ");
//		Print.array(this.pt, 8);
//		Print.text("\n");
		
		//save keys from method call
		this.k1 = k1;
		this.k2 = k2;
	}
	
	//initial permutation
	void IP() {
		int[] temp = new int[8];
		temp[0] = pt[1];
		temp[1] = pt[5];
		temp[2] = pt[2];
		temp[3] = pt[0];
		temp[4] = pt[3];
		temp[5] = pt[7];
		temp[6] = pt[4];
		temp[7] = pt[6];
		
		pt = temp;
		
//		Print.text("initial perm :");
//		Print.array(this.pt, 8);
//		Print.text("\n");
	}
	
	//inverse initial permutation
	void InverseIP() {
		int[] temp = new int[8];
		
		temp[0] = pt[3];
		temp[1] = pt[0];
		temp[2] = pt[2];
		temp[3] = pt[4];
		temp[4] = pt[6];
		temp[5] = pt[1];
		temp[6] = pt[7];
		temp[7] = pt[5];
		
		pt = temp;
	}
	
	//complex function
	int[] F(int[] Right, int[] subKey) {
		int[] temp = new int[8];
		
		//right side expansion
		temp[0] = Right[3];
		temp[1] = Right[0];
		temp[2] = Right[1];
		temp[3] = Right[2];
		temp[4] = Right[1];
		temp[5] = Right[2];
		temp[6] = Right[3];
		temp[7] = Right[0];

//		Print.text("expansion on right side: ");
//		Print.array(temp, 8);
//		Print.text("\n");

		//xor with subkey and right side
		temp[0] = temp[0] ^ subKey[0];
		temp[1] = temp[1] ^ subKey[1];
		temp[2] = temp[2] ^ subKey[2];
		temp[3] = temp[3] ^ subKey[3];
		temp[4] = temp[4] ^ subKey[4];
		temp[5] = temp[5] ^ subKey[5];
		temp[6] = temp[6] ^ subKey[6];
		temp[7] = temp[7] ^ subKey[7];
		
//		Print.text("xor with key : ");
//		Print.array(temp, 8);
//		Print.text("\n");
		
		//sboxes 
		final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} };
	    final int[][] S1 = { {0,1,2,3} , {2,0,1,3} , {3,0,1,0} , {2,1,0,3} };

	    //get row coordinates for sbox s0
	    int bit1 = temp[0];
	    int bit4 = temp[3];
	    
	    //which row to look in
	    int row1 = BinaryOp.BinToDec(bit1, bit4);
	    
	    //get col coordinates for sbox s0
	    int bit2 = temp[1];
	    int bit3 = temp[2];
	    
	    //which col to look in
	    int col1 = BinaryOp.BinToDec(bit2, bit3);
	    
	    //get number from row1 col1 coordinates in s0
	    int result = S0[row1][col1];
	    
	    //change result to bit
	    int[] out1 = BinaryOp.DecToBinArray(result);
	    
//	    Print.text("s box S0: ");
//	    Print.array(out1, 2);
//	    Print.text("\n");

	    //same as previous but for s1
	    int bit5 = temp[4]; 
		int bit8 = temp[7]; 
		int row2 = BinaryOp.BinToDec(bit5, bit8);

		int bit6 = temp[5];
		int bit7 = temp[6];
		int col2 = BinaryOp.BinToDec(bit6, bit7);

		int result2 = S1[row2][col2];
		   
		int[] out2 = BinaryOp.DecToBinArray(result2); 

//		 Print.text("S-Box S1: ");
//	     Print.array(out2,2);
//		 Print.text("\n"); 

		
		//put it all together according to sdes algorithm
		int[] out = new int[4];
		out[0] = out1[0];
	    out[1] = out1[1];
		out[2] = out2[0];
		out[3] = out2[1];

		//permutate according to algorithm
		int [] permutated_out = new int[4];
		permutated_out[0] = out[1];
		permutated_out[1] = out[3];
		permutated_out[2] = out[2];
	    permutated_out[3] = out[0];

//	      Print.text("Output of mappingF : ");
//	      Print.array(O_Per,4);
//	 	 Print.text("\n"); 
	      
		return permutated_out;
	}
	
	int[] functionFk(int[] left, int[] right, int[] subkey) {
		int[] temp = new int[4];
		int[] out = new int[8];
		
		temp = F(right, subkey);
		
		
		//xor with left side 
		out[0] = left[0] ^ temp[0];
		out[1] = left[1] ^ temp[1];
		out[2] = left[2] ^ temp[2];
		out[3] = left[3] ^ temp[3];
		
		//combine right side back with left
		out[4] = right[0];
		out[5] = right[1];
		out[6] = right[2];
		out[7] = right[3];

		return out;
	}
	
	//function to swap left and right sides 
	int[] SW(int[] in) {
		int[] temp = new int[8];

		temp[0] = in[4];
		temp[1] = in[5];
		temp[2] = in[6];
		temp[3] = in[7];
	  
	    temp[4] = in[0];
		temp[5] = in[1];
		temp[6] = in[2];
		temp[7] = in[3];	
		
		return temp;	
		
	}
	
	int[] encrypt(String plaintext, int[] k1, int[] k2) {
		gatherInfo(plaintext, k1, k2);
		
//		Print.text("\n---------------------------------------\n");
		IP();
//		Print.text("\n---------------------------------------\n");

		//identify left and right sides
		int[] ls = new int[4];
		int[] rs = new int[4];
		ls[0] = pt[0];
		ls[1] = pt[1];
		ls[2] = pt[2];
		ls[3] = pt[3];
		

		rs[0] = pt[4];
		rs[1] = pt[5];
		rs[2] = pt[6];
		rs[3] = pt[7];
		
//		Print.text("First Round LH : ");
//	     Print.array(LH,4);
//		 Print.text("\n");
//		 
//		 Print.text("First Round RH: ");
//	     Print.array(RH,4);
//		 Print.text("\n");
		 
		//expansion on right side
		int[] r1 = new int[8];
		r1 = functionFk(ls,rs,k1);
			
//			 Print.text("After First Round : ");
//		     Print.array(r1,8);
//			 Print.text("\n");
//			Print.text("\n---------------------------------------\n");
			
		int[] temp = new int[8];
		temp = SW(r1);
			
//			 Print.text("After Switch Function : ");
//		     Print.array(temp,8);
//			 Print.text("\n");
//			 Print.text("\n---------------------------------------\n");
			 
		//left and right side after swap
		ls[0] = temp[0];
		ls[1] = temp[1];
		ls[2] = temp[2];
		ls[3] = temp[3];
				
		rs[0] = temp[4];
		rs[1] = temp[5];
		rs[2] = temp[6];
		rs[3] = temp[7];
				
//				Print.text("Second Round LH : ");
//			     Print.array(LH,4);
//				 Print.text("\n");
//				 
//				 Print.text("Second Round RH: ");
//			     Print.array(RH,4);
//				 Print.text("\n");
				 
		//expansion on new ride side
		int[] r2 = new int[8];
		r2 = functionFk(ls,rs,k2);
					
		pt = r2;
					
//					 Print.text("After Second Round : ");
//				     Print.array(this.pt,8);
//					 Print.text("\n");
//					 Print.text("\n---------------------------------------\n");
					 
		//final step in algorithm
		InverseIP();
					 
		Print.text("Ciphertext: ");
		Print.array(this.pt,8);
//					 Print.text("\n");
					 
		return pt;
	}
}
