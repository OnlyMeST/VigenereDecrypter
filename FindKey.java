package vigenere;

import java.util.HashMap;
import java.util.Map;

public class FindKey{
	public int findLikelyKey(String substring) {
		int[] frequencies = new int[26];
        for (int i = 0; i < substring.length(); i++) {
            char c = Character.toUpperCase(substring.charAt(i));
            if (Character.isLetter(c)) {
                frequencies[c - 'A']++;
            }
        }

        int maxFrequency = 0;
        int likelyKey = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > maxFrequency) {
                maxFrequency = frequencies[i];
                likelyKey = (i - ('E' - 'A') + 26) % 26;
            }
        }
        
        System.out.println("\n likely Key" + likelyKey);

        return likelyKey;
    }
	
	public  String decryptVigenere(String ciphertext, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char encryptedChar = ciphertext.charAt(i);
            char keyChar = (char) ((encryptedChar - key + 26) % 26 + 'A');
            decryptedText.append(keyChar);
        }
        return decryptedText.toString();
    }
}