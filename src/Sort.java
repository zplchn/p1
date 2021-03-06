import java.util.*;

/**
 * Created by zplchn on 6/26/16.
 */
public class Sort {
    /***
     * Merge Sort - Divide and Conquer o(nlogn)
     */

    public void mergeSort(int [] a){
        mergeSort(a, new int[a.length], 0, a.length - 1);
    }

    private void mergeSort(int[] a, int [] tmp, int l, int r){
        if (l < r){
            int m = (l + r) >> 1;
            mergeSort(a, tmp, l, m);
            mergeSort(a, tmp, m + 1, r);
            merge(a, tmp, l, m, m + 1, r);
        }
    }

    private void merge(int[] a, int[] tmp, int ll, int lr, int rl, int rr){
        int k = ll, t = ll;
        while (ll <= lr && rl <= rr){
            tmp[k++] = a[ll] < a[rl] ? a[ll++] : a[rl++];
        }
        while (ll <= lr){
            tmp[k++] = a[ll++];
        }
        while (rl <= rr){
            tmp[k++] = a[rl++];
        }
        while (t <= rr){
            a[t] = tmp[t++];
        }
    }

    //41
    public int firstMissingPositive(int[] nums) {
        //like counting sort
        if (nums == null || nums.length == 0)
            return 1;
        //make sure A[i] = i+1, put A[A[i] -1] to the right position and swap back the one at that spot.
        for (int i = 0; i < nums.length; ++i){
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                int t = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = t;
                --i; //when swap back, dont move pointer and check
            }
        }
        for (int i = 0; i < nums.length; ++i)
            if (nums[i] != i+1)
                return i+1;
        return nums.length+1;

    }

    //56
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return res;
        intervals.sort((i1, i2) -> i1.start - i2.start);

        res.add(intervals.get(0));
        for (int i = 0; i < intervals.size(); ++i){
            if (res.get(res.size()-1).end >= intervals.get(i).start)
                res.get(res.size()-1).end = Math.max(res.get(res.size()-1).end, intervals.get(i).end);
            else
                res.add(intervals.get(i));
        }
        return res;
    }

    //57
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || newInterval == null)
            return res;
        int i = 0;
        while (i < intervals.size() && newInterval.start > intervals.get(i).end) {
            res.add(intervals.get(i++));
        }

        while (i < intervals.size() && newInterval.end >= intervals.get(i).start){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            ++i; //possibly absort nothing if the interval.end is smaller than the next where it's inserted
        }
        res.add(newInterval);

        while (i < intervals.size())
            res.add(intervals.get(i++));

        return res;
    }

    //148
    public ListNode sortList(ListNode head) {
        //every time divide to two sub lists till reach single node. then merge two lists
        if (head == null || head.next == null)
            return head;
        ListNode slow, fast;
        slow = fast = head;
        //here need to end the first list. so loop condition need to pay attention, stop before head of next to set null to last left
        //1-2-3    1-2
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;

        //divide
        ListNode lhead = sortList(head);
        ListNode rhead = sortList(head2);

        //merge
        return merge(lhead, rhead);
    }

    private ListNode merge(ListNode l, ListNode r){
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;

        while (l != null && r != null){
            if (l.val < r.val){
                pre.next = l;
                l = l.next;
            }
            else {
                pre.next = r;
                r = r.next;
            }
            pre = pre.next;
        }
        if (l != null){
            pre.next = l;
        }
        if (r != null)
            pre.next = r;
        return dummy.next;
    }

    //215
    // 3 solutions : 1. sort the array and get kth onlogn 2. quickselect avg on, worst on2 3. heap onlogk
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k)
            return 0;
        return findKthSmallest(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findKthSmallest(int[] nums, int k, int start, int end){
        if (start >= end)
            return nums[start];
        int pos = partition(nums, start, end);
        if (pos == k)
            return nums[pos];
        else if (pos < k)
            return findKthSmallest(nums, k, pos + 1, end);
        else
            return findKthSmallest(nums, k, start, pos - 1);
    }

    private int partition(int[] nums, int start, int end){
        int pivot = nums[end];
        int l = start, r = end;//here r is the last since if the array is sorted 1,2,3 and r = 2 then l wouldnt be able to reach to the last
        while (l < r){
            while (l < r && nums[l] < pivot)
                ++l;
            while (l < r && nums[r] >= pivot)
                --r;
            swap(nums, l, r);
        }
        swap(nums, l, end); //get the pivot to the right positon in the final sorted array
        return l;
    }

    private void swap(int[] nums, int l, int r){
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }

    private int findKthLargestMinHeap(int[] nums, int k){
        //3. use min heap o(nlogk) space o(k)
        Queue<Integer> pq = new PriorityQueue<Integer>(k); //minheap
        for (int i : nums){
            pq.offer(i);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    //242
    public boolean isAnagram(String s, String t) {
        if (s == null)
            return t == null;
        if (t == null)
            return false;
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();

        Arrays.sort(sa);
        Arrays.sort(ta);

        return new String(sa).equals(new String(ta));

    }

    //252
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return true;
        Arrays.sort(intervals,(i1, i2) -> i1.start - i2.start);
        for (int i = 0; i < intervals.length - 1; ++i){
            if (intervals[i].end > intervals[i+1].start)
                return false;
        }
        return true;
    }

    //274
    public int hIndex(int[] citations) {
        //sort from largest to smallest, the first one that nums[i] <= i is the one break, h-index is 1-based, so return i
        if (citations == null || citations.length == 0)
            return 0;
        Arrays.asList(citations);
        Arrays.sort(citations);
        reverse(citations);

        for (int i = 0; i < citations.length; ++i){
            if (citations[i] <=i)
                return i;
        }
        return citations.length;
    }

    private void reverse(int[] a){
        int l = 0, r = a.length - 1;
        while(l < r){
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            ++l;--r;
        }
    }

    //280
    public void wiggleSort(int[] nums) {
        /* at odd, i > i-1. at even i < i-1.if violate, then swap i with i-1. like 35
        small, large, larger, then need to swap large with larger
         */
        if (nums == null || nums.length <= 1)
            return;
        for (int i = 1; i < nums.length; ++i){
            if ((i%2 == 1 && nums[i] < nums[i-1]) || (i %2 == 0 && nums[i] > nums[i-1])){
                int t = nums[i-1];
                nums[i-1] = nums[i];
                nums[i] = t;
            }
        }
    }


    public static void main(String[] args){

        Sort st = new Sort();
        int [] a = {};
        st.mergeSort(a);
        System.out.println(Arrays.toString(a));



    }










}
