import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    //15
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3)
            return res;

        Arrays.sort(nums);

        for(int i = 0; i < nums.length -2; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0)
                    ++l;
                else if (sum > 0)
                    --r;
                else {
                    res.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                    while (l < r && nums[l] == nums[l - 1])
                        ++l;
                    while (l < r && nums[r] == nums[r + 1])
                        --r;
                }
            }
        }
        return res;
    }

    //16
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return -1;
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE, res = -1;

        for (int i = 0; i < nums.length; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1, r = nums.length - 1, sum;
            while (l < r){
                sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < minDiff) {
                    res = sum;
                    minDiff = Math.abs(sum - target);
                    //here cannot remove dup because not known how to move pointers yet
                }
                if (sum < target)
                    ++l;
                else if (sum > target)
                    --r;
                else
                    break;
            }
        }
        return res;
    }

    //18
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length -3; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            for (int j = i + 1; j < nums.length -2; ++j){
                if (j > i + 1 && nums[j] == nums[j-1])
                    continue;
                int l = j + 1, r = nums.length - 1;
                while (l < r){
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum < target)
                        ++l;
                    else if (sum > target)
                        --r;
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l++], nums[r--]));
                        while (l < r && nums[l] == nums[l-1])
                            ++l;
                        while (l < r && nums[r] == nums[r+1])
                            --r;
                    }
                }
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

    //167
    public int[] twoSum(int[] numbers, int target) {
        int [] res = {-1, -1};
        if (numbers == null || numbers.length < 2)
            return res;
        int l = 0, r = numbers.length - 1;
        while (l < r){
            int sum = numbers[l] + numbers[r];
            if (sum < target)
                ++l;
            else if (sum > target)
                --r;
            else {
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            }
        }
        return res;
    }

    //75
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int i1 = 0, i2 = nums.length - 1, cur = 0;
        while (cur <= i2){
            if (nums[cur] == 0){
                nums[cur++] = nums[i1];
                nums[i1++] = 0;
            }
            else if (nums[cur] == 1)
                ++cur;
            else{
                nums[cur] = nums[i2];
                nums[i2--] = 2;
            }
        }
    }

    //80
    public int removeDuplicatesTwo(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = 0, r = 1, cnt = 1;
        while (r < nums.length){
            if (nums[r] != nums[l]) {
                cnt = 1;
                nums[++l] = nums[r++];
            }
            else if (cnt == 1){
                ++cnt;
                nums[++l] = nums[r++];
            }
            else
                ++r;
        }
        return l + 1;

    }

    //259
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        if (nums == null || nums.length < 3)
            return res;
        Arrays.sort(nums); //it doesnt matter the sequence cuz there will always an order i <j < k, and we only care aobut the count
        for (int i = 0; i < nums.length -2; ++i){
            int l = i + 1, r = nums.length - 1, sum;
            while (l < r){
                sum = nums[i] + nums[l] + nums[r];
                if(sum >= target)
                    --r;
                else {
                    res += r - l; // like -2,0,1,2,3 to get 4. [-2,0,3] satisfied, then any combi [0,1], [0,2], [0,3] also satisfy,
                                 // count the ones in middle and then we cut left egge
                    ++l;
                }
            }
        }
        return res;
    }

    //277
    boolean knows(int a, int b){return true;} //dummy knows. can use 2d array to simulate
    public int findCelebrity(int n) {
        if (n < 1)
            return -1;
        //two pointers, if l,r -> true, l must be not, so l move right. else, r must be not, r move left
        int l = 0, r = n - 1;
        while (l < r){
            if (knows(l, r))
                ++l;
            else
                --r;
        }
        //here we have a potential candidate. this candi maynot be a cele cuz there maybe not one.
        l = 0;
        while (l < n){
            if (l != r && (!knows(l, r)||knows(r, l))) //before r havent verify r knows left, after r have verify right knows r
                 return -1;
            ++l;
        }
        return r;

    }

    //283
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int l = 0, r = 0;

        while (r < nums.length){
            if (nums[r] != 0)
                nums[l++] = nums[r++];
            else
                ++r;
        }
        while (l < nums.length)
            nums[l++] = 0;

    }




























}
