public class NQueens {
	static int[] x;
	public static boolean place(int k, int i) {
		for (int j = 1; j < k; j++)
			if (x[j] == i || Math.abs(x[j] - i) == Math.abs(j - k))
				return false;
				return true;
	}	
	public static void nqueens(int k, int n) {
		if (k > n) {
			for (int i = 1; i <= n; i++)
			System.out.print(x[i] + " ");
			System.out.println();
		}
		else
			for (int i = 1; i <= n; i++)
				if (place(k, i)) {
					x[k] = i;
					nqueens(k + 1, n);
				}
		}

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		System.out.print("Enter number of queens: ");
		int n = scanner.nextInt();
		scanner.close();
		x = new int[n + 1];
		nqueens(1, n);
	}
}
//output1
Enter number of queens: 4
2 4 1 3 
3 1 4 2 
  //output2 
Enter number of queens: 6   
2 4 6 1 3 5 
3 6 2 5 1 4 
4 1 5 2 6 3 
5 3 1 6 4 2 
