import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zplchn on 7/6/16.
 */
public class NP {

    //17
    public static final String[] phone = {"","","abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        letterCombinationsHelper(digits, 0, new StringBuilder(), res);
        return res;
    }

    private void letterCombinationsHelper(String digits, int s, StringBuilder sb, List<String> res){
        if (s == digits.length()){
            res.add(sb.toString());
            return;
        }
        int index = digits.charAt(s) - '0';
        for (int i = 0; i < phone[index].length(); ++i){
            sb.append(phone[index].charAt(i));
            letterCombinationsHelper(digits, s + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    //22
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0)
            return res;
        generateParenthesisHelper(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void generateParenthesisHelper(int n, int l, int r, StringBuilder sb, List<String> res){
        if (l == n && r == n){
            res.add(sb.toString());
            return;
        }
        if (l < n){
            sb.append('(');
            generateParenthesisHelper(n, l + 1, r, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (r < l){
            sb.append(')');
            generateParenthesisHelper(n, l, r + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

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

    //46
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        permuteHelper(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
        return res;
    }

    private void permuteHelper(int[] nums, boolean[] used, List<Integer> combi, List<List<Integer>> res){
        if (combi.size() == nums.length){
            res.add(new ArrayList<>(combi));
            return;
        }
        for (int i = 0; i < nums.length; ++i){
            if (!used[i]){
                used[i] = true;
                combi.add(nums[i]);
                permuteHelper(nums, used, combi, res);
                combi.remove(combi.size() -1);
                used[i] = false;
            }
        }
    }

    //47
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (nums == null)
//            return res;
//        permuteUniqueHelper(res, nums, )
//
//    }

    //51
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 1)
            return res;
        solveNQueensHelper(n, 0, new int[n], res);
        return res;
    }

    private void solveNQueensHelper(int n, int row, int[] columnForRow, List<List<String>> res){
        if (row == n){
            List<String> combi = new ArrayList<String>();
            for (int i = 0; i < n; ++i){
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; ++j){
                    if (columnForRow[i] == j)
                        sb.append('Q');
                    else
                        sb.append('.');
                }
                combi.add(sb.toString());
            }
            res.add(combi);
            return;
        }
        for (int i = 0; i < n; ++i){
            columnForRow[row] = i; //assert
            if (checkQueens(row, columnForRow)) //check
                solveNQueensHelper(n, row + 1, columnForRow, res); //recurse
        }
    }

    private boolean checkQueens(int row, int[] columnForRow){
        for (int i = 0; i < row; ++i){
            if (columnForRow[i] == columnForRow[row] || Math.abs(columnForRow[row] - columnForRow[i]) == row - i)
                return false;
        }
        return true;
    }

    //77
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k)
            return res;
        combineHelper(n, 1, k, new ArrayList<Integer>(), res);
        return res;
    }

    private void combineHelper(int n, int i, int k, List<Integer> combi, List<List<Integer>> res){
        if (k == 0){
            res.add(new ArrayList<>(combi)); //here must do a deep-copy otherwise only stores ref and will be backtracked
            return;
        }
        for (int s = i; s <= n; ++s){
            combi.add(s);
            combineHelper(n, s + 1, k - 1, combi, res);
            combi.remove(combi.size() - 1);
        }
    }

    //78
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        //Arrays.sort(nums);
        res.add(new ArrayList<Integer>());
        for (int i : nums){
            int size = res.size();
            for (int j = 0; j < size; ++j){
                List<Integer> combi = new ArrayList<>(res.get(j));
                combi.add(i);
                res.add(combi);
            }
        }
        return res;
    }

    //90
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums);
        res.add(new ArrayList<Integer>());
        int pre = 0;
        for (int i = 0; i < nums.length; ++i){
            int size = res.size(), j = 0;
            if (i > 0 && nums[i] == nums[i-1])
                j = pre;
            for (; j < size; ++j){
                List<Integer> combi = new ArrayList<>(res.get(j));
                combi.add(nums[i]);
                res.add(combi);
            }
            pre = size;
        }
        return res;
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
