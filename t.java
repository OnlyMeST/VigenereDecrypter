
private static String encryptVigenere(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);

            if (Character.isLetter(plainChar)) {
                char keyChar = key.charAt(i % keyLength);
                char encryptedChar = encryptCharVigenere(plainChar, keyChar);
                ciphertext.append(encryptedChar);
            } else {
                // If it's not a letter, leave it unchanged
                ciphertext.append(plainChar);
            }
        }

        return ciphertext.toString();
    }

    private static char encryptCharVigenere(char plainChar, char keyChar) {
        // Assuming uppercase letters only for simplicity
        int plainIndex = plainChar - 'A';
        int keyIndex = keyChar - 'A';
        int encryptedIndex = (plainIndex + keyIndex) % 26;
        return (char) ('A' + encryptedIndex);
    }

private static String decryptVigenere(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);

            if (Character.isLetter(cipherChar)) {
                char keyChar = key.charAt(i % keyLength);
                char decryptedChar = decryptCharVigenere(cipherChar, keyChar);
                decryptedText.append(decryptedChar);
            } else {
                // If it's not a letter, leave it unchanged
                decryptedText.append(cipherChar);
            }
        }

        return decryptedText.toString();
    }

    private static char decryptCharVigenere(char cipherChar, char keyChar) {
        // Assuming uppercase letters only for simplicity
        int cipherIndex = cipherChar - 'A';
        int keyIndex = keyChar - 'A';
        int decryptedIndex = (cipherIndex - keyIndex + 26) % 26; // Adding 26 to handle negative results
        return (char) ('A' + decryptedIndex);
    }

