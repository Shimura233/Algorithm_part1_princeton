public class StackWithMax extends StackInList<Double> {
    private Node maximum = null;

    public void push(Double s) {
        Node oldfirst = first;
        Node oldmax = maximum;
        first = new Node();
        maximum = new Node();
        first.item = s;
        if (oldmax == null) {
            maximum.item = s;
        } else {
            if (s > oldmax.item) {
                maximum.item = s;
            } else {
                maximum.item = oldmax.item;
            }
        }
        maximum.next = oldmax;
        first.next = oldfirst;
    }

    public Double pop() {
        Double s = first.item;
        first = first.next;
        maximum = maximum.next;
        return s;
    }

    public Double getMax() {
        return maximum.item;
    }

    public static void main(String[] args) {
        StackWithMax value = new StackWithMax();
        value.push(1.34);
        value.push(3.0);
        value.push(2.0);
        value.pop();
        value.push(4.0);
        System.out.println(value.getMax());
        value.pop();
        value.pop();
        System.out.println(value.getMax());
        value.push(0.768);
        value.push(4.5);
        System.out.println(value.getMax());
        while (!value.isEmpty()) System.out.print(value.pop() + " ");
    }
}
