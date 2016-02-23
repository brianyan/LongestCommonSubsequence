import java.util.*;
public class Main {
	
	public static void main(String [] args){
		Scanner userinput = new Scanner(System.in);
		int TestCases = Integer.parseInt(userinput.nextLine());
		List<List< String> > variables = new ArrayList<List<String> > ();
		int count = 0;
		while(userinput.hasNext()){
			 String[] s = userinput.nextLine().split(" ");
			 LongestCommonSubSequence(s[0], s[1]);
			 count++;
		}
		userinput.close();

	}

	public static void LongestCommonSubSequence(String str1, String str2){
		int m = str1.length();
		int n = str2.length();

		// initialize array to 0's
		int[][] array = new int [m+1][n+1];
		String[][] actualstring = new String [m+1][n+1];
		for(String [] subarray: actualstring){
			Arrays.fill(subarray, "");
		}
		for(int i = 1; i<=m; i++){
			for(int j = 1; j<=n; j++){
				if(str1.charAt(i-1) == (str2.charAt(j-1)) ){
					array[i][j] = array[i-1][j-1] + 1;
					actualstring[i][j] = actualstring[i-1][j-1] + str1.charAt(i-1);
				}
				else if( array[i-1][j] >= array[i][j-1]){
					array[i][j] = array[i-1][j];
					actualstring[i][j] = actualstring[i-1][j];
				}
				else {
					array[i][j] = array[i][j-1];
					actualstring[i][j] = actualstring[i][j-1];
				}
			}
			
		}
		System.out.print(array[m][n] + " ");
		System.out.println(actualstring[m][n]);
			//printLCS(actualstring, str1, m, n);
		
	}
	public static void printLCS(String[][] lcs, String str1, int i, int j){
		if( i == 0 || j == 0){
			return;
		}
		if(lcs[i][j] != ""){

			if(lcs[i][j].substring(0,lcs[i][j].length()-1).equals(lcs[i-1][j-1] ) ){
				printLCS(lcs, str1, i-1, j-1);
				System.out.print(str1.charAt(i-1));
			}
			else if(lcs[i][j] == lcs[i-1][j]){
				printLCS(lcs, str1, i-1, j);
			}
			else{
				printLCS(lcs, str1, i, j-1);
			}
		}
	}
}