package vigenere;

public class CoincidenceIndex{
	public double calculateIndex(String cipherText) {
		int[] letterFreq = new int[26];
		int totalChars = 0;
		//Count vhars
		for (char c : cipherText.toCharArray()) {
			if (Character.isLetter(c)) {
				letterFreq[Character.toLowerCase(c) - 'a']++;
				totalChars++;
			}
		}
		double index = 0;
		for (int frequency : letterFreq) {
			index += (frequency * (frequency - 1)) / (double)(totalChars * (totalChars - 1));
		}
		return index;
	}
}
