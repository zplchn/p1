import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

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

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0)
            return head;
        ListNode dummy = new ListNode(0), left, right;
        dummy.next = head;
        left = right = dummy;
        while (n > 0 && right != null){
            right = right.next;
            --n;
        }
        if (right == null)
            return head;
        while (right.next != null){
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
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

    //23
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        Queue<ListNode> pq = new PriorityQueue<>((ln1, ln2) -> ln1.val - ln2.val);
        for (ListNode ln : lists)
            if (ln != null)
                pq.offer(ln);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()){
            ListNode ln = pq.poll();
            cur.next = ln;
            cur = cur.next;
            if (ln.next != null)
                pq.offer(ln.next);
        }
        cur.next = null;
        return dummy.next;
    }

    //24
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head;

        while (cur != null && cur.next != null){
            ListNode next = cur.next.next;
            pre.next = cur.next;
            cur.next.next = cur;
            pre = cur;
            cur = next;
        }
        pre.next = cur;
        return dummy.next;
    }

    //25
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head, pre = dummy, left = dummy;
        while(cur != null){
            int i = 0;
            ListNode t = cur;
            while (i < k && t != null){
                t =t.next;
                ++i;
            }
            if (i < k)
                break;
            while (cur!=t){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            ListNode tt = left.next;
            left.next.next = cur;
            left.next = pre;
            left = tt;
        }
        return dummy.next;
    }

    //61
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return head;
        //first get length of list
        int n = 0;
        ListNode cur = head, right;
        while (cur != null){
            cur = cur.next;
            ++n;
        }
        k %= n;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        cur = right = dummy;
        while (k-- > 0){
            right = right.next;
        }
        while (right != null && right.next != null){
            cur = cur.next;
            right = right.next;
        }
        right.next = head;
        dummy.next = cur.next;
        cur.next = null;
        return dummy.next;

    }

    //82
    public ListNode deleteDuplicatesDeleteDupItself(ListNode head) {
        if(head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l = head, r, cur = dummy;
        while (cur != null && cur.next != null){
            r = l.next;
            while (r != null && r.val == l.val)
                r = r.next;
            if (r == l.next){
                cur =cur.next;
            }
            l = r;
            cur.next = l;
        }
        return dummy.next;
    }

    //83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode w = head, r = head.next, dummy = new ListNode(0);
        dummy.next = head;
        while (r != null){
            if (w.val != r.val){
                w.next = r;
                w = w.next;
            }
            r = r.next;
        }
        w.next = null; //this must be set to end the final list
        return dummy.next;
    }

    //86
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return head;
        ListNode smallHead = new ListNode(0), bigHead = new ListNode(0);
        ListNode cur = head, sm = smallHead, bg = bigHead;
        while (cur != null){
            if (cur.val < x){
                sm.next = cur;
                sm = sm.next;
            }
            else {
                bg.next = cur;
                bg = bg.next;
            }
            cur = cur.next;
        }
        if (smallHead.next == null)
            return bigHead.next;
        sm.next = bigHead.next;
        bg.next = null; // like 2,1 and given 2, need to end the big part
        return smallHead.next;
    }

    //92
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int c = n - m;
        while (m-- > 1){
            pre = pre.next;
        }
        ListNode left = pre, cur = pre.next, t;
        pre = null;
        while (c-- >= 0){
            t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        left.next.next = cur;
        left.next = pre;
        return dummy.next;
    }

    //138
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return head;

        //copy and make it like 1-1'-2-2'
        RandomListNode cur = head;
        while (cur != null){
            RandomListNode t = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur.next.next = t;
            cur = t;
        }
        cur = head;
        //back to head and then link random
        while (cur != null){
            cur.next.random = cur.random != null ? cur.random.next : cur.random;
            cur = cur.next.next;
        }

        //seperate into two ll
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        cur = head;

        while (cur != null){
            pre.next = cur.next;
            pre = pre.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummy.next;
    }

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

    //143
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        //1. find mid point
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //slow.next = null; here should not se the slow's next to null cuz then cannot go on the second half
        //2. reverse the second part
        ListNode pre = null, cur = slow;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //3. interleaving
        cur = head;
        while (cur != slow){ // remember
            ListNode t = cur.next, s = pre.next;
            cur.next = pre;
            pre.next = t;
            cur = t;
            pre = s;
        }
        cur.next = null; // when 1-2. need to do this again. the 2 point to itself
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

    //234
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true; // null treats as palindrome
        //1. find mid point
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = null, cur = slow;
        //2. reverse
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //3. compare
        cur = head;
        while (cur != slow){
            if (pre.val != cur.val)
                return false;
            pre = pre.next;
            cur = cur.next;
        }
        return true;

    }

    //237
    public void deleteNode(ListNode node) {
        if (node == null)
            return;
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        LinkedList ll = new LinkedList();
        ll.reorderList(head);
    }



































}
