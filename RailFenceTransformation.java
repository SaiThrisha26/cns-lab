import java.util.*;

public class RailFenceTransformation {
    // function to encrypt a message
    public static String encryptRailFence(String text, int key) {

        char[][] rail = new char[key][text.length()];

        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i],'\n');

        boolean dirDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1)
                dirDown = !dirDown;

            rail[row][col++] = text.charAt(i);

            if (dirDown)
                row++;
            else
                row--;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result.append(rail[i][j]);

        return result.toString();
    }

    // function to decrypt a message
    public static String decryptRailFence(String cipher, int key) {

        char[][] rail = new char[key][cipher.length()];

        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '\n');

        // Initialize direction
        boolean dirDown = true;

        int row = 0, col = 0;

        // mark the places with '*'
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                dirDown = true;
            else if (row == key - 1)
                dirDown = false;

            rail[row][col++] = '*';

            if (dirDown)
                row++;
            else
                row--;
        }

        // fill the rail matrix with cipher characters
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();

        // reset direction for reading the matrix
        dirDown = true;
        row = 0;
        col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0)
                dirDown = true;
            else if (row == key - 1)
                dirDown = false;

            if (rail[row][col] != '\n')
                result.append(rail[row][col++]);

            if (dirDown)
                row++;
            else
                row--;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the key (number of rails): ");
        int key = scanner.nextInt();

        String encrypted = encryptRailFence(text, key);
        System.out.println("\nEncrypted Message: " + encrypted);

        String decrypted = decryptRailFence(encrypted, key);
        System.out.println("Decrypted Message: " + decrypted);

        scanner.close();
    }
}
