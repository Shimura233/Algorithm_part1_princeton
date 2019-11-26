/* *****************************************************************************
 *  Name: Wei Wang
 *  Date: 06/11/2019
 *  Description: Assignment 2/Algorithms, part 1/Princetion
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("you must specify the item you want to add");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;


        if (!isEmpty()) {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        else {

            last = first;
        }
        size = size + 1;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("you must specify the item you want to add");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (!isEmpty()) {
            oldlast.next = last;
            last.prev = oldlast;
        }
        else {
            first = last;
        }
        size = size + 1;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item s = first.item;
        first = first.next;
        size = size - 1;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.prev = null;
        }
        return s;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item s = last.item;
        last = last.prev;
        size = size - 1;
        if (isEmpty()) {
            first = last;
        }
        else {
            last.next = null;
        }
        return s;
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item s = current.item;
            current = current.next;
            return s;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public static void main(String[] args) {
        int n = args.length;
        Deque<String> test = new Deque<String>();
        for (int i = 0; i < n; i++) {
            switch (args[i]) {
                case "-":
                    test.removeFirst();
                    break;
                case "--":
                    test.removeLast();
                    break;
                case "+":
                    i = i + 1;
                    test.addFirst(args[i]);
                    break;
                case "++":
                    i = i + 1;
                    test.addLast(args[i]);
                    break;
            }
        }
        for (String s : test) {
            System.out.print(s + " ");
        }
    }

}
