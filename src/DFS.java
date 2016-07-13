import java.util.ArrayList;
import java.util.List;

/**
 * Created by zplchn on 7/4/16.
 */
public class DFS {

    //101
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true; // this is a true
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode l1, TreeNode l2){
        if (l1 == null)
            return l2 == null;
        else if (l2 == null)
            return false;
        else
            return l1.val == l2.val && isSymmetricHelper(l1.left, l2.right) && isSymmetricHelper(l1.right, l2.left);
    }

    //110
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode root){
        if (root == null)
            return 0;
        int l = isBalancedHelper(root.left);
        if (l == -1)
            return -1;
        int r = isBalancedHelper(root.right);
        if (r == -1)
            return -1;
        if (Math.abs(l - r) > 1)
            return -1;
        return Math.max(l, r) + 1;
    }

    //111
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

    }

    //112
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);

    }


    //113
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        pathSumHelper(root, sum, res, new ArrayList<Integer>());
        return res;
    }

    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> combi){
        if (root == null)
            return;
        combi.add(root.val);
        if (root.left == null && root.right == null){
            if (root.val == sum){
                res.add(new ArrayList<>(combi));
            }
            combi.remove(combi.size() - 1);
            return;
        }
        pathSumHelper(root.left, sum-root.val, res, combi);
        pathSumHelper(root.right, sum-root.val, res, combi);
        combi.remove(combi.size() - 1);
    }

    //129

    private int sum;
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        sumNumbersHelper(root, 0);
        return this.sum;
    }

    private void sumNumbersHelper(TreeNode root, int pre){
        if (root == null){
            return;
        }
        pre = pre * 10 + root.val;
        if (root.left == null && root.right == null)
            this.sum += pre;
        sumNumbersHelper(root.left, pre);
        sumNumbersHelper(root.right, pre);
    }































}
