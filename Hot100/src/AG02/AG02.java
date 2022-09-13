package AG02;

public class AG02 {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(3);
        ListNode l13 = new ListNode(6);
        l1.next=l12;
        l12.next = l13;
        ListNode l2 = new ListNode(2);
        ListNode l21 = new ListNode(7);
        l2.next=l21;
        ListNode listNode = addTwoNumbers(l1, l2);
//            2--->4--->3--->4--->
//            5--->6--->4--->0--->
//            7-->0--->8--->4
        //     23
        //     23
//        System.out.println(lengthOfLongestSubstring("asss"));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryOver = 0;
        ListNode start = new ListNode();
        ListNode container = start;
        while (true){
            container.val = (carryOver+l1.val+l2.val)%10;
            carryOver=(carryOver+l1.val+l2.val)/10;
            if (l1.next==null&&l2.next==null) break;
            if (l1.next==null) l1 = new ListNode(0);
            else l1=l1.next;
            if (l2.next==null) l2 = new ListNode(0);
            else l2=l2.next;
            ListNode next = new ListNode();
            container.next=next;
            container = next;
        }
        if (carryOver==1) container.next = new ListNode(1);
        return start;
    }
}
