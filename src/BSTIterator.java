import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 8/7/16.
 */

//173

public class BSTIterator {
    private Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tn = stack.pop();
        pushLeft(tn.right);
        return tn.val;
    }

    private void pushLeft(TreeNode root){
        while (root != null){
            stack.push(root);
            root = root.left;
        }
    }
}
