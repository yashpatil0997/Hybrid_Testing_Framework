package Test_Cases;


import java.util.ArrayList;
import java.util.List;

public class Test_11 {

	public static void main(String[] args) {
		//{'1','w','3','5','2','r'};
		char[] a= {'1','w','3','5','2','r'};
		 for (char i= '9'; i>= '0';  i-- ) {
			 for (char c: a) {
				 if (c == i) {
					 System.out.println(c + " ");
				 }
			 }
		 }
		
		
		

		

	}

}
