import java.util.Scanner;

public class RowColumnTransformation{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take matrix size
        System.out.print("Enter number of rows: ");
        int n = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int m = sc.nextInt();

        int[][] a = new int[n][m];

        // Take matrix input
        System.out.println("Enter matrix elements:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        int[] rowSum = new int[n];
        int[] colSum = new int[m];

        // Calculate row sums
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSum[i] += a[i][j];
            }
        }

        // Calculate column sums
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j] += a[i][j];
            }
        }

        // Create transformed matrix
        int[][] result = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = rowSum[i] + colSum[j];
            }
        }

        // Print result
        System.out.println("\nTransformed Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
