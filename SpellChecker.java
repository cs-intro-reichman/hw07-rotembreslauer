
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
		int length1 = word1.length();
		int length2 = word2.length();
		if (length1 == 0){
			return length1;
		} else if(length2 == 0){
			return length2;
		}else if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		} else {
			return 1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2)));
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
		
		// the word to return
		int min = threshold +1;
		String mostSimiler = word;
		// this loop runs on the dictionary and checks the distance between the input
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word, dictionary[i]);
			// identifies the word with the minimum distance
			// checks whether this distance exceeds the given threshold
			if (distance < min) {
				min = distance;
				// if the distance is smaller than the threshold, the word is replaced
				mostSimiler = dictionary[i];
			}
		}
		// if the minimum distance found is bigger than the threshold,
		// this indicates that no word in the dictionary is sufficiently similar to the
		// input word.
		return mostSimiler;
	}
}


