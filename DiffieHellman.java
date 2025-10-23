// This program calculates the Key for two persons
// using the Diffie-Hellman Key exchange algorithm
public class DiffieHellman {

    // Function to return (a^b) mod P
    private static long power(long a, long b, long p) {
        if (b == 1)
            return a;
        else
            return ((long) Math.pow(a, b) % p);
    }

    public static void main(String[] args) {
        long P, G, x, a, y, b, ka, kb;

        // Publicly agreed values P and G
        P = 23; // Prime number
        System.out.println("The value of P: " + P);

        G = 9;  // Primitive root of P
        System.out.println("The value of G: " + G);

        // Alice's private key
        a = 4;
        System.out.println("The private key 'a' for Alice: " + a);
        x = power(G, a, P); // Alice's public key

        // Bob's private key
        b = 3;
        System.out.println("The private key 'b' for Bob: " + b);
        y = power(G, b, P); // Bob's public key

        // Generate shared secret keys
        ka = power(y, a, P); // Secret key for Alice
        kb = power(x, b, P); // Secret key for Bob

        System.out.println("\nSecret key for Alice: " + ka);
        System.out.println("Secret key for Bob: " + kb);
    }
}
