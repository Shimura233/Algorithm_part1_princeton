public class StackInList<item> { //evaluate infix expression
    Node first = null;

    class Node {
        item item;
        Node next;
    }

    public void push(item s) {
        Node oldfirst = first;
        first = new Node();
        first.item = s;
        first.next = oldfirst;

    }

    public item pop() {
        item s = first.item;
        first = first.next;
        return s;
    }

    public boolean isEmpty() {
        return first == null;
    }

    static private void evaluateTopTwo(String op, StackInList<Double> value) {
        if (op.equals("+")) {
            value.push(value.pop() + value.pop());
        } else {
            if (op.equals("-")) {
                value.push(-(value.pop() - value.pop()));
            } else {
                if (op.equals("*")) {
                    value.push(value.pop() * value.pop());
                } else {
                    Double a = value.pop();
                    Double b = value.pop();
                    value.push(b / a);
                }
            }
        }
    }

    public static void main(String[] args) {
        StackInList<Double> value = new StackInList<Double>();
        StackInList<String> operand = new StackInList<String>();
        int n = args.length;
        String op;

        for (int i = 0; i < n; i++) {
            if (args[i].equals("(")) {

            } else {
                if (args[i].equals("+") || args[i].equals("-") || args[i].equals("*") || args[i].equals("/")) {
                    operand.push(args[i]);
                } else {
                    if (args[i].equals(")")) {
                        op = operand.pop();
                        evaluateTopTwo(op, value);
                    } else {
                        value.push(Double.parseDouble(args[i]));
                    }
                }

            }
        }
        op = operand.pop();
        evaluateTopTwo(op, value);
        System.out.print(value.pop());
    }
}
