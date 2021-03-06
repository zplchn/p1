import java.util.*;

/**
 * Created by zplchn on 7/11/16.
 */
public class DP {
    //5
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0, start = 0, end = 0;

        for (int i = s.length() - 1; i >= 0; --i){
            for (int j = i; j < s.length(); ++j){
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    if (j - i + 1 > max){
                        max = j - i + 1;
                        start = i; //here should take note rather then substring(full copy of substring even ultra long)
                        end = j;
                    }
                }
                //must to the end. cbbc. cb, cbb all false. cbbc true
            }
        }
        return s.substring(start, end + 1);
    }

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

    //55
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        int i = 1, max = nums[0];
        while (i < nums.length && max >= i){
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1)
                break;
            ++i;
        }
        return max >= nums.length-1;
    }

    //62
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                dp[i][j] += (i > 0? dp[i-1][j] : 0) + (j > 0 ? dp[i][j-1]: 0);
            }
        }
        return dp[m-1][n-1];
    }

    //63
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1)
            return 0;
        obstacleGrid[0][0] = 1;
        for (int i = 0; i < obstacleGrid.length; ++i){
            for (int j = 0; j < obstacleGrid[0].length; ++j){
                if (!(i== 0 && j == 0) && obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else {
                    obstacleGrid[i][j] += (i > 0 ? obstacleGrid[i-1][j] : 0) + (j > 0 ? obstacleGrid[i][j-1] : 0);
                }
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    //64
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        for (int j = 1; j < grid[0].length; ++j)
            grid[0][j] += grid[0][j-1];
        for (int i = 1; i < grid.length; ++i)
            grid[i][0] += grid[i-1][0];
        for (int i = 1; i < grid.length; ++i)
            for (int j = 1; j < grid[0].length; ++j){
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    //70
    public int climbStairs(int n) {
        if (n < 0)
            return 0;
        int[] dp = new int[2];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; ++i){
            int t = dp[1];
            dp[1] += dp[0];
            dp[0] = t;
        }
        return dp[1];
    }

    //72
    public int minDistance(String word1, String word2) {
        //a - ab or ab - a or ac - ad
        if (word1 == null || word1.length() == 0)
            return word2 == null ? 0 : word2.length();
        if (word2 == null || word2.length() == 0)
            return word1.length();
        int[][] dp = new int[word1.length() +1][word2.length()+1];
        for (int j = 1; j < dp[0].length; ++j)
            dp[0][j] = j;
        for (int i = 1; i < dp.length; ++i)
            dp[i][0] = i;
        for (int i = 1; i < dp.length; ++i){
            for (int j = 1; j < dp[0].length; ++j){
                if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    //85
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        //divide this to subproblem largest rectangle in histogram, with each row, and height as histogram
        //need to cache the height
        int[] height = new int[matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; ++i){
            for (int j = 0; j < matrix[0].length; ++j){
                height[j] = matrix[i][j] == '0'? 0 : height[j] + 1;
            }
            max = Math.max(max, maximalRectangleHelper(height)); //now convert to calculate largest rectangel histogram
        }
        return max;
    }

    private int maximalRectangleHelper(int[] height){
        //use stack to store increasing height, and pop and use i as right bank, peek() as left bank to calculate size
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i <= height.length; ++i){
            int cur = i < height.length? height[i] : -1;
            while (!stack.isEmpty() && height[stack.peek()] > cur){
                max = Math.max(max, height[stack.pop()] * (stack.isEmpty()? i: (i - stack.peek() -1)));
            }
            stack.push(i);
        }
        return max;
    }

    public static void main (String[] args){
        char[][] t = {{'1', '0', '1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}};
        DP dp = new DP();
        dp.maximalRectangle(t);
    }



    //96
    public int numTrees(int n) {
        if (n <= 0)
            return 0;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <=n; ++i){
            for (int j = 0; j < i; ++j){ //j is number of nodes on left child
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[dp.length - 1];
    }

    //97
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null || s1.length()+s2.length() != s3.length())
            return false;
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); ++i)
            dp[i][0] = s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0];
        for (int j = 1; j <= s2.length(); ++j)
            dp[0][j] = s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1];
        for (int i = 1; i < s1.length(); ++i){
            for (int j = 1; j < s2.length();++j){
                dp[i][j] = (s1.charAt(i-1) == s3.charAt(i + j-1) ? dp[i-1][j] : false) ||
                        (s2.charAt(j-1) == s3.charAt(i + j-1) ? dp[i][j - 1] : false);
            }
        }
        return dp[dp.length - 1][dp[0].length-1];
    }


    //115
    public int numDistinct(String s, String t) {
        if (s == null || t == null)
            return 0;
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 0; i < dp.length; ++i)
            dp[i][0] = 1;
        for (int i = 1; i < dp.length; ++i)
            for (int j = 1; j < dp[0].length; ++j){
                dp[i][j] = (s.charAt(i-1) == t.charAt(j-1)?dp[i-1][j-1]:0) + dp[i-1][j];
            }
        return dp[dp.length-1][dp[0].length - 1];
    }

    //120
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0)
            return 0;
        for (int i = triangle.size() - 2; i >= 0; --i){
            for (int j = 0; j < triangle.get(i).size(); ++j){
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
            }
        }
        return triangle.get(0).get(0);
    }

    //132
    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        boolean[][] parti = getPartition(s);
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < s.length(); ++i){
            for (int j = i; j < s.length(); ++j){
                if (parti[i][j]){
                    dp[j+1] = Math.min(dp[j+1], dp[i]+1);
                }
            }
        }
        return dp[dp.length - 1] - 1;
    }

    private boolean[][] getPartition(String s){
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; --i){
            for (int j = i; j < s.length(); ++j){
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1]))
                    dp[i][j] = true;
            }
        }
        return dp;
    }

    //139
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || wordDict == null)
            return false;
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); ++i){
            if (!dp[i])
                continue;
            for (int j = i; j < s.length(); ++j){
                if (wordDict.contains(s.substring(i, j + 1)))
                    dp[j+1] = true;
            }
        }
        return dp[dp.length - 1];
    }

    //152
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int lmax, lmin, max;
        lmax = lmin = max = nums[0];
        for (int i = 1; i < nums.length; ++i){
            int t = lmax;
            lmax = Math.max(nums[i], Math.max(nums[i] * lmin, nums[i] * lmax));
            lmin = Math.min(nums[i], Math.min(nums[i] * lmin, nums[i] * t));
            max = Math.max(max, lmax);
        }
        return max;
    }

    //198
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; ++i)
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        return dp[dp.length - 1];
    }

    //213
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        //since the first one and last one cannot be robbed at the same time, we dive and conquer by two cases, either rob first, or last, no neither
        return Math.max(rob2Helper(nums, 0, nums.length -2), rob2Helper(nums, 1, nums.length - 1));
    }

    private int rob2Helper(int[] nums, int l, int r){
        if (r == l)
            return nums[l];
        int[] dp = new int[nums.length];
        dp[l] = nums[l];
        dp[l+1] = Math.max(nums[l], nums[l + 1]); //note here all use l-based
        for (int i = l + 2; i <= r; ++i){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[r];
    }

    //256
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;
        //dp[i][j] = costs[i][j] + min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3]) the cost of i,j is costs at i,j + min of the other two in last step
        int[][] dp = new int[costs.length+1][costs[0].length];
        for (int i = 1; i < dp.length; ++i){
            for(int j = 0; j < dp[0].length; ++j)
                dp[i][j] = costs[i-1][j]+Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
        }
        return Math.min(dp[dp.length-1][0], Math.min(dp[dp.length - 1][1],dp[dp.length - 1][2]));
    }

    //279
    public int numSquares(int n) {
        //dp : a number can be added by up to 4 squares, guaranteed. So the possiblity is 1234.
        //for each i, we need to look back for a one that add 1 to the i. dp[i] = Min(dp[i], dp[i-sqare]+1)
        //for this type of problems, we should not for each i look back and loop again and again.
        //instead we push forward from the beginning
        //欲向后看,反从前导。

        if (n < 1)
            return 0;
        int[] dp = new int[n+1]; //we make index and n match and more importantly, we need intial condition dp[0]=0
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; ++i){
            for (int j = 1; i + j*j <=n; ++j){
                dp[i + j*j] = Math.min(dp[i+j*j], dp[i] + 1);
            }
        }
        return dp[n];
    }

    //292
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }


}
