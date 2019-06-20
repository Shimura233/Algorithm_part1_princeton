public class DeleteRepetive<item> extends StackInList<item> {


    public void DeleteWithAllRepeat() {
        this.first = DeleteWithAllRepeat(this.first);
    }

    public void DeleteRemainOneWithRecur() {
        this.first = DeleteRemainOneWithRecur(this.first);
    }

    public void DeleteRemainOne() {
        this.first = DeleteRemainOne(this.first);
    }

    public Node DeleteWithAllRepeat(Node head) {
        if (head == null || head.next == null) return head;
        if (head.item == head.next.item) {
            while (head.item == head.next.item) {
                head = head.next;

            }
            return DeleteWithAllRepeat(head.next);
        } else {
            head.next = DeleteWithAllRepeat(head.next);
            return head;
        }
    }

    public Node DeleteRemainOneWithRecur(Node head) {
        if (head == null || head.next == null) return head;
        while (head.next.item == head.item) {
            head = head.next;
        }
        head.next = DeleteRemainOneWithRecur(head.next);
        return head;
    }

    public Node DeleteRemainOne(Node head) {
        if (head == null || head.next == null) return head;
        StackInList.Node cur = head;
        while (cur != null && cur.next != null) {
            while (cur.next != null && cur.next.item == cur.item) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {

        DeleteRepetive<Integer> test1 = new DeleteRepetive<Integer>();
        DeleteRepetive<Integer> test2 = new DeleteRepetive<Integer>();
        DeleteRepetive<Integer> test3 = new DeleteRepetive<Integer>();
        for (int i = 0; i < args.length; i++) {

            test1.push(Integer.parseInt(args[i]));
            test2.push(Integer.parseInt(args[i]));
            test3.push(Integer.parseInt(args[i]));
        }
        test1.DeleteRemainOne();
        test2.DeleteRemainOneWithRecur();
        test3.DeleteWithAllRepeat();
        System.out.println("this is deleteremainone");
        while (!test1.isEmpty()) {
            System.out.print(test1.pop() + " ");
        }
        System.out.println();
        System.out.println("DeleteRemainOneWithRecur");
        while (!test2.isEmpty()) {
            System.out.print(test2.pop() + " ");
        }
        System.out.println();
        System.out.println("DeleteAll");
        while (!test3.isEmpty()) {
            System.out.print(test3.pop() + " ");
        }
    }
}
