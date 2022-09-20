package AG10;

import java.util.LinkedList;

public class AG10 {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;
        removeNthFromEnd(listNode1,2);

    }
    //还是双指针实现一下吧
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode index1  = head;
        ListNode index2;
        //index1先走n步
        int count  = 0;
        while (true){
            if (index1.next!=null) {
                index1=index1.next;
                count++;
                if (count==n) break;
            }else break;
        }
        if (count<n) return head.next;
        index2 =head;
        if (index1.next==null){
            head.next=null;
            return head;
        }else index1=index1.next;
        while (true){
            if (index1.next!=null){
                index1=index1.next;
                index2 = index2.next;
            }else {
                index2 = index2.next;
                index2.next = index2.next.next;
                return head;
            }
        }

    }


}
