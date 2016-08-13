import java.util.Arrays;
import java.util.List;
import java.util.Set;

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


}
