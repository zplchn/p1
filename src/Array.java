import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zplchn on 7/4/16.
 */


public class Array {

    //31
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        int r = nums.length - 1;
        while (r > 0 && nums[r] <= nums[r-1])
            --r;
        if (r == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        //need to find from right the next big one of r - 1
        int t = nums.length - 1;
        while (nums[t] <= nums[r-1])
            --t;

        swap(nums, r - 1, t);
        reverse(nums, r, nums.length - 1);
    }

    private void swap(int[] nums, int l, int r){
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }

    private void reverse(int[] nums, int l, int r){
        while (l < r){
            swap(nums, l++, r--);
        }
    }

    //54
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int m = matrix.length, n = matrix[0].length, lvl = (Math.min(m, n) >> 1);
        for (int l = 0; l < lvl; ++l){
            for (int j = l; j < matrix[0].length -l -1; ++j) //every direction leave the last one as the next direction start
                res.add(matrix[l][j]);
            for (int i = l; i < matrix.length -l -1; ++i)
                res.add(matrix[i][matrix[0].length - l - 1]);
            for (int j = matrix[0].length - l - 1; j > l; --j)
                res.add(matrix[matrix.length -l-1][j]);
            for (int i = matrix.length -l -1; i > l; --i)
                res.add(matrix[i][l]);
        }
        if (Math.min(m, n) % 2 == 1) {//note here is not lvl, when m=n=1
            if (m < n) {
                for (int j = lvl; j < matrix[0].length - lvl; ++j)
                    res.add(matrix[lvl][j]);
            }
            else { //this else cover an important case, when m=n=1 [[1]]. this will only be added here
                for (int i = lvl; i < matrix.length - lvl; ++i)
                    res.add(matrix[i][lvl]);
            }
        }
        return res;
    }

    //59
    public int[][] generateMatrix(int n) {
        if (n < 0)
            return null;
        int[][] res = new int[n][n];
        int c = 1;
        for (int l = 0; l < n/2; ++l){
            for (int j = l; j < n-l-1; ++j)
                res[l][j] = c++;
            for (int i = l; i < n-l-1; ++i)
                res[i][n-l-1] = c++;
            for(int j = n-1-l; j> l; --j)
                res[n-1-l][j] = c++;
            for(int i = n-1-l; i > l; --i)
                res[i][l] = c++;
        }
        //need to take care of heart
        if (n%2==1)
            res[n/2][n/2] = c;
        return res;

    }

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

    //289
    public void gameOfLife(int[][] board) {
        //inplace --> must sweep twice. and encoding for the first pass
        /*
        0 : dead -> dead
        1 : live -> live
        -- change
        2: live -> dead 1&& (<2 || >3)
        3: dead -> live 0&& (3)
        then sweep again %2
         */
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int[] offx = {-1, -1, -1,  0,  0,  1, 1, 1};
        int[] offy = {-1,  0,  1, -1,  1, -1, 0, 1};

        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length; ++j){
                int cnt = 0;

                for (int k = 0; k < offx.length; ++k){
                    int x = i + offx[k];
                    int y = j + offy[k];
                    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length)
                        cnt += (board[x][y] == 1 || board[x][y] == 2) ? 1 : 0; //note here need to count the state before change
                }

                if (board[i][j] ==1 && (cnt < 2 || cnt > 3))
                    board[i][j] = 2;
                else if (board[i][j] == 0 && cnt == 3)
                    board[i][j] = 3;
            }
        }
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[0].length; ++j)
                board[i][j] %= 2;
    }


    public static void main(String[] args){
        Array aa = new Array();
        int[][] t = {{1,1}};
        aa.gameOfLife(t);
    }





























}
