import java.util.*;

/**
 * Created by zplchn on 7/5/16.
 */
public class BFS {

    //102
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur = 1, next = 0;
        List<Integer> lvl = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            lvl.add(tn.val);

            if (tn.left != null) {
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null) {
                queue.offer(tn.right);
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

    //103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> lvl = new ArrayList<>();
        queue.offer(root);
        int cur = 1, next = 0;
        boolean rev = false;

        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            lvl.add(tn.val);

            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }

            if (--cur == 0){
                if (rev)
                    Collections.reverse(lvl);
                rev = !rev;
                res.add(lvl);
                lvl = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        return res;
    }

    //107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur = 1, next = 0;
        List<Integer> lvl = new ArrayList<>();

        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            lvl.add(tn.val);

            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }

            if (--cur == 0){
                res.add(lvl);
                lvl = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        Collections.reverse(res);
        return res;
    }

    //130 surrounded regions

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        //start from 4 edges and flood fill with another marker
        for (int i = 0; i < board.length; ++i){
            if (board[i][0] == 'O')
                floodFill(board, i, 0);
            if (board[i][board[0].length - 1] == 'O')
                floodFill(board, i, board[0].length - 1);
        }
        for (int j = 0; j < board[0].length; ++j){
            if (board[0][j] == 'O')
                floodFill(board, 0, j);
            if (board[board.length - 1][j] == 'O')
                floodFill(board, board.length - 1, j);
        }

        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[0].length; ++j){
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '~')
                    board[i][j] = 'O';
            }

    }

    private void floodFill(char[][] board, int m, int n){
        Deque<Integer> queue = new ArrayDeque<>();
        board[m][n] = '~';
        queue.offer(m * board[0].length + n);

        while (!queue.isEmpty()){
            int s = queue.poll();
            int r = s / board[0].length;
            int c = s % board[0].length;

            if (r > 0 && board[r - 1][c] == 'O'){
                board[r - 1][c] = '~';
                queue.offer((r-1)*board[0].length + c);
            }
            if (r < board.length - 1 && board[r + 1][c] == 'O') {
                board[r + 1][c] = '~';
                queue.offer((r + 1) * board[0].length + c);
            }
            if (c > 0 && board[r][c-1] == 'O'){
                board[r][c-1] = '~';
                queue.offer(r*board[0].length + c - 1);
            }
            if (c < board[0].length - 1 && board[r][c+1] == 'O'){
                board[r][c+1] = '~';
                queue.offer(r*board[0].length + c+1);
            }
        }

    }

    //199
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        int cur = 1, next = 0;
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            //--cur;
            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }
            if (cur-- == 1){
                res.add(tn.val);
                cur = next;
                next = 0;
            }
        }
        return res;
    }

    //200
    private int num = 0;
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        for (int i = 0; i < grid.length; ++i){
            for (int j = 0; j < grid[0].length; ++j){
                if (grid[i][j] == '1') {
                    numIslandsHelper(grid, i, j);
                    this.num++;
                }
            }
        }
        return this.num;

    }

    private void numIslandsHelper(char[][] grid, int m, int n){
        Deque<Integer> queue = new ArrayDeque<>();
        grid[m][n] = '0';
        queue.offer(m * grid[0].length + n);
        while (!queue.isEmpty()){
            int s = queue.poll();
            int r = s / grid[0].length;
            int c = s % grid[0].length;

            if (r > 0 && grid[r - 1][c] == '1'){
                grid[r - 1][c] = '0';
                queue.offer((r-1)*grid[0].length + c);
            }
            if (r < grid.length - 1 && grid[r + 1][c] == '1'){
                grid[r + 1][c] = '0';
                queue.offer((r+1)*grid[0].length + c);
            }
            if (c > 0 && grid[r][c-1] == '1'){
                grid[r][c-1] = '0';
                queue.offer(r*grid[0].length + c-1);
            }
            if (c < grid[0].length - 1 && grid[r][c+1] == '1'){
                grid[r][c+1] = '0';
                queue.offer(r*grid[0].length + c+1);
            }
        }
    }
























}
