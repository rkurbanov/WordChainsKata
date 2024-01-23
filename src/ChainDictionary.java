import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChainDictionary {
	private Map<String, List<String>> dictionary = new HashMap<String, List<String>>();

	// To add word
	public void addWord(String word) {
		if (dictionary.containsKey((word)))
			return;

		dictionary.put(word, new ArrayList<String>());
		for (String wordInDictionary : dictionary.keySet()) {
			if (!word.equalsIgnoreCase(wordInDictionary) && checkIfNeighbors(word, wordInDictionary)) {
				addNeighbor(wordInDictionary, word);
				addNeighbor(word, wordInDictionary);
			}
		}
	}

	public boolean contains(String word) {
		return dictionary.containsKey(word);
	}

	public List<String> getNeighbors(String source) {
		return dictionary.get(source);
	}

	private void addNeighbor(String word, String neighbor) {
		List<String> neighborsList = dictionary.get(word);
		if (null == neighborsList) {
			neighborsList = new ArrayList<String>();
		}
		neighborsList.add(neighbor);
		dictionary.put(word, neighborsList);
	}

	private boolean checkIfNeighbors(String a, String b) {
		if (a.length() != b.length())
			return false;

		String aL = a.toLowerCase();
		String bL = b.toLowerCase();
		int nDifferences = 0;
		for (int i = 0; i < aL.length(); i++) {
			if (aL.charAt(i) != bL.charAt(i))
				++nDifferences;
		}
		return nDifferences <= 1;
	}
}
