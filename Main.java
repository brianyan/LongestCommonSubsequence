import java.util.*;
public class Main {
	public static ArrayList<List<Pair>> result = new ArrayList<List<Pair>>();
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
				 result = new ArrayList<List<Pair>>();
				 int [][] allLCS = allLCSSequence(s[0], s[1]);
				 ArrayList<List<Pair>> answer = printAllLCS(allLCS, s[0], s[1], s[0].length(), s[1].length());
				 for(List<Pair> pair : answer){
				 	System.out.println(pair.toString());
				 }
			}
		}
		else if(args[0].equals("-test") ) {
			while(userinput.hasNext()){
				 String[] s = userinput.nextLine().split(" ");
				 int [][] allLCS = allLCSSequence(s[0], s[1]);
				 ArrayList<String> list = new ArrayList<String>();
				 String q = new String();
				 lcs1(allLCS, s[0], s[1], s[0].length(), s[1].length(),q);
				 Set<String> set = new HashSet<String>();
				 //set.add
				 //System.out.println(answer.toString());
				 // for(int i = 1; i<answer.size();i++){
				 // 	ArrayList<Pair> temp = new ArrayList<Pair>();
				 // 	while(i < answer.size()){
				 // 		if(answer.get(i).equals(newstart)){
				 // 			set.add(temp);
				 // 			temp = new ArrayList<Pair>();
				 // 		}
				 // 		else {
				 // 			temp.add(answer.get(i));
				 // 		}
				 // 	i++;
				 // 	}
				 // }
				 // for(ArrayList<Pair> arr : set){
				 // 	System.out.println(arr);
				 // }
				 // for(Pair str : answer){
				 // 	System.out.print(str);
				 // }
				 System.out.println();
				 // for(ArrayList<Pair>  pair : answer){
				 // 	for(Pair p : pair){
				 // 		System.out.print(p);
				 // 	}
				 // 	System.out.println();
				 // }
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

// 	public static ArrayList<String> lcs1(int[][] dp, String fst, String snd, int i, int j) {
//     ArrayList<String> lcss = new ArrayList<String>();

//     if (i == 0 || j == 0) {
//         lcss.add("");
//     } else if (fst.charAt(i - 1) == snd.charAt(j - 1)) {
//         for (String lcs : lcs1(dp, fst, snd, i - 1, j - 1)) {
//             lcss.add(lcs + fst.charAt(i - 1));
//         }
//     } else {
//         if (dp[i - 1][j] >= dp[i][j - 1]) {
//             lcss.addAll(lcs1(dp, fst, snd, i - 1, j));
//         }

//         if (dp[i][j - 1] >= dp[i - 1][j]) {
//             lcss.addAll(lcs1(dp, fst, snd, i, j - 1));
//         }
//     }
//     return lcss;
// }
	public static void lcs1(int[][] dp, String fst, String snd, int i, int j, String lcsSoFar) {
	    if(dp[i][j] == 0){
	    	// if(dp[])
	    	// lcsSoFar = "<" + i + ", " + j + ">" + lcsSoFar;
	        System.out.println(lcsSoFar);
	        return;
	    }
	    if (dp[i][j] == dp[i][j - 1]) {
	        lcs1(dp, fst, snd, i, j-1, lcsSoFar);
	    }
	    if (dp[i][j] == dp[i - 1][j]) {
            lcs1(dp, fst, snd, i-1, j, lcsSoFar);
        }
        if(fst.charAt(i-1) == snd.charAt(j-1)){
	        lcsSoFar = "<" + i + ", " + j + ">, "+ lcsSoFar;
	        lcs1(dp, fst, snd, i - 1, j - 1, lcsSoFar); //{ // String str: lcs1(dp, fst, snd, i - 1, j - 1)
	    } 
	}
	//(<3, 2>, <4, 3>)
// (<1, 2>, <4, 3>)
// (<1, 2>, <2, 3>)
	public static ArrayList<List<Pair>> printAllLCS(int[][] lcs, String str1, String str2, int i, int j){
		if(i == 0 || j == 0){
			return result;
		}
		List<Pair> list = new ArrayList<Pair>();
		list.add(new Pair(i,j));
		result.add(list);
		// if()
		// StringBuilder result = new StringBuilder();
		//while(lcs[i][j] != ""){
			if((lcs[i][j]-1) == (lcs[i-1][j-1])) {
				// the two letters match up, so store the indexes

				//result.append(lcs[i][j].substring(lcs[i][j].length()-1));
				i = i-1;
				j = j-1;
			}
			else if(lcs[i][j] == lcs[i-1][j]){
				i = i-1;
			}
			else {
				j = j-1;
			}
		return result;
		//System.out.print(result.reverse().toString());
	//}
	}
	
}
