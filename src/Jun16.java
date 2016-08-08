import sun.rmi.server.InactiveGroupException;

import java.util.*;


/**
 * Created by zplchn on 6/9/16.
 */
public class Jun16 {
    //1
    public int[] twoSum(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length < 2) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    //2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int sum, carry = 0;

        while (l1 != null || l2 != null || carry!= 0) {
            sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            cur.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur = cur.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }
        return dummy.next;

    }

    //3
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        //longest --> two pointer, nonrepeated -->HashMap
        int i, j, max;
        i = j = max = 0;
        Map<Character, Integer> hm = new HashMap<>();
        while (j < s.length()) {
            char c = s.charAt(j);
            if (hm.containsKey(c) && hm.get(c) >= i) {
                max = Math.max(j - i, max);
                i = hm.get(c) + 1;
            }
            hm.put(c, j++);
        }
        max = Math.max(j - i, max);
        return max;

    }


    //4
    public int reverse(int x) {

        long res = 0;

        while (x != 0) {
            res = res * 10 + (x % 10);
            x /= 10;
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)res;
    }

    /***
     * 5
     * space, overflow return MAX/MIN instantly, non-digit stop,
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        long res = 0;
        boolean isNeg = false;
        int start = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            isNeg = str.charAt(0) == '-';
            start = 1;
        }
        for (int i = start; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (!Character.isDigit(c))
                break;
            res = res * 10 +  c - '0';
            if (!isNeg && res > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (isNeg && -res < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }

        return (int)(isNeg ? -res : res);
    }

    //6
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0){
            if (x / div != x % 10) {
                return false;
            }
            x = x % div / 10;
            div /= 100;
        }
        return true;
    }



    //7
    public int maxArea(int[] height) {
        if (height.length < 2)
            return 0;
        int i = 0, j = height.length - 1, max = 0;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j])
                i++;
            else
                j--;
        }
        return max;
    }

    //8
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String res = strs[0];
        int min = strs[0].length(), j = 0;

        for (int i = 1; i < strs.length; ++i) {
            for (j = 0; j < Math.min(min, strs[i].length()); ++j){
                if (strs[i].charAt(j) != res.charAt(j))
                    break;
            }
            min = Math.min(min, j);
        }
        return res.substring(0, min);

    }

    //9
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3)
            return res;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i+1, r = nums.length - 1, sum;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum > 0)
                    --r;
                else if (sum < 0)
                    ++l;
                else {
                    List<Integer> combi = Arrays.asList(nums[i],nums[l],nums[r]);
                    res.add(combi);
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l-1])
                        ++l;
                    while (l < r && nums[r] == nums[r+1])
                        --r;
                }
            }
        }
        return res;

    }

    //10
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3){
            return Integer.MIN_VALUE;
        }
        int min = Integer.MAX_VALUE, res=Integer.MIN_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1, r = nums.length - 1, sum;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    res = sum;
                }
                if (sum < target)
                    ++l;
                else if (sum > target)
                    --r;
                else
                    break;
            }

        }
        return res;

    }



    //11
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4)
            return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            for (int j = i+1; j < nums.length - 2; ++j) {
                if (j > i+1 && nums[j] == nums[j-1])
                    continue;
                int l = j + 1, r = nums.length -1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum > target)
                        --r;
                    else if (sum < target)
                        ++l;
                    else {
                        List<Integer> combi = Arrays.asList(nums[i], nums[j], nums[l], nums[r]);
                        res.add(combi);
                        ++l;
                        --r;
                        while (l < r && nums[l] == nums[l-1])
                            ++l;
                        while (l < r && nums[r] == nums[r+1])
                            --r;
                    }
                }
            }
        }
        return res;
    }

    //12
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            else {
                TreeNode tn = stack.removeFirst();
                res.add(tn.val);
                root = tn.right;
            }
        }
        return res;
    }

    //13
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    //14
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode t = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(t);
        return root;
    }

    //15
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null)
            return q == null;
        else if (q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    //16
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root){
        if (root == null)
            return 0;
        int l = getHeight(root.left);
        if (l < 0)
            return -1;
        int r = getHeight(root.right);
        if (r < 0)
            return -1;
        return Math.abs(l - r) <= 1 ? Math.max(l, r) + 1 : -1;
    }

    //17
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestorBST(root.left, p, q);
        }
        else if (p.val > root.val && q.val > root.val){
            return lowestCommonAncestorBST(root.right, p, q);
        }
        else
            return root;
    }

    //18
    public TreeNode lowestCommonAncestorBT(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        if (root == p || root == q)
            return root;
        TreeNode l = lowestCommonAncestorBT(root.left, p, q);
        TreeNode r = lowestCommonAncestorBT(root.right, p, q);
        if (l != null && r != null)
            return root;
        return l!= null ? l : r;
    }



    //19
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSym(root.left, root.right);

    }

    private boolean isSym(TreeNode l, TreeNode r){
        if (l == null)
            return r == null;
        else if (r == null)
            return false;
        return l.val == r.val && isSym(l.left, r.right) && isSym(l.right, r.left);
    }

    //20
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        Deque<Character> stack = new ArrayDeque<>();
        String left = "([{", right = ")}]";

        for (int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if (left.indexOf(c) >= 0){
                stack.addFirst(c);
            }
            else if (right.indexOf(c) >= 0){
                if (stack.isEmpty())
                    return false;
                char r = stack.removeFirst();

                if ((c == ')' && r != '(')
                    || (c == '}' && r != '{')
                    || (c == ']' && r != '['))
                    return false;
            }
        }
        return stack.isEmpty();
    }


    //21
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return this.maxSum;
    }

    private int maxSum = Integer.MIN_VALUE;

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int l = Math.max(0, dfs(root.left));
        int r = Math.max(0, dfs(root.right));
        this.maxSum = Math.max(maxSum, root.val + l + r);
        return root.val + Math.max(l, r);
    }

    //22
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        if (root.left != null)
            root.left.next = root.right;
        if (root.right != null)
            root.right.next = root.next != null ? root.next.left : null;
        connect(root.left);
        connect(root.right);

    }

    //23
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode dummy = new ListNode(0), cur = dummy;
        Queue<ListNode> queue = new PriorityQueue<>((ListNode l1, ListNode l2) -> l1.val - l2.val);
        for (ListNode ln : lists){
            if (ln != null)
                queue.offer(ln);
        }
        while (!queue.isEmpty()){
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null)
                queue.offer(cur.next);
        }
        cur.next = null;
        return dummy.next;
    }

    //24
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        int cur = 1, next = 0;
        List<Integer> lvl = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode tn = queue.removeFirst();
            lvl.add(tn.val);
            if (tn.left != null){
                queue.addLast(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.addLast(tn.right);
                ++next;
            }
            if (--cur == 0) {
                res.add(lvl);
                lvl = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        Collections.reverse(res); //reverse in -place
        return res;
    }

    //25
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        int cur = 1, next = 0;
        boolean rev = false;
        List<Integer> lvl = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode tn = queue.removeFirst();
            lvl.add(tn.val);
            if (tn.left != null) {
                queue.addLast(tn.left);
                ++next;
            }
            if (tn.right != null) {
                queue.addLast(tn.right);
                ++next;
            }
            if (--cur == 0) {
                if (rev) {
                    Collections.reverse(lvl);
                }
                res.add(lvl);
                lvl = new ArrayList<>();
                rev = !rev;
                cur = next;
                next = 0;

            }
        }
        return res;
    }

    //26
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long min, long max){
        if (root == null)
            return true;
        if (root.val <= min || root.val >= max)
            return false;
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val,max);
    }

    //27
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

    }


    //28
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()){
            root = root == null ? stack.removeFirst() : root;
            res.add(root.val);
            if (root.right != null)
                stack.addFirst(root.right);
            root = root.left;

        }
        return res;
    }

    //29
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;
        int max = 1; //at least 1 point being itself

        for (int i = 0; i < points.length; ++i){
            Map<Double, Integer> hm = new HashMap<>();
            int same = 0;
            for (int j = i+1; j < points.length; ++j){ //no need to compare previous nodes again as they have been calculated
                if (points[i].x == points[j].x && points[i].y == points[j].y)
                    ++same;
                else if (points[i].x == points[j].x)
                    hm.put(Double.MAX_VALUE, hm.containsKey(Double.MAX_VALUE) ? hm.get(Double.MAX_VALUE) + 1 : 2);//initial = 2
                else if (points[i].y == points[j].y) //+0.0 -0.0
                    hm.put(0.0, hm.containsKey(0.0) ? hm.get(0.0) + 1 : 2);
                else {
                    double z = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x); //here must cast to double
                    hm.put(z, hm.containsKey(z) ? hm.get(z) + 1 : 2);
                }
            }
            int localMax = 1; //self
            for (int k : hm.values())
                localMax = Math.max(localMax, k);
            max = Math.max(max, localMax + same);
        }
        return max;
    }

    //30
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hs = new HashSet<>(nums.length);
        //the trick for this problem is to set the initial capacity
        //so there is no repetative reallocate and copy happens to larget input with no duplicate
        for (int i : nums){
            if (hs.contains(i))
                return true;
            hs.add(i);
        }
        return false;
    }

    //31
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int l, int r){
        if (l > r)
            return null;
        int m = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[m]);
        root.left = sortedArrayToBSTHelper(nums, l, m - 1);
        root.right = sortedArrayToBSTHelper(nums, m + 1, r);
        return root;
    }

    //32
    private TreeNode pre;
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        if (pre != null){
            pre.right = root;
            pre.left = null;
        }
        pre = root;
        TreeNode r = root.right;//before we went into left child, we must preserve right child cuz left will try to modify right when muldify pre at the left child's level, and when bounce back to root, the right already lost
        flatten(root.left);
        flatten(r);


    }

    //33
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length)
            return null;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i){
            hm.put(inorder[i], i);
        }
        return buildTreeHelper(hm, preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(Map<Integer, Integer> hm, int[] pre, int pl, int pr, int[] in, int il, int ir){
        if (pl > pr)
            return null;
        TreeNode root = new TreeNode(pre[pl]);
        int m = hm.get(pre[pl]);
        root.left = buildTreeHelper(hm, pre, pl + 1, pl + m - il, in, il, m - 1);
        root.right = buildTreeHelper(hm, pre, pl + m - il +1, pr, in, m + 1, ir);
        return root;
    }

    //34
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePathsHelper(root, "", res);
        return res;
    }

    private void binaryTreePathsHelper(TreeNode root, String pre, List<String> res){
        if (root == null)
            return;
        String path = pre.length() == 0 ? pre + root.val : pre + "->" + root.val;
        if (root.left == null && root.right == null) {
            res.add(path);
            return;
        }
        binaryTreePathsHelper(root.left, path, res);
        binaryTreePathsHelper(root.right, path, res);

    }

    //35
    public int countNodes(TreeNode root) {
        //complete bt total#=2^h - 1

        if (root == null)
            return 0;
        int left = countNodesHelper(root.left, true);
        int right =countNodesHelper(root.right, false);
        if (left == right)
            return (2 << left) -1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int countNodesHelper(TreeNode root, boolean isLeft){
        if (root == null)
            return 0;
        int count =0;
        while (root!= null){
            count++;
            root = isLeft? root.left : root.right;
        }
        return count;
    }

    //36
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        pathSumHelper(root, sum, res, new ArrayList<Integer>());
        return res;
    }

    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path){
        if (root == null)
            return;
        path.add(root.val);
        if (root.left == null && root.right == null && sum == root.val){
            res.add(new ArrayList<Integer>(path));
            return;
        }
        pathSumHelper(root.left, sum - root.val, res, path);
        pathSumHelper(root.right, sum - root.val, res, path);
        path.remove(path.size() -1);
    }

    //37
    private int closetV;
    private double delta = Double.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
        if (root == null)
            return Integer.MIN_VALUE;
        closetValueHelper(root, target);
        return closetV;
    }

    private void closetValueHelper(TreeNode root, double target) {
        if (root == null)
            return;
        double dl= Math.abs(target - root.val);
        if (dl < delta){
            delta = dl;
            this.closetV = root.val;
        }
        if (target > root.val){
            closetValueHelper(root.right, target);
        }
        else
            closestValue(root.left, target);
    }

    //38



    //39


    //40

    //41

    //42

    //43

    //44
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);

    }

    //45
    //46
    //47
    //48
    //49
    //50
    //51
    //52
    //53
    //54
    //55
    //56
    //57
    //58
    //59
    //60

    /*
    public List<String> letterCombinations(String digits) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {


    }

    public String longestPalindrome(String s) {


    }

    public String convert(String s, int numRows) {

    }

    public String intToRoman(int num) {

    }

    public int romanToInt(String s) {

    }

    public boolean isMatch(String s, String p) {

    }
    public int numTrees(int n) {


    }
    /*public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        while (root.left != null){
            root.left.left = root.right;
            TreeNode t = root.left;
            root.left.right = root;

            root = t;
        }

    }*/
    /*private TreeNode successor;
    private boolean isFound;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;
        inorderTraversalHelper(root, p);
        return this.successor;
    }

    private void inorderTraversalHelper(TreeNode root, TreeNode p){
        if (root == null)
            return;
        if (isFound){
            this.successor = root;
            isFound = !isFound;
            return;
        }
        inorderTraversalHelper(root.left, p);
        if (root.val == p.val){
            isFound = true;
        }
        inorderTraversalHelper(root.right, p);
    }*/


    //61
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root ==null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> lvl = new ArrayList<>();
        queue.addLast(root);
        int cur = 1, next = 0;
        while (!queue.isEmpty()) {
            TreeNode tn = queue.removeFirst();
            lvl.add(tn.val);

            if (tn.left != null) {
                queue.addLast(tn.left);
                ++next;
            }
            if (tn.right != null) {
                queue.addLast(tn.right);
                ++next;
            }
            if (--cur == 0){
                res.add(lvl);
                lvl = new ArrayList<>();
                cur = next;
                next = 0;
            }

        }
        return res;
    }

    //62
    //63
    //64
    //65
    //66
    //67
    //68
    //69
    //70
    //71
    //72
    //73
    public void setZeroes(int[][] matrix) {

    }
    //74
    //75
    //76
    //77
    //78
    //79
    //80
    //81
    //82
    //83
    //84
    //85
    //86
    //87
    //88
    //89
    //90
    //91
    //92
    //93
    //94
    //95
    //96
    //97
    //98
    //99
    //100
    //101
    //102
    //103
    //104
    //105
    //106
    //107
    //108
    //109
    //110









    //169
    public int majorityElement(int[] nums) {
        if (nums.length == 0)
            return Integer.MIN_VALUE;
        int major = nums[0], cnt = 1;

        for (int i = 1; i < nums.length; ++i){
            if (nums[i] == major)
                ++cnt;
            else if(--cnt == 0){
                major = nums[i];
                cnt = 1;
            }
        }
        return major;
    }

    //215
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k)
            return Integer.MIN_VALUE;
        Queue<Integer> queue = new PriorityQueue<>(k);
        for (int i : nums){
            if (queue.size() < k || i >= queue.peek())
                queue.offer(i);
        }
        return queue.poll();
    }













    public static void main(String[] args){
        Jun16 j16 = new Jun16();

        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        r.left.right = new TreeNode(5);
        r.right = new TreeNode(3);

//        j16.flatten(r);
//        j16.preBT(r);
        System.out.println(j16.binaryTreePaths(r));
    }

    private void preBT(TreeNode r){
        if (r == null)
            return;
        System.out.println(r.val);
        preBT(r.left);
        preBT(r.right);
    }





}
