/**
 * Created by zplchn on 7/4/16.
 */
public class Bit {

    //136
    public int singleNumberTwo(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = 0;
        for (int i : nums)
            res ^= i;
        return res;

    }

    //137
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < 32; ++i){
            int cnt = 0;
            for (int k : nums){
                cnt += ((k >> i) & 1);
            }
            res |= ((cnt % 3) << i);
        }
        return res;
    }

    //190
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i){
            res = (res << 1) + (n & 1);
            n >>>= 1;
        }
        return res;
    }

    //191
    public int hammingWeight(int n) {
        int c = 0;
        while (n != 0){
            c += (n & 1);
            n >>>= 1;
        }
        return c;
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n-1)) == 0; // precedence : equality > bit > logical
    }

    //260
    public int[] singleNumberTwoSingleAllotherInPairs(int[] nums) {
        int[] res = {0, 0};
        if (nums == null || nums.length < 2)
            return new int[]{-1,-1};//initializer can only be standalone in delcaration, otherwise must be have new int[]{val, val2}
        //1. first xor all and get a^b
        int xor_two = 0;
        for (int i : nums)
            xor_two ^= i;

        //2. now we find which bit of xor_two is 1 where they differ
        int k = 0;
        while (k < 32){
            if (((xor_two >> k) & 1) == 1) // note & is lower than ==, must extra ()
                break;
            ++k;
        }
        //3. we know at kth a and b must be one 1 one 0, now we loop through the entire array and find ones at kth=1, xor it

        for (int i : nums){
            if (((i >> k) & 1) == 1)
                res[0] ^= i;
            else
                res[1] ^= i;
        }
        return res;
    }

    //268
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < nums.length; ++i)
            res ^= nums[i] ^ (i+1);
        return res;

    }


































}
