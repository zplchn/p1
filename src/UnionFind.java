import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zplchn on 8/16/16.
 */
public class UnionFind {
    //128
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        //union find - insert all into a hashset, and then get one and keep expanding to neighbours(UNION) and get the longest path
        Set<Integer> hs = new HashSet<>();
        for (int i : nums)
            hs.add(i);

        int max = 0;
        while (!hs.isEmpty()){
            Iterator<Integer> iter = hs.iterator(); //since we delete, everytime need to get new
            int x = iter.next();
            hs.remove(x);
            int len = 1, t = x;
            while (hs.contains(--t)){
                hs.remove(t);
                ++len;
            }
            t = x;
            while (hs.contains(++t)){
                hs.remove(t);
                ++len;
            }
            max = Math.max(max, len);
        }
        return max;
    }
}
