package vigenere;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VigenereDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Vigenere cipher text: ");
        String cipherText = scanner.nextLine().toLowerCase(); // Convert to lowercase for consistency

        KeyLength keyLengthFinder = new KeyLength();
        int keyLength = keyLengthFinder.findKeyLength(cipherText);

        System.out.println("Estimated key length: " + keyLength);

        String key = findKey(cipherText, keyLength);
        System.out.println("Estimated key: " + key);

        String decryptedText = decryptVigenere(cipherText, key);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    private static String findKey(String cipherText, int keyLength) {
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < keyLength; i++) {
            String subtext = getSubtext(cipherText, i, keyLength);
            char keyChar = findKeyCharacter(subtext);
            key.append(keyChar);
        }

        return key.toString();
    }

    private static String getSubtext(String cipherText, int start, int keyLength) {
        StringBuilder subtext = new StringBuilder();
        for (int i = start; i < cipherText.length(); i += keyLength) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                subtext.append(c);
            }
        }
        return subtext.toString();
    }

    private static char findKeyCharacter(String subtext) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxFrequency = 0;
        char mostFrequentChar = 'a';

        for (char c : subtext.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
                int currentFrequency = frequencyMap.get(c);
                if (currentFrequency > maxFrequency) {
                    maxFrequency = currentFrequency;
                    mostFrequentChar = c;
                }
            }
        }

        char estimatedKeyChar = (char) ((mostFrequentChar - 'e' + 26) % 26 + 'a');
        return estimatedKeyChar;
    }

    private static String decryptVigenere(String cipherText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            char keyChar = key.charAt(i % key.length());
            if (Character.isLetter(cipherChar)) {
                char decryptedChar = decryptChar(cipherChar, keyChar);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(cipherChar);
            }
        }
        return decryptedText.toString();
    }

    private static char decryptChar(char cipherChar, char keyChar) {
        char base = Character.isUpperCase(cipherChar) ? 'A' : 'a';
        return (char) ((cipherChar - keyChar + 26) % 26 + base);
    }
}
