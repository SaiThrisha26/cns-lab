import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Take user input
        System.out.print("Enter text to encrypt: ");
        String msg = sc.nextLine();

        // AES key (must be 16 bytes for AES-128)
        String keyStr = "1234567890123456";
        SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), "AES");

        // Create Cipher for AES
        Cipher c = Cipher.getInstance("AES");

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
