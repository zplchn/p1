/**
 * Created by zplchn on 7/4/16.
 */
public class TwoPointer {

    //11
    public int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int i = 0, j = height.length - 1, res = 0;
        while (i < j){
            int left = height[i], right = height[j];
            res = Math.max(res, Math.min(left, right) * (j - i));
            if (left <= right){ //this otherwise timeout, if left is small, then as long as small, wont get large
                while (i < j && height[i] <= left)
                    ++i;
            }
            else {
                while (i < j && height[j] <= right)
                    --j;
            }
        }
        return res;
    }



    //26
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = 0, r = 1;

        while (r < nums.length){
            if (nums[l] != nums[r])
                nums[++l] = nums[r];
            ++r;
        }
        return l + 1;
    }

    //27
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = 0, r = 0;
        while (r < nums.length){
            if (nums[r] != val)
                nums[l++] = nums[r];
            ++r;
        }
        return l + 1;
    }




























}
