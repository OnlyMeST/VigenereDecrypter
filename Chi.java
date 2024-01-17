package vigenere;

import java.lang.Math;

public class Chi{
	private final double[] ENGLISH_EXPECTED_FREQUENCIES = {0.0817, 0.0149, 0.0278, 0.0425, 0.1270, 0.0223, 0.0202, 0.0609, 0.0697, 0.0015, 0.0077, 0.0402, 0.0241,
                                                           0.0675, 0.0751, 0.0193, 0.0010, 0.0596, 0.0633, 0.0906, 0.0276, 0.0098, 0.0236, 0.0015, 0.0197, 0.0007};
	public double calculateChi(String cipherText) {
		int[] letterFrequency = new int[26];
		int totalChars = 0;
		//Count vhars
		for (char c : cipherText.toCharArray()) {
			if (Character.isLetter(c)) {
				letterFrequency[Character.toLowerCase(c) - 'a']++;
				totalChars++;
			}
		}
		double chiSquared = 0;
		for (int i = 0; i < 26; i++) {
			double expectedFrequencies = totalChars * ENGLISH_EXPECTED_FREQUENCIES[i];
			chiSquared += Math.pow(letterFrequency[i] - expectedFrequencies, 2) / expectedFrequencies;
		}
		return chiSquared;
	}
}
