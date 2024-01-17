int findKeyLength(String ciphertext) {
    double maxIndexOfCoincidence = 0;
    double minChiSquared = Double.MAX_VALUE;
    int likelyKeyLength = 0;
    for (int keyLength = 1; keyLength <= MAX_KEY_LENGTH; keyLength++) {
        StringBuilder[] substrings = new StringBuilder[keyLength];
        for (int i = 0; i < keyLength; i++) {
            substrings[i] = new StringBuilder();
        }
        for (int i = 0; i < ciphertext.length(); i++) {
            substrings[i % keyLength].append(ciphertext.charAt(i));
        }
        // Calculate the index of coincidence for each substring
        double indexOfCoincidenceSum = 0;
        for (StringBuilder substring : substrings) {
            double indexOfCoincidence = calculateIndexOfCoincidence(substring.toString());
            indexOfCoincidenceSum += indexOfCoincidence;
        }
        double averageIndexOfCoincidence = indexOfCoincidenceSum / keyLength;

        // Calculate the chi-squared statistic for each substring
        double chiSquaredSum = 0;
        for (StringBuilder substring : substrings) {
            double chiSquared = calculateChiSquared(substring.toString());
            chiSquaredSum += chiSquared;
        }
        double averageChiSquared = chiSquaredSum / keyLength;

        // Update the likely key length based on the index of coincidence or chi-squared statistic
        if (averageIndexOfCoincidence > maxIndexOfCoincidence) {
            maxIndexOfCoincidence = averageIndexOfCoincidence;
            likelyKeyLength = keyLength;
        }
        if (averageChiSquared < minChiSquared) {
            minChiSquared = averageChiSquared;
            likelyKeyLength = keyLength;
        }
    }
    return likelyKeyLength;
}
