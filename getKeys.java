package project1;

public class getKeys {

	private int[] key = new int[10];
	private int[] k1 = new int[8];
	private int[] k2 = new int[8];
	  

//	getKeys() {}
	  
	void GenerateKeys(String userKey) {
		int[] key = new int[10];
		char c1;
		String ts;
		  
		for(int i = 0; i < 10; i++) {
			c1 = userKey.charAt(i);
			ts = Character.toString(c1);
			key[i] = Integer.parseInt(ts);
			  
			//input validation
			if (key[i] != 0 && key[i] != 1) {
				System.out.println("Invalid Key");
				System.exit(0);
				return;
			}
		}
		
		this.key = key;
//		Print.msg("before p10 key :");
//		Print.array(this.key, 10);
//		Print.msg("\n");
		
		P10();
		
//		Print.msg("After p10 key :");
//		Print.array(this.key, 10);
//		Print.msg("\n");
		
		LS1();
		
//		Print.msg("After ls1 : ");
//		Print.array(this.key, 10);
//		Print.msg("\n");
		
		this.k1 = P8();
		
//		Print.msg("Subkey k1 : ");
//		Print.array(this.k1, 8);
//		Print.msg("\n");
		
		LS2();
		
//		Print.msg("after ls2 : ");
//		Print.array(this.key, 10);
//		Print.msg("\n");
		
		this.k2 = P8();
		
//		Print.msg("k2 : ");
//		Print.array(this.k2, 8);
//		Print.msg("\n");
	}
	
	//function to carry out p8 from algorithm
	private int[] P8() {
	  int[] temp = new int[8];
	    
	  temp[0] = key[5];
	  temp[1] = key[2];
	  temp[2] = key[6];
	  temp[3] = key[3];
	  temp[4] = key[7];
	  temp[5] = key[4];
	  temp[6] = key[9];
	  temp[7] = key[8];
	    
	  return temp;
	        
	}
	
	//function to carry out ls1 from algorithm
	private void LS1() {
	  int[] temp = new int[10];
	    
	  temp[0] = key[1];
	  temp[1] = key[2];
	  temp[2] = key[3];
	  temp[3] = key[4];
	  temp[4] = key[0];
	    
	  temp[5] = key[6];
	  temp[6] = key[7];
	  temp[7] = key[8];
	  temp[8] = key[9];
	  temp[9] = key[5];
	    
	  key = temp;
	    
	}
	
	//ls2 from algo
	private void LS2() {
	  int[] temp = new int[10];
	    
	  temp[0] = key[2];
	  temp[1] = key[3];
	  temp[2] = key[4];
	  temp[3] = key[0];
	  temp[4] = key[1];
	    
	  temp[5] = key[7];
	  temp[6] = key[8];
	  temp[7] = key[9];
	  temp[8] = key[5];
	  temp[9] = key[6];
	    
	  key = temp;
	    
	}
	
	//from algo
	private void P10() {
		int[] temp = new int[10];
	  
		temp[0] = key[2];
		temp[1] = key[4];
		temp[2] = key[1];
		temp[3] = key[6];
		temp[4] = key[3];
		temp[5] = key[9];
		temp[6] = key[0];
		temp[7] = key[8];
		temp[8] = key[7];
		temp[9] = key[5];
		  
		  
		key = temp;
	      
	}
	
	//getters
	public int[] getK1() {
	  return k1;
	}

	public int[] getK2() {
	  return k2;
	}  
}
