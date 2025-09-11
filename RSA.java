import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import java.util.Scanner;

public class RSA {

    public static void main(String[] args) throws Exception {

        // Generate RSA key pair (Public + Private)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        // Take user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a message: ");
        String t = sc.nextLine();

        // Create cipher object
        Cipher c = Cipher.getInstance("RSA");

        // Encrypt with public key
        c.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        byte[] e = c.doFinal(t.getBytes());

        // Decrypt with private key
        c.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        byte[] d = c.doFinal(e);

        // Show results
        System.out.println("Enc: " + Base64.getEncoder().encodeToString(e));
        System.out.println("Dec: " + new String(d));

        sc.close();
    }
}
