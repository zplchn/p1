import sun.rmi.server.InactiveGroupException;

import java.util.*;


/**
 * Created by zplchn on 6/9/16.
 */
public class Jun16 {

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

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {


    }

    public String longestPalindrome(String s) {


    }

    public String convert(String s, int numRows) {

    }

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

    public boolean isMatch(String s, String p) {

    }

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

    public String intToRoman(int num) {

    }

    public int romanToInt(String s) {

    }

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

    public List<String> letterCombinations(String digits) {

    }

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

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode t = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(t);
        return root;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null)
            return q == null;
        else if (q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if (p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        else
            return root;
    }

    public int numTrees(int n) {


    }

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

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);

    }

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





}
