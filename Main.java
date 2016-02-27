import java.util.*;
public class Main {
	public static Set<String> set = new HashSet<String>();
	public static void main(String [] args){
		// read user input
		Scanner userinput = new Scanner(System.in);
		// Get the number of test cases to run
		int TestCases = Integer.parseInt(userinput.nextLine());
		List<List< String> > variables = new ArrayList<List<String> > ();
		int count = 0;
		// regular lcs case
		if(args.length == 0){
			while(userinput.hasNext()){
				 String[] s = userinput.nextLine().split(" ");
				 LongestCommonSubSequence(s[0], s[1]);
				 count++;
			}
		}
		// test for the all case
		else if(args[0].equals("-all") ) {
			while(userinput.hasNext()){
				 String[] s = userinput.nextLine().split(" ");
				 int [][] allLCS = allLCSSequence(s[0], s[1]);
				 ArrayList<String> list = new ArrayList<String>();
				 String q = new String();
				 set = new HashSet<String>();
				 lcs1(allLCS, s[0], s[1], s[0].length(), s[1].length(),q);
				 for(String str: set){
				 	str = "(" + str.substring(0,str.length()-2) + ")";
				 	System.out.println(str);
				 }
				 System.out.println();
			}
		}
		userinput.close();
	}

	public static void LongestCommonSubSequence(String str1, String str2){
		int m = str1.length();
		int n = str2.length();
		// initialize array to 0's
		int[][] array = new int [m+1][n+1];
		// initialize a string matrix to store the lcs 
		String[][] actualstring = new String [m+1][n+1];
		for(String [] subarray: actualstring){
			Arrays.fill(subarray, "");
		}
		// iterate
		for(int i = 1; i<=m; i++){
			for(int j = 1; j<=n; j++){
				// if character is same update the matrix
				if(str1.charAt(i-1) == (str2.charAt(j-1)) ){
					array[i][j] = array[i-1][j-1] + 1;
					actualstring[i][j] = actualstring[i-1][j-1] + str1.charAt(i-1);
				}
				// if length on top is greater, adopt the top to new matrix
				else if( array[i-1][j] >= array[i][j-1]){
					array[i][j] = array[i-1][j];
					actualstring[i][j] = actualstring[i-1][j];
				}
				else {
					// otherwise, adopt the left side
					array[i][j] = array[i][j-1];
					actualstring[i][j] = actualstring[i][j-1];
				}
			}
		}
		System.out.print(array[m][n] + " ");
		printLCS(actualstring, str1, m, n);
		System.out.println();
	}
	/* function to print the lcs */
	public static void printLCS(String[][] lcs, String str1, int i, int j){
		if( i == 0 || j == 0){
			return;
		}
		// create the result you want to return
		StringBuilder result = new StringBuilder();
		// loop until you see empty string ie the base case
		while(lcs[i][j] != ""){
			// if the array diagonal is the one less than the array, add the character
			if((lcs[i][j].length()-1) == (lcs[i-1][j-1].length() )) {
				result.append(lcs[i][j].substring(lcs[i][j].length()-1));
				i = i-1;
				j = j-1;
			}
			// update index
			else if(lcs[i][j] == lcs[i-1][j]){
				i = i-1;
			}
			else {
				// update index
				j = j-1;
			}
		}
		System.out.print(result.reverse().toString());
	}
	// return a matrix for allLCS
	public static int[][] allLCSSequence(String str1, String str2){
		int m = str1.length();
		int n = str2.length();
		// initialize array to 0's
		int[][] array = new int [m+1][n+1];
		for(int i = 1; i<=m; i++){
			for(int j = 1; j<=n; j++){
				if(str1.charAt(i-1) == (str2.charAt(j-1)) ){
					array[i][j] = array[i-1][j-1] + 1;
				}
				else if( array[i-1][j] >= array[i][j-1]){
					array[i][j] = array[i-1][j];
				}
				else {
					array[i][j] = array[i][j-1];
				}
			}
		}
		return array;
	}
	// Prints out allLCS
	public static void lcs1(int[][] array, String str1, String str2, int i, int j, String lcsSoFar) {
	    // check base case
	    if(array[i][j] == 0){
	        set.add(lcsSoFar);
	        return;
	    }
	    // if it matches on left side, call lcs 
	    if (array[i][j] == array[i][j - 1]) {
	        lcs1(array, str1, str2, i, j-1, lcsSoFar);
	    }
	    // if it matches upper, call lcs
	    if (array[i][j] == array[i - 1][j]) {
            lcs1(array, str1, str2, i-1, j, lcsSoFar);
        }
        // case where the characters match so add on its index
        if(str1.charAt(i-1) == str2.charAt(j-1)){
	        lcsSoFar = "<" + i + ", " + j + ">, "+ lcsSoFar;
	        lcs1(array, str1, str2, i - 1, j - 1, lcsSoFar);
	    } 
	}
}
