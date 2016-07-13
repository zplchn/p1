import java.util.HashMap;
import java.util.Map;

/**
 * Created by zplchn on 7/4/16.
 */
public class HashTable {

    //1
    public int[] twoSum(int[] nums, int target) {
        int [] res = {-1, -1};
        if (nums == null || nums.length < 2)
            return res;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; ++i){
            if (hm.containsKey(target - nums[i])){
                res[0] = hm.get(target - nums[i]);
                res[1] = i;
                break;
            }
            hm.put(nums[i], i);
        }
        return res;


    }






























}
