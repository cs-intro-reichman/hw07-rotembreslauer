
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.isEmpty()){
			return word2.length();
		}
		if (word2.isEmpty()){
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)){
			return  levenshtein(tail(word1), tail(word2));
		}else{
			return 1 + Math.min(Math.min(levenshtein(tail(word1), tail(word2)) , levenshtein(word1, tail(word2))) , levenshtein(word2, tail(word1)));
		}
	}


	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
			for (int i = 0; i < dictionary.length; i ++){
				String word = in.readString();
					dictionary[i] = word;
				}
			
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		 String mostSimilar = word; 

		for (int j = 0; j <= threshold; j++) {
			for (int i = 0; i < dictionary.length; i++) {
			String candidate = dictionary[i];
			int distance = levenshtein(word.toLowerCase(), candidate);
	
				if (distance <= j && distance < levenshtein(word.toLowerCase(), mostSimilar)) {
					mostSimilar = candidate;
				}
			}
	}
		if (levenshtein(word.toLowerCase(), mostSimilar) > threshold) {
		return word;
		}
		return mostSimilar;
	}
}

