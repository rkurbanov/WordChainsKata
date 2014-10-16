import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class WordChain {
	ChainDictionary dictionary;
	Map<String, String> pathMap = new HashMap<String, String>();

	public WordChain(ChainDictionary dictionary) {
		this.dictionary = dictionary;
	}

	public List<String> chain(String origin, String target) throws WordChainException {
		validateInput(origin, target);
		
		return extractChain(origin, target);
	}

	private List<String> extractChain(String origin, String target) {
		Stack<String> stack = new Stack<String>();

		if (hasChain(origin, target)) {
			String current = target;
			stack.add(current);
			while (!origin.equalsIgnoreCase(current)) {
				current = pathMap.get(current);
				stack.add(current);
			}
		}
		return stackToList(stack);
	}

	private List<String> stackToList(Stack<String> stack) {
		List<String> chain = new ArrayList<String>();
		while (!stack.isEmpty()) {
			chain.add(stack.pop());
		}
		return chain;
	}

	private boolean hasChain(String source, String target) {
		boolean hasChain = false;
		List<String> visited = new ArrayList<String>();

		visited.add(source);

		Queue<String> queue = new LinkedList<String>();
		queue.add(source);

		while (!queue.isEmpty()) {
			String current = queue.poll();
			for (String word : dictionary.getNeighbors(current)) {
				if (!visited.contains(word)) {
					pathMap.put(word, current);
					visited.add(word);
					if (word.equalsIgnoreCase(target)) {
						hasChain = true;
					}
					queue.add(word);
				}
			}
		}

		return hasChain;
	}

	private void validateInput(String origin, String target) throws WordChainException {
		if (null == origin || null == target || "".equals(origin.trim()) || "".equals(target.trim()))
			throw new WordChainException();

		if (origin.length() != target.length())
			throw new WordChainException();

		if (!dictionary.contains(origin) || !dictionary.contains(target))
			throw new WordChainException();
	}
}
