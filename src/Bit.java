/**
 * Created by zplchn on 7/4/16.
 */
public class Bit {

    //136
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = 0;
        for (int i : nums)
            res ^= i;
        return res;

    }

































}
