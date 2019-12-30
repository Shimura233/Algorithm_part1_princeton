package rbbst;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestInterval {
	String[] document;
	String[] words;
	public ShortestInterval(String[] document, String[] words) {
		this.document = document;
		this.words = words;
	}
	public int getInterval() {
		int i = 0;
		HashMap<String,Queue<Integer>> dict = new HashMap<>();
		for (String word:document) {
			Queue<Integer> tmp = dict.getOrDefault(word, new LinkedList<Integer>());
			tmp.add(i ++);
			dict.put(word, tmp);
		}
		Queue<Integer>[] queries = new Queue[words.length];
		i = 0;
		for (String word:words) {
			if (!dict.containsKey(word)) return -1;
			queries[i ++] = dict.get(word);
		}
		int leastinterval = -1;
		tofind:
		while (!queries[0].isEmpty()) {
			int lo = queries[0].poll();
			int hi = lo;
			for (int j = 1; j < i; j ++) {
				while (!queries[j].isEmpty() && queries[j].peek() <= hi) queries[j].poll();
				if (queries[j].isEmpty()) {
					break tofind;
				} else {
					hi = queries[j].peek();
				}
			}
			if (leastinterval == -1 || hi - lo + 1 < leastinterval) leastinterval = hi - lo + 1;
		}
		return leastinterval;
	}
	public static void main(String[] args) {
		ShortestInterval test = new ShortestInterval(new String[] {"what", "do", "you", "do", "get", "if", "do", "that", "get"}, new String[] {"tt"});
		System.out.println(test.getInterval());
	}
}
