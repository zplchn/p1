import java.util.HashMap;
import java.util.Map;

/**
 * Created by zplchn on 6/9/16.
 */
public class Jun16 {

    public int[] twoSum(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length < 2) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int sum, carry = 0;

        while (l1 != null || l2 != null || carry!= 0) {
            sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }
        return dummy.next;

    }







}
