import java.util.Scanner;

public class RC4 {
    private byte[] S = new byte[256];
    private int i = 0;
    private int j = 0;

    // Key Scheduling Algorithm (KSA)
    public void keySetup(byte[] key) {
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + (S[i] & 0xFF) + (key[i % key.length] & 0xFF)) & 0xFF;
            swap(S, i, j);
        }
        this.i = 0;
        this.j = 0;
    }

    // Pseudo-Random Generation Algorithm (PRGA)
    public byte[] encryptDecrypt(byte[] data) {
        byte[] output = new byte[data.length];
        for (int k = 0; k < data.length; k++) {
            i = (i + 1) & 0xFF;
            j = (j + (S[i] & 0xFF)) & 0xFF;
            swap(S, i, j);
            int t = ((S[i] & 0xFF) + (S[j] & 0xFF)) & 0xFF;
            byte keyStreamByte = S[t];
            output[k] = (byte) (data[k] ^ keyStreamByte);
        }
        return output;
    }

    private void swap(byte[] array, int i, int j) {
        byte temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter key: ");
        String keyString = sc.nextLine();

        try {
            RC4 rc4 = new RC4();

            // Setup key
            rc4.keySetup(keyString.getBytes());

            // Encrypt plaintext
            byte[] encrypted = rc4.encryptDecrypt(plaintext.getBytes());

            // Display encrypted data in hex
            System.out.print("Encrypted (hex): ");
            for (byte b : encrypted) {
                System.out.printf("%02X ", b);
            }
            System.out.println();

            // To decrypt, initialize RC4 again with same key
            RC4 rc4Decrypt = new RC4();
            rc4Decrypt.keySetup(keyString.getBytes());

            byte[] decrypted = rc4Decrypt.encryptDecrypt(encrypted);
            String decryptedText = new String(decrypted);
            System.out.println("Decrypted text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
