import java.util.*;

/**
 * Created by zplchn on 8/20/16.
 */


public class Codec {

    /* 271 */
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        // use len + # to encode
        if (strs == null || strs.size() == 0)
            return "";
        StringBuilder res = new StringBuilder();
        for (String s : strs){
            res.append(s.length()); //stringbuilder is faster than string + string
            res.append("#");
            res.append(s);
        }
        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        if (s == null)
            return res;
        int start = 0;
        while (start < s.length()){
            int i = s.indexOf('#', start); //indexof(char, startINDEX) //every subsring is costing lot of time. should minimize
            int len = Integer.parseInt(s.substring(start, i));
            res.add(s.substring(i+1, i +len+1));
            start = i + len + 1;
        }
        return res;
    }

    /* END 271 */

    /* 297 */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb){
        if (root == null){
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(',');
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0)
            return null;
        Deque<String> t = new ArrayDeque<>(Arrays.asList(data.split(",")));//deque is much faster than list.remove(0) ->copy every time
        return deserializeHelper(t);
    }

    private TreeNode deserializeHelper(Deque<String> t){
        if (t.isEmpty()){
            return null;
        }
        String s = t.poll();
        if (s.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserializeHelper(t);
        root.right = deserializeHelper(t);
        return root;
    }


    /* END 297 */
    public static void main(String[] args){
        String s = ",a,";
        String[] ss = s.split(",");
        System.out.println();
    }
}
