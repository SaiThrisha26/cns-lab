import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class AESECB {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Take user input
        System.out.print("Enter text to encrypt: ");
        String t = sc.nextLine();

        // Step 1: Generate AES key (secret key)
        KeyGenerator k = KeyGenerator.getInstance("AES");
        k.init(128); // 128-bit AES key
        SecretKey key = k.generateKey();

        // Step 2: Create Cipher (AES/ECB/PKCS5Padding by default)
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Step 3: Encrypt
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] e = c.doFinal(t.getBytes());

        // Step 4: Decrypt
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] d = c.doFinal(e);

        // Step 5: Print results
        System.out.println("Enc: " + Base64.getEncoder().encodeToString(e));
        System.out.println("Dec: " + new String(d));

        sc.close();
    }
}
