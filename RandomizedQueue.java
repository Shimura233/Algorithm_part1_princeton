/* *****************************************************************************
 *  Name: Wei Wang
 *  Date: 06/11/2019
 *  Description: assignment 2
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private int first;
    private int last;
    private Item[] q;

    public RandomizedQueue() {
        size = 0;
        first = 0;
        last = 0;
        q = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("you must specify the item you want to add");
        }
        if (size == q.length) {
            resize(2 * size);
        }
        q[last] = item;
        last = last + 1;
        if (last == q.length) last = 0;
        size = size + 1;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] newq = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newq[i] = q[(first + i) % q.length];
        }
        q = newq;
        first = 0;
        last = size;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int ind = StdRandom.uniform(size);
        ind = (first + ind) % q.length;
        Item temp = q[ind];
        q[ind] = q[first];
        q[first] = null;
        first = first + 1;
        if (first == q.length) first = 0;
        size = size - 1;
        if (size > 0
                && size == q.length / 4) { //>0 is important, or will cause an array with length 0
            resize(q.length / 2);
        }
        return temp;

    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int ind = StdRandom.uniform(size);
        ind = (first + ind) % q.length;
        return q[ind];
    }

    public Iterator<Item> iterator() {
        return new RandIterator();
    }

    private class RandIterator implements Iterator<Item> {
        private Item[] forite = (Item[]) new Object[size];
        private int current = 0;
        private int tosample = size;

        public RandIterator() {
            for (int i = 0; i < size; i++) {
                forite[i] = q[(first + i) % q.length];
            }
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int ind = StdRandom.uniform(tosample);
            ind = current + ind;
            Item temp = forite[ind];
            forite[ind] = forite[current];
            current = current + 1;
            tosample = tosample - 1;
            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
