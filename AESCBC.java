import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class AESCBC {
    public static void main(String[] a) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Take input from user
        System.out.print("Enter text to encrypt: ");
        String t = sc.nextLine();

        // Step 1: Generate AES key (like secret password)
        var k = KeyGenerator.getInstance("AES");
        k.init(128); // key size = 128 bits
        var key = k.generateKey();

        // Step 2: Generate random IV (Initialization Vector)
        byte[] iv = new byte[16]; // AES block size = 16 bytes
        new SecureRandom().nextBytes(iv);

        // Step 3: Create Cipher for AES CBC mode with padding
        var c = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Step 4: Encrypt
        c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        var e = c.doFinal(t.getBytes());

        // Step 5: Decrypt
        c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        var d = c.doFinal(e);

        // Step 6: Print results
        System.out.println("Enc: " + Base64.getEncoder().encodeToString(e));
        System.out.println("Dec: " + new String(d));

        sc.close();
    }
}
