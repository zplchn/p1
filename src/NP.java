import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zplchn on 7/6/16.
 */
public class NP {

    //39
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        combinationSumHelper(candidates, res, new ArrayList<Integer>(), target, 0);
        return res;
    }

    private void combinationSumHelper(int[] candi, List<List<Integer>> res, List<Integer> combi, int target, int start){
        if (target < 0){
            return;
        }
        if (target == 0){
            res.add(new ArrayList<>(combi));
            return;
        }
        //bfs -- like a layer, select one neighbor and mark add, go to next.
        //doing dfs is also workable, but if the solution is the 1st and 100th elem, too many stacks
        for (int i = start; i < candi.length; ++i){
            if (i > 0 && candi[i] == candi[i-1])
                continue;
            combi.add(candi[i]);
            combinationSumHelper(candi, res, combi, target - candi[i], i);
            combi.remove(combi.size() - 1);
        }
    }

    //40
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        combinationSum2Helper(candidates, res, 0, target, new ArrayList<Integer>());
        return res;
    }

    private void combinationSum2Helper(int[] candi, List<List<Integer>> res, int start, int target, List<Integer> combi){
        if (target < 0)
            return;
        if (target == 0){
            res.add(new ArrayList<>(combi));
            return;
        }
        for (int i = start; i < candi.length; ++i){
            if (i > start && candi[i] == candi[i-1])
                continue;
            combi.add(candi[i]);
            combinationSum2Helper(candi, res, i + 1, target - candi[i], combi);
            combi.remove(combi.size() -1);
        }
    }

    //216
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n <= 0)
            return res;
        combinationSum3Helper(res, 1, k, n, new ArrayList<Integer>());
        return res;
    }

    private void combinationSum3Helper(List<List<Integer>> res, int start, int k, int n, List<Integer> combi){
        if (n < 0)
            return;
        if (k == 0){
            if (n == 0)
                res.add(new ArrayList<>(combi));
            return;
        }
        for (int i = start; i <= 9; ++i){
            combi.add(i);
            combinationSum3Helper(res, i + 1, k - 1, n - i, combi);
            combi.remove(combi.size() - 1);
        }
    }


















}
