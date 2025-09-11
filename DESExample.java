import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESExample {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Take user input
        System.out.print("Enter text to encrypt (DES): ");
        String msg = sc.nextLine();

        // DES key (must be 8 bytes)
        SecretKeySpec key = new SecretKeySpec("12345678".getBytes(), "DES");

        // Create cipher
        Cipher c = Cipher.getInstance("DES");

        // Encrypt
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(enc));

        // Decrypt
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);
        System.out.println("Decrypted: " + new String(dec));

        sc.close();
    }
}
