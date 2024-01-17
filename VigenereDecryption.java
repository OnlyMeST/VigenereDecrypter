public static int findLikelyKey(String substring) {
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

        return likelyKey;
    }

    public static String decryptVigenere(String ciphertext, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char encryptedChar = ciphertext.charAt(i);
            char keyChar = (char) ((encryptedChar - key + 26) % 26 + 'A');
            decryptedText.append(keyChar);
        }
        return decryptedText.toString();
    }



public static String decryptVigenere(String ciphertext, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char encryptedChar = ciphertext.charAt(i);
            char keyChar = (char) ((encryptedChar - key + 26) % 26 + 'A');
            decryptedText.append(keyChar);
        }
        return decryptedText.toString();
    }



String ciphertext = "YOUR_CIPHERTEXT_HERE";
        int keyLength = VigenereUtils.findKeyLength(ciphertext);

        // Divide the ciphertext into substrings based on the key length
        StringBuilder[] substrings = new StringBuilder[keyLength];
        for (int i = 0; i < keyLength; i++) {
            substrings[i] = new StringBuilder();
        }
        for (int i = 0; i < ciphertext.length(); i++) {
            substrings[i % keyLength].append(ciphertext.charAt(i));
        }

        // Decrypt each substring using frequency analysis
        StringBuilder plaintext = new StringBuilder();
        for (StringBuilder substring : substrings) {
            int likelyKey = VigenereUtils.findLikelyKey(substring.toString());
            String decryptedSubstring = VigenereUtils.decryptVigenere(substring.toString(), likelyKey);
            plaintext.append(decryptedSubstring);
        }

        System.out.println("Decrypted plaintext: " + plaintext.toString());
