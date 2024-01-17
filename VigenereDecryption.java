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
