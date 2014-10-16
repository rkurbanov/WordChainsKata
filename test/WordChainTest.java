import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WordChainTest {

	private WordChain wc;
	private ChainDictionary dict;

	@Before
	public void setUp() {
		dict = new ChainDictionary();

		wc = new WordChain(dict);
	}

	@Test(expected = WordChainException.class)
	public void testExceptionIfOriginOrTargetWordsAreNull() throws Exception {
		wc.chain(null, null);
	}

	@Test(expected = WordChainException.class)
	public void testExceptionIfOriginOrTargetWordsAreEmpty() throws Exception {
		wc.chain("", " ");
	}

	@Test(expected = WordChainException.class)
	public void testExceptionIfWordLengthsAreNotEqual() throws Exception {
		wc.chain("a", "an");
	}

	@Test(expected = WordChainException.class)
	public void testExceptionIfWordsDoesntExistInDictionary() throws Exception {
		wc.chain("q", "w");
	}

	@Test
	public void testResultWhenThereIsNoChain() throws Exception {
		String origin = "yeah";
		String target = "nope";

		dict.addWord(origin);
		dict.addWord(target);

		List<String> result = wc.chain(origin, target);

		assertTrue(result.isEmpty());
	}

	@Test
	public void testResultWhenTheOriginAndTargetWordsAreSame() throws Exception {
		dict.addWord("a");

		List<String> result = wc.chain("a", "a");

		assertTrue(result.isEmpty());
	}

	@Test
	public void testResultWhenItsOneLetterWords() throws Exception {
		String origin = "a";
		String target = "i";

		dict.addWord(origin);
		dict.addWord(target);

		List<String> result = wc.chain(origin, target);

		assertEquals(2, result.size());
	}

	@Test
	public void testThatThereIsChainBetween2AdjacentWords() throws Exception {
		String origin = "yes";
		String target = "yep";

		dict.addWord(origin);
		dict.addWord(target);

		List<String> result = wc.chain(origin, target);

		List<String> expectedList = Arrays
				.asList(new String[] { origin, target });
		assertEquals(expectedList, result);
	}

	@Test
	public void testThatThereIsChainOfExpectedLength() throws Exception {
		String origin = "top";
		String intermediate1 = "cop";
		String intermediate2 = "cap";
		String target = "cat";

		dict.addWord(origin);
		dict.addWord(intermediate1);
		dict.addWord(intermediate2);
		dict.addWord(target);

		List<String> result = wc.chain(origin, target);

		List<String> expectedList = Arrays.asList(new String[] { origin,
				intermediate1, intermediate2, target });
		assertEquals(expectedList, result);
	}
	
	@Test
	public void testThatChainReturnsShortestChain() throws Exception {
		String origin ="cat";
		String target = "dog";
		String shorterChain1 = "cot";
		String shorterChain2 = "dot";
		String longerChain1 = "mat";
		String longerChain2 = "dat";
		String longerChain3 = "dag";
		
		dict.addWord(origin);
		dict.addWord(target);
		dict.addWord(shorterChain1);
		dict.addWord(shorterChain2);
		dict.addWord(longerChain1);
		dict.addWord(longerChain2);
		dict.addWord(longerChain3);
		
		List<String> expectedList = Arrays.asList(new String[]{origin, shorterChain1, shorterChain2, target});
		
		List<String> result = wc.chain(origin, target);
		
		assertEquals(4, result.size());
		assertEquals(expectedList, result);
		
	}
}
