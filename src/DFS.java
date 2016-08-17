import java.util.*;

/**
 * Created by zplchn on 7/4/16.
 */
public class DFS {

    //60
    public String getPermutation(int n, int k) {


    }

    //79
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null)
            return false;
        else if (board.length == 0 || board[0].length == 0)
            return word.length() == 0;
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length; ++j){
                if (existHelper(board, i, j, 0, word))
                    return true;
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, int r, int c, int k, String word){
        if (k == word.length())
            return true;
        boolean res = false;
        if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == word.charAt(k)){
            board[r][c] ^= 256;
            res =  existHelper(board, r - 1, c, k + 1, word)
                    || existHelper(board, r + 1, c, k + 1, word)
                    || existHelper(board, r, c - 1, k + 1, word)
                    || existHelper(board, r, c + 1, k + 1, word);
            board[r][c] ^= 256;
        }
        return res;
    }

    //93
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0){
            return res;
        }

        dfs(s, 1, 0, "", res);
        return res;
    }

    private void dfs(String s, int k, int i, String pre, List<String> res){
        if (k == 4){
            String rest = s.substring(i);
            if (isValid(rest)){
                res.add(pre + rest);
            }
            return;
        }

        for (int x = i+1; x < s.length() && x <= i + 3; ++x){ // make sure not go beyond s

            String t = s.substring(i, x);
            if (isValid(t)){
                dfs(s, k + 1, x, pre + t + ".", res);
            }
        }
    }

    private boolean isValid(String s){
        if (s.length() > 3 || s.length() == 0 ||
                (s.length() > 1 && s.charAt(0) == '0') || Integer.parseInt(s) > 255){
            return false;
        }
        return true;
    }

    //94
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()){
            if (root != null){
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    //95
    public List<TreeNode> generateTrees(int n) {
        //this combines binary serach + postorder. split at any node and then define left set and right set
        //the only difference is not a "binary search but loop through all available candidates
        //then postorder to connect back. For sorted arrays, this divide and conquer/binary search should be remembered!
        if (n < 1)
            return new ArrayList<TreeNode>();
        return generateTreesHelper(1, n);
    }

    private List<TreeNode> generateTreesHelper(int left, int right){
        List<TreeNode> res = new ArrayList<>();
        if (left > right){
            res.add(null);
            return res;
        }
        //pick everyone as root and divide and conquer. then connect as postorder
        for (int i = left; i <= right; ++i){
            List<TreeNode> lchild = generateTreesHelper(left, i-1);
            List<TreeNode> rchild = generateTreesHelper(i+1, right);

            for (TreeNode tnleft : lchild){
                for (TreeNode tnright : rchild){
                    TreeNode root = new TreeNode(i);
                    root.left = tnleft;
                    root.right = tnright;
                    res.add(root);
                }
            }
        }
        return res;
    }

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

    //105
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            return null;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i)
            hm.put(inorder[i], i);
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, hm);
    }

    private TreeNode buildTreeHelper(int[] preorder, int pl, int pr, int[] inorder, int il, int ir, Map<Integer,Integer> hm){
        if (il > ir)
            return null;
        TreeNode tn = new TreeNode(preorder[pl]);
        int k = hm.get(tn.val);
        tn.left = buildTreeHelper(preorder, pl + 1, pl + k - il, inorder, il, k - 1, hm);
        tn.right = buildTreeHelper(preorder, pl + k - il + 1, pr, inorder, k + 1, ir, hm);
        return tn;
    }

    //106
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i)
            hm.put(inorder[i], i);
        return buildTreeHelper2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, hm);
    }

    private TreeNode buildTreeHelper2(int[] inorder, int il, int ir, int[] postorder, int pl, int pr, Map<Integer, Integer> hm){
        if (il > ir)
            return null;
        TreeNode root = new TreeNode(postorder[pr]);
        int k = hm.get(root.val);
        root.left = buildTreeHelper2(inorder, il, k - 1, postorder, pl, pl + k - il - 1, hm);
        root.right = buildTreeHelper2(inorder, k + 1, ir, postorder, pl + k - il, pr - 1, hm);
        return root;
    }

    //108
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return sortedArryToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArryToBSTHelper(int [] nums, int l, int r){
        if (l > r)
            return null;
        int m = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[m]);
        root.left = sortedArryToBSTHelper(nums, l, m - 1);
        root.right = sortedArryToBSTHelper(nums, m + 1, r);
        return root;
    }

    //109
    private ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head;
        int len = 0;
        while (cur != null){
            cur = cur.next;
            ++len;
        }
        this.head = head;
        return sortedListToBSTHelper(0, len - 1);
    }

    private TreeNode sortedListToBSTHelper(int l, int r){ //cannot pass head here since reassgin value wont change head overall only content will
        if (l > r)
            return null;
        int m = l + ((r - l) >> 1);
        TreeNode lsub = sortedListToBSTHelper(l, m - 1);
        TreeNode root = new TreeNode(this.head.val);
        //head = head.next; java is pass by value only and here reassign value wont change head('s content)
        this.head = head.next;
        root.left = lsub;
        root.right = sortedListToBSTHelper(m + 1, r);
        return root;
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

    //114
    private TreeNode last = null;
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        TreeNode t = root.right;
        if (last != null){
            last.left = null;
            last.right = root;
        }
        last = root;
        flatten(root.left);
        flatten(t);
    }

    //116
    public void connectCompleteBT(TreeLinkNode root) {
        if (root == null)
            return;
        if (root.left != null)
            root.left.next = root.right;
        if (root.right != null)
            root.right.next = root.next != null ? root.next.left : null;
        connect(root.left);
        connect(root.right);
    }

    //117
    public void connect(TreeLinkNode root) {
        /* preorder but right first then left. at each level find the next non-null/ null child to connect.
            when no missing child, it's same as the complete tree's solution */
        if (root == null)
            return;
        TreeLinkNode t = root.next;
        while (t != null){
            if (t.left != null){
                t = t.left;
                break;
            }
            else if (t.right != null){
                t = t.right;
                break;
            }
            else
                t = t.next;
        }

        //connect child
        if (root.right != null)
            root.right.next = t;
        if (root.left != null)
            root.left.next = root.right != null?root.right : t;
        //preorder
        connect(root.right);
        connect(root.left);
    }

    //124
    private int maxPath;
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        maxPath = root.val;
        maxPathSumHelper(root);
        return maxPath;
    }

    private int maxPathSumHelper(TreeNode root){
        if (root == null)
            return 0;
        int ln = Math.max(maxPathSumHelper(root.left), 0);
        int rn = Math.max(maxPathSumHelper(root.right), 0);
        maxPath = Math.max(maxPath, ln + rn + root.val);
        return Math.max(ln, rn) + root.val;
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

    //131
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        boolean[][] parti = getPartitions(s);
        partitionHelper(s, parti, 0, new ArrayList<String>(),res);
        return res;
    }

    private boolean[][] getPartitions(String s){
        boolean [][] dp =new boolean[s.length()][s.length()];
        for (int i = s.length()-1; i>=0; --i){
            for (int j = i; j < s.length(); ++j){
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1]))
                    dp[i][j] = true;
            }
        }
        return dp;
    }

    private void partitionHelper(String s, boolean[][] parti, int k, List<String> combi, List<List<String>> res){
        if (k == s.length()){
            res.add(new ArrayList<String>(combi));
            return;
        }
        for (int i = k; i < s.length(); ++i){
            if (parti[k][i]){
                combi.add(s.substring(k,i+1));
                partitionHelper(s, parti, i+1, combi, res);
                combi.remove(combi.size()-1);
            }
        }
    }

    //144
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()){
            if (root != null){
                res.add(root.val);
                if (root.right != null)
                    stack.push(root.right);
                root = root.left;
            }
            else {
                root = stack.pop();
            }
        }
        return res;
    }

    //145
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()){
            if (root != null){
                stack.push(root);
                root = root.left;
            }
            else {
                //we dont know if the mid point need to the right child, or is returned from the right child. so we
                //set the pre as the last one traversed and compare with the peek, when equal means coming back and pop
                TreeNode tn = stack.peek();
                if (tn.right != null && pre != tn.right)
                    root = tn.right;
                else {
                    tn = stack.pop();
                    res.add(tn.val);
                    pre = tn;
                }
            }
        }
        return res;
    }

    //156
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        return upsideDownBinaryTreeHelper(root, null);
    }

    private TreeNode upsideDownBinaryTreeHelper(TreeNode root, TreeNode parent){
        if (root == null)
            return root;
        TreeNode new_root = upsideDownBinaryTreeHelper(root.left, root);
        root.left = parent != null ? parent.right : null;
        root.right = parent;

        return new_root == null ? root : new_root;
    }

    //222
    public int countNodes(TreeNode root) {
        // complete tree - the last lvl are left filled. full complete tree(no missing nodes) have total 2^h - 1 nodes o(h^2)
        if (root == null)
            return 0;
        int lheight = getLeft(root.left);
        int rheight = getRight(root.right);

        if (lheight == rheight)
            return (2<<lheight) -1;
        else
            return countNodes(root.left) + countNodes(root.right) +1;
    }


    private int getLeft(TreeNode t) {
        int cnt = 0;
        while (t != null){
            t = t.left;
            ++cnt;
        }
        return cnt;
    }

    private int getRight(TreeNode t) {
        int cnt = 0;
        while (t != null){
            t = t.right;
            ++cnt;
        }
        return cnt;
    }

    //226
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //230
    //bst's inorder traversal is sorted, the kth is the kth in inorder traversal
    private TreeNode kth;
    private int index = 1;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return -1;
        kthSmallestHelper(root, k);
        return this.kth.val;
    }

    private void kthSmallestHelper(TreeNode root, int k){
        if (root == null)
            return;
        if (kth == null)
            kthSmallestHelper(root.left, k);
        if (index++ == k){
            kth = root;
        }
        if (kth == null)
            kthSmallestHelper(root.right, k);
    }

    //235
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        return lowestCommonAncestorBSTHelper(root, p, q);
    }

    private TreeNode lowestCommonAncestorBSTHelper(TreeNode root, TreeNode p, TreeNode q){
        while (root != null){
            if (p.val < root.val && q.val < root.val)
                root = root.left;
            else if (p.val > root.val && q.val > root.val)
                root = root.right;
            else
                break;
        }
        return root;
    }

    //236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        return lowestCommonAncestorHelper(root, p, q);
    }

    private TreeNode lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q){
        if (root == null)
            return null;
        if (root == p || root == q) //need to compare the node, not value, because there could be dup in the tree
            return root;
        TreeNode left = lowestCommonAncestorHelper(root.left, p, q);
        TreeNode right = lowestCommonAncestorHelper(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }

    //250
    private int uni;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        countUnivalSubtreesHelper(root);
        return this.uni;
    }

    private boolean countUnivalSubtreesHelper(TreeNode root){
        if (root == null)
            return true;
        boolean uniLeft = countUnivalSubtreesHelper(root.left);
        boolean uniRight = countUnivalSubtreesHelper(root.right);

        if (uniLeft && uniRight && (root.left == null || root.left.val == root.val) && (root.right == null || root.right.val == root.val)){
            this.uni++;
            return true;
        }
        return false;
    }

    //255
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return true;
        return verifyPreorderHelper(preorder, 0, preorder.length - 1);
    }

    private boolean verifyPreorderHelper(int[] preorder, int l, int r){
        //rule is find left subtree, then right subtree should all > root. then recurse to each subtree when l > r is a leaf
        if (l > r)
            return true;
        int root = preorder[l], i = l + 1;
        while (i <= r && preorder[i] < root)
            ++i;
        int leftEnd = i - 1;
        while (i <= r){
            if (preorder[i++] < root)
                return false;
        }
        return verifyPreorderHelper(preorder, l + 1, leftEnd) && verifyPreorderHelper(preorder, leftEnd + 1, r);
    }

    //257
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        binaryTreePathsHelper(root, res, "");
        return res;
    }

    private void binaryTreePathsHelper(TreeNode root, List<String> res, String pre){
        if (root == null)
            return;
        if (root.left == null && root.right == null){
            res.add(pre + root.val);
            return;
        }
        binaryTreePathsHelper(root.left, res, pre + root.val + "->");
        binaryTreePathsHelper(root.right, res, pre + root.val + "->");
    }

    //270
    private double max;
    private int closet;
    public int closestValue(TreeNode root, double target) {
        if (root == null)
            return 0;
        this.max = Math.abs(root.val - target);
        this.closet = root.val; //dont forget this
        closestValueHelper(root, target);
        return this.closet;
    }

    private void closestValueHelper(TreeNode root, double target){
        if (root == null)
            return;
        if (Math.abs(target - root.val) < max) {
            max = Math.abs(target - root.val);
            this.closet = root.val;
        }
        if (target < root.val)
            closestValueHelper(root.left, target);
        else
            closestValueHelper(root.right, target);
    }

    //285
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        /* two cases : 1. if p has right child, then would be the leftmost child of the right child/or itself
        2. otherwise, it's the last time when turn to left when searching p
         */
        if (root == null || p == null)
            return null;

        //1. find p and cache lastleft
        TreeNode lastLeft = null;
        while (root != null && root != p){
            if (p.val > root.val)
                root = root.right;
            else {
                lastLeft = root;
                root = root.left;
            }
        }
        if (root == null)
            return null; //not found p
        if (root.right == null) //no right child, need to return lastleft
            return lastLeft;
        root = root.right;//return leftmost of right child
        while (root.left != null)
            root = root.left;
        return root;
    }

    //294
    public boolean canWin(String s) {
        if (s == null || s.length() == 0)
            return false;
        return canWinHelper(s.toCharArray()); //need to do string munipulation, change to char[] is simpler
    }
    //try all possible ways and take turns, when inside loop it's the oppo takes as the player and needs to lose
    //as long as there is a way to make the first player win. then we return true because we can follow this
    private boolean canWinHelper(char[] s){
        for (int i = 0; i < s.length - 1; ++i){
            if (s[i] == '+' && s[i+1] == '+'){
                s[i] = s[i+1] = '-';
                boolean oppo = canWinHelper(s);
                s[i] = s[i+1] = '+';
                if (!oppo)
                    return true;
            }
        }
        return false;
    }

    //298
    private int lc = 1;
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        longestConsecutiveHelper(root, null, 1);
        return this.lc;
    }

    private void longestConsecutiveHelper(TreeNode root, TreeNode parent, int k){
        if (root == null)
            return;
        if (parent != null && parent.val + 1 == root.val)
            this.lc = Math.max(this.lc, ++k);
        else
            k = 1;
        longestConsecutiveHelper(root.left, root, k);
        longestConsecutiveHelper(root.right, root, k);
    }

    public static void main(String[] args){
        String s = "010010";
        //System.out.println(s.substring(1, 6));
        DFS dfs = new DFS();
        dfs.restoreIpAddresses(s).forEach(System.out::println);
    }






























}
