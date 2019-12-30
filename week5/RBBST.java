package rbbst;

import java.util.LinkedList;
import java.util.Queue;

public class RBBST<Key extends Comparable<Key>, Value> {
    private class Node{
        boolean red;
         Key key;
         Value val;
         int N;
         Node left;
         Node right;
        Node(Key key, Value val, int N, boolean red) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.red = red;
        }

    }
    Node root;
    Node todelete;
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }
    public Value get(Key key) {
        return get(key, root);
    }
    private Value get(Key key, Node x) {
        if (x == null) return null;
        int cmp= key.compareTo(x.key);
        if (cmp < 0) {
            return get(key, x.left);
        } else {
            if (cmp > 0) {
                return get(key, x.right);
            } else {
                return x.val;
            }
        }
    }
    public void put(Key key, Value val) {
        root = put(key, val, root);
        root.red = false;
    }
    private Node put(Key key, Value val, Node x) {//put(key,value) into the subtree with the root x and return the root
      if (x == null) return new Node(key, val, 1,true);
      int cmp = key.compareTo(x.key);
      if (cmp == 0) {
          x.val = val;
          return x;
      } else {
          if (cmp < 0) {
              x.left = put(key, val, x.left);
          } else {
              x.right = put(key, val, x.right);
          }
      }
        x.N = size(x.right) + size(x.left) + 1;
        if (!isRed(x.left) && isRed(x.right)) {
          x = rotateLeft(x);
      }
      if (isRed(x.left) && isRed(x.left.left)) {
          x = rotateRight(x);
      }
      if (isRed(x.left) && isRed(x.right)) {
          flipColor(x);
      }
      return x;
    }
    private Node rotateLeft(Node x) {
        Node torotate = x;
        x = torotate.right;
        torotate.right = x.left;
        x.left = torotate;
        boolean tmp = x.red;
        x.red = torotate.red;
        torotate.red = tmp;
        x.N = torotate.N;
        torotate.N = size(torotate.left) + size(torotate.right) + 1;
        return x;
    }
    private Node rotateRight(Node x) {
        Node torotate = x;
        x = torotate.left;
        torotate.left = x.right;
        x.right = torotate;
        boolean tmp = x.red;
        x.red = torotate.red;
        torotate.red = tmp;
        x.N = torotate.N;
        torotate.N = size(torotate.left) + size(torotate.right) + 1;
        return x;
    }
    private void flipColor(Node x) {
        x.left.red = !x.left.red;
        x.right.red = !x.right.red;
        x.red = ! x.red;
    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.red;
    }
    public Key max() {
        Node tmp = max(root);
        return (tmp == null)? null:tmp.key;
    }
    private Node max(Node x) {
        if (x == null || x.right == null) return x;
        else return max(x.right);
    }
    public Key min() {
        Node tmp = min(root);
        return (tmp == null)? null:tmp.key;
    }
    private Node min(Node x) {
        if (x == null || x.left == null) return x;
        else return min(x.left);
    }
    public Key floor(Key target) {
        Node tmp = floor(target, root);
        return (tmp == null)? null:tmp.key;
    }
    private Node floor(Key target, Node x) {
        if (x == null) return null;
        int cmp = target.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else {
            if (cmp < 0) {
                return floor(target, x.left);
            } else {
                Node tmp = floor(target, x.right);
                return (tmp == null)? x:tmp;
            }
        }
    }
    public Key ceiling(Key target) {
        return ceiling(target, root);
    }
    private Key ceiling(Key target, Node x) {
        if (x == null) return null;
        int cmp = target.compareTo(x.key);
        if (cmp == 0) {
            return x.key;
        } else {
            if (cmp > 0) {
                return ceiling(target, x.right);
            } else {
                Key tmp = ceiling(target, x.left);
                return (tmp == null)? x.key:tmp;
            }
        }
    }
    public Key select(int k) {
        if (k < 0 || k >= size(root)) return null;
        Node tmp = select(k, root);
        return tmp.key;
    }
    private Node select(int k, Node x) {
        int less = size(x.left);
        if (less == k) {
            return x;
        } else {
            if (less < k) {
                return select(k - less - 1, x.right);
            } else {
                return select(k, x.left);
            }
        }
    }
    public int rank(Key key){//num of keys less than key
        return rank(key, root);
    }
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return size(x.left);
        } else {
            if (cmp > 0) {
                return size(x.left) + 1 + rank(key, x.right);
            } else {
                return rank(key, x.left);
            }
        }
    }

    public Value deleteMin() {
        root = deleteMin(root);
        if (root != null)
        root.red = false;
        Value result = (todelete == null)? null:todelete.val;
        todelete = null;
        return result;
    }
    private Node deleteMin(Node x) {
        if (x == null || x.left == null) {
        	todelete = x;
            return null;
        }
     if (isTwo(x.left)) {
          x = generateThreeLeft(x);
      }
      x.left = deleteMin(x.left);
     x.N = size(x.left) + size(x.right) + 1;
     if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
      if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
      if (isRed(x.left) && isRed(x.right)) flipColor(x);
      return x;
    }
    private Node generateThreeLeft(Node x) {
        if (isTwo(x.right)) {
            flipColor(x);
        } else {
            flipColor(x);
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColor(x);
        }
        return x;
    }
