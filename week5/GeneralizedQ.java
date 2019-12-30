package rbbst;

public class GeneralizedQ<item> {
private RBBST<Integer,item> items;
int N;
int size;
public int size() {
	return size;
}
public GeneralizedQ() {
	items = new RBBST<>();
}
public void add(item val) {
	items.put(N ++, val);
	size ++;
}
public item poll() {
	size --;
	return items.deleteMin();
}
public item removeIth(int i) {
	int k = items.select(i);
	size --;
	return items.delete(k);
}
public item getIth(int i) {
	int k = items.select(i);
	return items.get(k);
}
public static void main(String[] args) {
	GeneralizedQ<Integer> test = new GeneralizedQ<>();
	for (int i = 0; i < 10; i ++) {
		test.add(i);
	}
	System.out.println(test.poll());
	test.add(35);
	System.out.println(test.removeIth(0));
	System.out.println(test.removeIth(4));
	System.out.println(test.getIth(4));
	for (int i = 0; i < test.size(); i ++) {
		System.out.println(test.getIth(i));
	}
}
}
