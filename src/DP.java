/**
 * Created by zplchn on 7/11/16.
 */
public class DP {

   //53
   public int maxSubArray(int[] nums) {
       if (nums == null || nums.length == 0)
           return 0;
       int localMax = nums[0], max = nums[0];

       for (int i = 1; i < nums.length; ++i){
           localMax = Math.max(localMax + nums[i], nums[i]);
           max = Math.max(max, localMax);
       }
       return max;
   }
}
