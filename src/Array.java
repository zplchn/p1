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





























}
