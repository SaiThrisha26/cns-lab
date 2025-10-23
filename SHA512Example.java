import java.security.MessageDigest;
import java.util.Scanner;

public class SHA512Example {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter text to calculate SHA-512 digest: ");
            String input = sc.nextLine();

            // Create MessageDigest instance for SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Compute the hash
            byte[] digest = md.digest(input.getBytes());

            // Convert byte array into hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }

            // Display the message digest
            System.out.println("\nMessage Digest (SHA-512):");
            System.out.println(hexString.toString());

            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
