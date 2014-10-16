import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChainDictionaryTest {
	ChainDictionary dict = new ChainDictionary();

	@Test
	public void testAddWordShouldAddWords() throws Exception {
		dict.addWord("a");

		assertTrue(dict.contains("a"));
	}

	@Test
	public void testAddWordDontAddDuplicates() throws Exception {
		dict.addWord("a");
		dict.addWord("a");

		assertTrue(dict.contains("a"));
	}

	@Test
	public void testWordDoesntHaveNeighbors() throws Exception {
		dict.addWord("a");

		assertTrue(dict.getNeighbors("a").isEmpty());
	}

	@Test
	public void testAddindTwoSingleLetterWordsShouldMakeThemNeighbors() throws Exception {
		dict.addWord("a");
		dict.addWord("b");

		assertTrue(dict.getNeighbors("a").contains("b"));
		assertTrue(dict.getNeighbors("b").contains("a"));
	}

	@Test
	public void testThatTwoWordsWithDifferentLengthWillNotBeNeighbors() throws Exception {
		dict.addWord("a");
		dict.addWord("ab");

		assertFalse(dict.getNeighbors("a").contains("ab"));
		assertFalse(dict.getNeighbors("ab").contains("a"));

	}

	@Test
	public void testThatTwoWordsWithMoreThanTwoDifferencesAreNotNeighbors() throws Exception {
		dict.addWord("ax");
		dict.addWord("no");

		assertFalse(dict.getNeighbors("ax").contains("no"));
		assertFalse(dict.getNeighbors("ax").contains("no"));
	}

	@Test
	public void testThatWordsWithOneDifferenceAreNeighbors() throws Exception {
		String firstWord = "abcdefg";
		String secondWord = "bbcdefg";

		dict.addWord(firstWord);
		dict.addWord(secondWord);

		assertTrue(dict.getNeighbors(firstWord).contains(secondWord));
		assertTrue(dict.getNeighbors(secondWord).contains(firstWord));
	}

	@Test
	public void testAddingMoreThan1Neighbor() throws Exception {
		String word = "abcdefg";
		String firstNeighbor = "bbcdefg";
		String secondNeighbor = "cbcdefg";

		dict.addWord(word);
		dict.addWord(firstNeighbor);
		dict.addWord(secondNeighbor);

		assertTrue(dict.getNeighbors(word).contains(firstNeighbor));
		assertTrue(dict.getNeighbors(word).contains(secondNeighbor));
	}

	@Test
	public void testThatAddNewWordIsNotCaseSensitive() throws Exception {
		String first = "ax";
		String second = "Am";

		dict.addWord(first);
		dict.addWord(second);

		assertTrue(dict.getNeighbors(first).contains(second));
		assertTrue(dict.getNeighbors(second).contains(first));
	}
}
