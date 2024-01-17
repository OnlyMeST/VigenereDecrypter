package vigenere;

public class KeyLength {
    private CoincidenceIndex coincidenceIndex = new CoincidenceIndex();
    private Chi chi = new Chi();

    public int findKeyLength(String cipherText) {
        int maxLength = 20; // You can adjust this based on the expected maximum key length

        double minDiff = Double.MAX_VALUE;
        int keyLength = 1;

        for (int i = 1; i <= maxLength; i++) {
            String[] subtexts = getSubtexts(cipherText, i);
            double averageIndex = calculateAverageIndex(subtexts);

            double chiSquaredSum = 0;
            for (String subtext : subtexts) {
                chiSquaredSum += chi.calculateChi(subtext);
            }
            double averageChiSquared = chiSquaredSum / i;

            double difference = Math.abs(averageIndex - averageChiSquared);

            if (difference < minDiff) {
                minDiff = difference;
                keyLength = i;
            }
        }

        return keyLength;
    }

    private String[] getSubtexts(String cipherText, int keyLength) {
        String[] subtexts = new String[keyLength];
        for (int i = 0; i < keyLength; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < cipherText.length(); j += keyLength) {
                sb.append(cipherText.charAt(j));
            }
            subtexts[i] = sb.toString();
        }
        return subtexts;
    }

    private double calculateAverageIndex(String[] subtexts) {
        double sum = 0;
        for (String subtext : subtexts) {
            sum += coincidenceIndex.calculateIndex(subtext);
        }
        return sum / subtexts.length;
    }
}
