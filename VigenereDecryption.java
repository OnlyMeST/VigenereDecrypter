package vigenere;

import java.util.Scanner;

public class VigenereDecryption {
	
    public static void main(String[] args) {
        KeyLength keyLength = new KeyLength();
        FindKey findKey = new FindKey();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter encrypted Text: ");
        String ciphertext = scanner.nextLine();
        
        int keyLeng = keyLength.findKeyLength(ciphertext);
        StringBuilder[] substrings = new StringBuilder[keyLeng];
        for (int i = 0; i < keyLeng; i++) {
            substrings[i] = new StringBuilder();
        }
        for (int i = 0; i < ciphertext.length(); i++) {
            substrings[i % keyLeng].append(ciphertext.charAt(i));
        }
        
        StringBuilder plaintext = new StringBuilder();
        for (StringBuilder substring : substrings) {
            int likelyKey = findKey.findLikelyKey(substring.toString());
            String decryptedSubstring = findKey.decryptVigenere(substring.toString(), likelyKey);
            plaintext.append(decryptedSubstring);
        }

        System.out.println("\nDecrypted plaintext: " + plaintext.toString());
    }
}