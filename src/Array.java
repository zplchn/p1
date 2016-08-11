import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zplchn on 7/4/16.
 */


public class Array {

    //73
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;

        //without extra space, use first row/col as markers
        boolean firstR = false, firstC = false;
        for (int i = 0; i < matrix.length; ++i)
            if (matrix[i][0] == 0) {
                firstC = true;
                break;
            }
        for (int j = 0; j < matrix[0].length; ++j)
            if (matrix[0][j] == 0) {
                firstR = true;
                break;
            }

        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j)
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        for (int i = 1; i < matrix.length; ++i)
            for (int j = 1; j < matrix[0].length; ++j)
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
        if (firstC){
            for (int i = 0; i < matrix.length; ++i)
                matrix[i][0] = 0;
        }
        if (firstR){
            for (int i = 0; i < matrix[0].length; ++i)
                matrix[0][i] = 0;
        }
    }

    //88
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null)
            return;
        int t = m + n - 1;
        --m;
        --n;

        while (t >= 0) {
            if (n < 0)
                break;
            else if (m < 0){
                nums1[t--] = nums2[n--];
            }
            else {
                nums1[t--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            }
        }
    }

    //118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0)
            return res;
        res.add(Arrays.asList(1));
        for (int i = 1; i < numRows; ++i){
            List<Integer> combi = new ArrayList<>();
            combi.add(1);
            List<Integer> last = res.get(res.size() - 1);
            for (int j = 1; j < last.size(); ++j){
                combi.add(last.get(j) + last.get(j-1));
            }
            combi.add(1);
            res.add(combi);
        }
        return res;
    }

    //119
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0)
            return res;
        res.add(1);
        for (int i = 1; i <= rowIndex; ++i){
            res.add(1);
            for (int j = res.size()-2; j > 0; --j){
                res.set(j, res.get(j) + res.get(j-1));
            }
        }
        return res;
    }

    //163
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || lower > upper)
            return res;
        if (nums.length == 0){ //nums be empty still a valid case
            res.add(findMissingRangesHelper(lower - 1, upper + 1));
            return res;
        }
        int l = 0, r = 1;
        if (nums[0] > lower)
            res.add(findMissingRangesHelper(lower-1, nums[0]));
        while (r < nums.length){
            if (nums[r] == nums[r-1] + 1)
                ++r;
            else {
                res.add(findMissingRangesHelper(nums[r - 1], nums[r]));
                l = r;
                r += 1;
            }
        }
        if (nums[nums.length - 1] < upper)
            res.add(findMissingRangesHelper(nums[nums.length - 1], upper+1));
        return res;
    }
    //not include i, j
    private String findMissingRangesHelper(int i, int j){
        if (j == i + 2)
            return Integer.toString(i+1);
        else
            return (i+1) + "->" + (j-1);
    }

    //169
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int maj = nums[0], cnt = 1;
        for (int i = 1; i < nums.length; ++i){
            if (nums[i] == maj)
                ++cnt;
            else
                --cnt;
            if (cnt == 0) {
                maj = nums[i];
                cnt = 1;
            }
        }
        return maj;
    }

    //204
    public int countPrimes(int n) {
        boolean [] np = new boolean[n];
        int cnt = 0;
        for (int i = 2; i <= Math.sqrt(n); ++i){ //use sqrt, small*big -->big* small must be already calculated cannot create new cases
            if (!np[i]) {
                for (int j = 2; i * j < n; ++j) {
                    np[i * j] = true;
                }
            }
        }
        for (int k = 2; k < n; ++k)
            cnt += np[k] ? 0 : 1;

        return cnt;
    }

    //228
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        int l = 0, r = 1;
        while (r < nums.length){
            while (r < nums.length && nums[r] == nums[r-1] + 1)
                ++r;
            if (r != l + 1){
                res.add(nums[l] + "->" + nums[r - 1]);
            }
            else {
                res.add(Integer.toString(nums[l])); //cannot directly add an int.
            }
            l = r++;
        }
        if (l < nums.length) {
            res.add(Integer.toString(nums[l]));
        }
        return res;

    }

    //229
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        int n1, n2, c1, c2;
        n1 = n2 = c1 = c2 = 0;


        for (int i : nums){
            if (i == c1)
                ++n1;
            else if (i == c2)
                ++n2;
            else if (n1 == 0){
                c1 = i;
                n1 = 1;
            }
            else if (n2 == 0){
                c2 = i;
                n2 = 1;
            }
            else {
                --n1;
                --n2;
            }
        }
        n1 = n2 = 0;
        //must have this verification process because there could be no majority or 1 or 2 majority candidates
        //only majority elements can cause c1/c1 to change
        for (int i : nums){
            if (i == c1)
                ++n1;
            else if (i == c2)
                ++n2;
        }
        if (n1 > nums.length / 3)
            res.add(c1);
        if (n2 > nums.length / 3)
            res.add(c2);
        return res;
    }

    //238
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; ++i){
            res[i] = res[i-1] * nums[i-1];
        }
        int right = 1;
        for (int i = nums.length -2; i >= 0; --i){
            right *= nums[i+1];
            res[i] *= right;
        }
        return res;
    }

    //243
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null)
            return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE, i1 = -1, i2 = -1;
        for (int i = 0; i < words.length; ++i){
            if (words[i].equals(word1)){
                if (i2 >= 0)
                    min = Math.min(min, i - i2);
                i1 = i;
            }
            else if (words[i].equals(word2)){
                if (i1 >= 0)
                    min = Math.min(min, i - i1);
                i2 = i;
            }
        }
        return min;
    }






























}