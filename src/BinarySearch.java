/**
 * Created by zplchn on 7/6/16.
 */
public class BinarySearch {

    //33 search in rotated sorted array no dup
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;

        while (l <= r){
            m = l + ((r - l) >> 1);
            if (nums[m] == target)
                return m;
            else if (nums[m] < nums[r]){
                if (target > nums[m] && target <= nums[r])
                    l = m + 1;
                else
                    r = m - 1;
            }
            else {
                if (target >= nums[l] && target < nums[m])
                    r = m - 1;
                else
                    l = m + 1;
            }
        }
        return -1;
    }

    //81 search in rotated sorted array dup possible
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int l = 0, r = nums.length - 1, m;

        while (l <= r){
            m = l + ((r-l) >> 1);
            if (nums[m] == target)
                return true;
            if (nums[m] > nums[r]){
                if (target >= nums[l] && target < nums[m])
                    r = m - 1;
                else
                    l = m + 1;
            }
            else if (nums[m] < nums[r]){
                if (target > nums[m] && target <= nums[r])
                    l = m + 1;
                else
                    r = m - 1;
            }
            else
                r--; // if m == r, no way to tell the dup on left or right, 11131 13111
        }
        return false;

    }

    //35
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;
        while (l <= r){
            m = l + ((r-l) >> 1);
            if (target > nums[m])
                l = m + 1;
            else if (target < nums[m])
                r = m - 1;
            else
                return m;
        }
        return l;
    }

    //278
    public int firstBadVersion(int n) {
        if (n < 1)
            return -1;
        int l = 1, r = n, m;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (isBadVersion(m))
                r = m - 1;
            else
                l = m + 1;
        }
        return l;
    }
    //dummy isBadver
    private boolean isBadVersion(int i){
        return i == 0;
    }

    //153
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;

        while (l <= r){
            m = l + ((r-l) >> 1);
            if (nums[m] >= nums[r])
                l = m + 1;
            else
                r =m;
        }
        return r;
    }




















    public static void main(String[] args){
        BinarySearch bs = new BinarySearch();
        int[] t = {3,1};
        //bs.search(t, 1);
    }
}
