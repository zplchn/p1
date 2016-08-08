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

    //34
    public int[] searchRange(int[] nums, int target) {
        int[]  res = {-1, -1};
        if (nums == null || nums.length == 0)
            return res;
        int l = 0, r = nums.length -1, m;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (nums[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        int lmost = l;
        l = 0;
        r = nums.length - 1;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (nums[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        if (lmost > r) //here should not include =
            return res;
        res[0] = lmost;
        res[1] = r;
        return res;
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

    //74
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int n = matrix[0].length - 1, l = 0, r = matrix.length-1, m;
        while (l < r){
            m = l + ((r - l) >> 1);
            if (matrix[m][n] == target)
                return true;
            else if (matrix[m][n] < target)
                l = m + 1;
            else
                r = m;
        }
        int t = l;
        l = 0;
        r = n;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (target == matrix[t][m])
                return true;
            else if (target > matrix[t][m])
                l = m + 1;
            else
                r = m - 1;
        }
        return false;
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
    public int findMinNoDup(int[] nums) {
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

    //154
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;
        while (l < r){
            m = l + ((r-l) >> 1);
            if (nums[m] == nums[r])
                --r;
            else if (nums[m] < nums[r])
                r = m;
            else
                l = m + 1;
        }
        return nums[l];
    }

    //162
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;
        while (l <= r){
            m = l + ((r-l) >> 1);
            if ((m == 0 || nums[m] > nums[m - 1]) && (m == nums.length - 1 || nums[m] > nums[m + 1]))//found peak
                return m;
            else if (m != 0 && nums[m] < nums[m - 1]) //peak to the left
                r = m - 1;
            else if (m != nums.length -1 && nums[m] < nums[m + 1]) //peak to the right
                l = m + 1;
        }
        return -1; //actually peak is guaranteed
    }

    //240
    /*
    [
  [1, 3, 5, 7],
  [2, 4, 7, 8],
  [3, 5, 9, 10]
] note duplicate is possible when need count, if (target == matrix[i][j]) --j, ++x at the same time
(http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/)
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        //start from upper right, move down left. each time we can safely ignore a row or a column
        for (int i =0, j = matrix[0].length - 1; i < matrix.length && j >= 0;){
            if (matrix[i][j] > target)
                --j;
            else if (matrix[i][j] < target)
                ++i;
            else
                return true;
        }
        return false;
    }

    //287
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        //binary search find mid point between 1 and n and then count how many <= n, then pick side. o(nlogn)
        int n = nums.length -1, l = 1, r = n, m, cnt = 0;

        while (l < r){ //not equal. when euqal we know we found the one
            m = l + ((r - l) >> 1);
            cnt = 0;
            for (int i : nums){
                if (i <= m)
                    ++cnt;
            }
            if (cnt > m)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }




















    public static void main(String[] args){
        BinarySearch bs = new BinarySearch();
        int[] t = {3,1};
        //bs.search(t, 1);
    }
}
