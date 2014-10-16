import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		ChainDictionary dict = new ChainDictionary();
		WordChain wc = new WordChain(dict);

		bootstrapData(dict);

		List<String> chain = null;
		try {
			chain = wc.chain("ruby", "code");
			prettyPrint(chain);
		} catch (WordChainException e) {
			System.out.println("Not valid arguments");
		}

	}

	private static void prettyPrint(List<String> chain) {
		Iterator<String> iterator = chain.iterator();
		System.out.print(iterator.next());
		while (iterator.hasNext()) {
			System.out.print(" -> " + iterator.next());
		}
	}

	private static void bootstrapData(ChainDictionary dict) {
		dict.addWord("ruby");
		dict.addWord("rubs");
		dict.addWord("robs");
		dict.addWord("rods");
		dict.addWord("rode");
		dict.addWord("code");
	}
}