private boolean isTwo(Node x) {
        if (x == null) return true;
        return !isRed(x) && !isRed(x.left);
}
    public Value deleteMax() {
        root = deleteMax(root);
        if (root != null) root.red = false;
        Value result = (todelete == null)? null:todelete.val;
        todelete = null;
        return result;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left) && !isRed(x.right)) {//一律右倾，简单化到一种形式
            x = rotateRight(x);
        }
        if (x == null || x.right == null) {
        	todelete = x;
            return null;
        }
        if (isTwo(x.right)) {
          x = generateThreeRight(x);
        }
        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        if (!isRed(x) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);
        return x;
    }

    private Node generateThreeRight(Node x) {
        if (isTwo(x.left)) {
            flipColor(x);
        } else {
            flipColor(x);
            x = rotateRight(x);
            flipColor(x);
        }
        return x;
    }

    public Value delete(Key key) {
        root = delete(key, root);
        if (root != null) root.red = false;
        Value result = (todelete == null)? null:todelete.val;
        todelete = null;
        return result;
    }


    private Node delete(Key target, Node x) {
        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(target);
        if (cmp > 0) {
            if (isTwo(x.left)){
                x = generateThreeLeft(x);
            }
            x.left = delete(target, x.left);
        } else {

                if (isRed(x.left) && !isRed(x.right)) {
                    x = rotateRight(x);
                    x.right = delete(target, x.right);
                } else {
                    if (x.right == null) {
                        if (cmp == 0){
                        	todelete = x;
                            return null;
                        } else {
                            return x;
                        }
                    }
                    if (isTwo(x.right)) {
                        x = generateThreeRight(x);
                    }
                    if (x.key.compareTo(target) == 0) {
                    	x.right = deleteMin(x.right);
                    	todelete.right = x.right;
                    	todelete.left = x.left;
                    	todelete.red = x.red;
                       Node tmp = x;
                       x = todelete;
                       todelete = tmp;
                       todelete.left = null;
                       todelete.right = null;
                    } else {
                        x.right = delete(target, x.right);
                    }
                }
        }
       x.N = size(x.right) + size(x.left) + 1;
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);
        return x;
    }


    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        keys(root, q);
        return q;
    }
    public Iterable<Key> keys(Key lo,  Key hi) {
        Queue<Key> q = new LinkedList<>();
        keys(lo, hi, root, q);
        return q;
    }
    private void keys(Node x, Queue<Key> q) {
        if (x == null) return;
        keys(x.left, q);
        q.add(x.key);
        keys(x.right, q);
    }
    private void keys(Key lo, Key hi, Node x, Queue<Key> q) {
        if (x == null) return;
        int cmplo = x.key.compareTo(lo);
        if (cmplo < 0) {
            keys(lo, hi, x.right, q);
        } else {
            int cmphi = x.key.compareTo(hi);
            if (cmphi > 0) {
                keys(lo, hi, x.left, q);
            } else {
                keys(lo, hi, x.left, q);
                keys(lo, hi, x.right, q);
                q.add(x.key);
            }
        }
    }
    public String drawTree() {
        return drawTree(root);
    }
    private String drawTree(Node x) {
        StringBuilder s = new StringBuilder();
        if (x == null) return "null_black,";
        Queue<Node> q = new LinkedList<>();
        q.add(x);
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            if (tmp == null) {
                s.append("null_black,");
            } else {
                s.append(tmp.key + "_" + ((tmp.red)? "red":"black") + ",");
                q.add(tmp.left);
                q.add(tmp.right);
            }
        }
        return s.toString();
    }
    public static void main(String[] args) {
        Character[] input = {'E','A','S','Y','Q','U','T','I','O','N'};
        RBBST<Character,Integer> test = new RBBST<>();
       for (int i = 0; i < input.length; i ++) {
           test.put(input[i],i);
           System.out.println(test.drawTree());
       }
       Character[] todelete = {'Y','U','A','E','S'};
       for (int i = 0; i < todelete.length; i ++) {
           test.delete(todelete[i]);
           System.out.println(test.drawTree());
       }
    }
}

