/**
 * Created by zplchn on 7/4/16.
 */
public class LinkedList {

    //2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;

        while (l1 != null || l2!= null || carry != 0){
            int sum = (l1!= null?l1.val:0) + (l2!= null?l2.val:0) + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            l1 = l1!=null? l1.next : l1;
            l2 = l2!=null? l2.next : l2;
        }
        cur.next = null;
        return dummy.next;
    }

    //21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode dummy = new ListNode(1);
        ListNode cur = dummy;

        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;;
        }
        if (l1 != null)
            cur.next = l1;
        else if (l2 != null)
            cur.next = l2; // one ends, still need to connect the cur
        return dummy.next;
    }

    //138

    //141
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode slow, fast;
        slow = fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    //142
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return head;
        boolean isCyclic = false;
        ListNode slow, fast;
        slow = fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                isCyclic = true;
                break;
            }
        }
        if (!isCyclic)
            return null;
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode la = headA, lb = headB;
        int na = 0, nb = 0;

        while (la != null){
            la = la.next;
            na++;
        }
        while (lb != null){
            lb = lb.next;
            nb++;
        }
        la = headA;
        lb = headB;
        int diff = na - nb;
        if (diff > 0){
            while (diff-- > 0)
                la = la.next;
        }
        else {
            diff = -diff;
            while (diff-- > 0)
                lb = lb.next;
        }
        while (la != null && la.val != lb.val){
            la = la.next;
            lb = lb.next;
        }
        return la;
    }



    //203
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;

        while (cur.next != null){
            if (cur.next.val == val){
                cur.next = cur.next.next;
            }
            else
                cur = cur.next;
        }
        return dummy.next;
    }

    //206
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = head, cur = head.next;
        pre.next = null;

        while (cur != null){
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;

    }

    //237
    public void deleteNode(ListNode node) {
        if (node == null)
            return;
        node.val = node.next.val;
        node.next = node.next.next;
    }



































}
