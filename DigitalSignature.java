import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Scanner;

public class DigitalSignature {
    public static void main(String[] args) throws Exception {
        // Accepting text from user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter some text: ");
        String msg = sc.nextLine();

        // Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

        // Initializing the key pair generator
        keyPairGen.initialize(2048);

        // Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        // Getting the private key from the key pair
        PrivateKey privKey = pair.getPrivate();

        // Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");

        // Initialize the signature with the private key
        sign.initSign(privKey);

        // Adding data to the signature
        byte[] bytes = msg.getBytes();
        sign.update(bytes);

        // Calculating the signature
        byte[] signature = sign.sign();

        // Printing the signature in hex form
        System.out.println("\nDigital signature for given text:");
        StringBuilder hexSig = new StringBuilder();
        for (byte b : signature) {
            hexSig.append(String.format("%02x", b));
        }
        System.out.println(hexSig.toString());

        sc.close();
    }
}
