public class Hill {
    // Encryption key matrix
    static int[][] k = { {3, 3}, {2, 5} };

    // Decryption key matrix (inverse of k mod 26)
    static int[][] inv = { {15, 17}, {20, 9} };

    // Encrypt/Decrypt function
    static String enc(String t, int[][] m) {
        // Convert to uppercase and remove non-letters
        t = t.toUpperCase().replaceAll("[^A-Z]", "");

        // If odd length, add 'X'
        if (t.length() % 2 != 0) t += "X";

        StringBuilder r = new StringBuilder();

        // Process pairs of letters
        for (int i = 0; i < t.length(); i += 2) {
            int a = t.charAt(i) - 'A';       // Convert char to number (0–25)
            int b = t.charAt(i + 1) - 'A';

            // Matrix multiplication mod 26
            int x = (m[0][0] * a + m[0][1] * b) % 26;
            int y = (m[1][0] * a + m[1][1] * b) % 26;

            r.append((char) (x + 'A'));  // Convert back to letter
            r.append((char) (y + 'A'));
        }
        return r.toString();
    }

    public static void main(String[] args) {
        String msg = "HI";

        String e = enc(msg, k);       // Encrypt with key matrix
        String d = enc(e, inv);       // Decrypt with inverse matrix

        System.out.println("Original : " + msg);
        System.out.println("Encrypted: " + e);
        System.out.println("Decrypted: " + d);
    }
}
