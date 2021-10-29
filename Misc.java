package project1;

import java.util.Arrays;

public class Misc {
	public static void main(String[] args) {
		String heystring = "";
		int[] hey = new int[8];
		for (int i = 0; i < hey.length; i++) {
			hey[i] = 1;
		}

	    for(int i=0;i<hey.length;i++) {
//	        System.out.print(hey[i] + "");
	        heystring += hey[i];
	    }
		
		System.out.println(heystring);
	}
}
