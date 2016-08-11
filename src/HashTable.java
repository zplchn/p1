import java.util.*;

/**
 * Created by zplchn on 7/4/16.
 */
public class HashTable {

    //1
    public int[] twoSum(int[] nums, int target) {
        int [] res = {-1, -1};
        if (nums == null || nums.length < 2)
            return res;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; ++i){
            if (hm.containsKey(target - nums[i])){
                res[0] = hm.get(target - nums[i]);
                res[1] = i;
                break;
            }
            hm.put(nums[i], i);
        }
        return res;
    }

    //36
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;

        //row
        for (int i = 0; i < 9; ++i){
            Set<Character> hs = new HashSet<>();
            for (char c : board[i]){
                if (c == '.')
                    continue;
                else if (hs.contains(c))
                    return false;
                else
                    hs.add(c);
            }
        }

        //col
        for (int j = 0; j < 9; ++j){
            Set<Character> hs = new HashSet<>();
            for (int i = 0; i < 9; ++i){
                char c = board[i][j];
                if (c == '.')
                    continue;
                else if (hs.contains(c))
                    return false;
                else
                    hs.add(c);
            }
        }

        //box
        for (int k = 0; k < 9; ++k){
            Set<Character> hs = new HashSet<>();
            for (int i = k/3*3; i < k/3*3 + 3; ++i){
                for (int j = k%3*3; j < k%3*3 + 3; ++j){
                    char c = board[i][j];
                    if (c == '.')
                        continue;
                    else if (hs.contains(c))
                        return false;
                    else
                        hs.add(c);
                }
            }
        }
        return true;
    }

    //49
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0)
            return res;
        Map<String, List<String>> hm = new HashMap<>();
        for (String s : strs){
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String ss = new String(ca);
            if (!hm.containsKey(ss))
                hm.put(ss, new ArrayList<String>());
            hm.get(ss).add(s);
        }
        //values is java.util.Collection need to use ArrayList<>(Collection c) to create a new ArrayList
        return new ArrayList<List<String>>(hm.values());
    }

    //149
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
                else if (points[i].y == points[i].y) //+0.0 -0.0
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

    //187
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        Map<String, Boolean> hm = new HashMap<>(); //note boolean needs to be Boolean
        for (int i = 0; i <= s.length() - 10; ++i){
            String str = s.substring(i, i+10);
            if (hm.containsKey(str)){
                if (!hm.get(str)){
                    hm.put(str, true);
                    res.add(str);
                }
            }
            else
                hm.put(str, false);
        }
        return res;
    }



    //205
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;
        Map<Character, Character> hm = new HashMap<>();
        for (int i = 0; i < s.length(); ++i){
            char sc = s.charAt(i), tc = t.charAt(i);
            if (!hm.isEmpty() && hm.containsKey(sc)){
                if (hm.get(sc) != tc)
                    return false;
            }
            else if(hm.containsValue(tc)){
                return false;
            }
            else {
                hm.put(sc, tc);
            }
        }
        return true;
    }

    //217
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        Set<Integer> hs = new HashSet<>();
        for (int i : nums){
            if (hs.contains(i))
                return true;
            hs.add(i);
        }
        return false;
    }

    //219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; ++i){
            if (hm.containsKey(nums[i]) && i - hm.get(nums[i]) <= k)
                return true;
            hm.put(nums[i], i);
        }
        return false;
    }

    //245
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length < 1)
            return Integer.MAX_VALUE;
        Map<String, Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < words.length; ++i){
            if (words[i].equals(word1)){
                if (hm.containsKey(word2)){
                    min = Math.min(min, i - hm.get(word2));
                }
                hm.put(word1, i);
            }
            else if (words[i].equals(word2)){
                if (hm.containsKey(word1)){
                    min = Math.min(min, i - hm.get(word1));
                }
                hm.put(word2, i);
            }
        }
        return min;
    }

    //249
    public List<List<String>> groupStrings(String[] strings) {
        //use offset to create a map and group strings into this map. yx = 25 cuz for neg ones we +26 %26 == az
        List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length == 0)
            return res;
        Map<String, List<String>> hm = new HashMap<>();

        for (String s : strings){
            if (s == null || s.length() == 0)
                continue;
            String code = "";
            for (char c : s.toCharArray()){ //need to do char math, better directly convert to char array
                code += ((c - s.charAt(0) + 26) % 26 + ","); //offset1,offset2, offset3 ... as code and use as key
            }
            if (!hm.containsKey(code))
                hm.put(code, new ArrayList<String>());
            hm.get(code).add(s);
        }
        return new ArrayList<List<String>>(hm.values());
    }

    //266
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            hm.put(s.charAt(i), hm.containsKey(s.charAt(i)) ? hm.get(s.charAt(i)) + 1 : 1);
        boolean hasOdd = false;
        for (int i : hm.values()){
            if ((i & 1) == 1){
                if (hasOdd)
                    return false;
                hasOdd = true;
            }
        }
        return true;
    }

    //290
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null)
            return false;
        String[] tokens = str.split("\\s"); // \s in regex means any space, and in java we need to first escape the \
        if (tokens.length != pattern.length())
            return false;
        Map<Character, String> hm = new HashMap<>();
        for (int i = 0; i < pattern.length(); ++i){
            if (hm.containsKey(pattern.charAt(i))){ //contains do not need to check isEmpty() first
                if (!hm.get(pattern.charAt(i)).equals(tokens[i])) //here must use equals, compare value. == compares reference not content
                    return false;
            }
            else if (hm.containsValue(tokens[i]))
                return false;
            else
                hm.put(pattern.charAt(i), tokens[i]);
        }
        return true;
    }

    //299
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() != guess.length())
            return "";
        int bull = 0, cow = 0;
        //1 3 2
        //2 3 1
        //1. if equal then bump bull. 2. for top cnt++, for bottom cnt--. if cnt(top) < 0 or cnt(bottom) > 0, means a cow
        int[] cnt = new int[10]; //only 0 - 9. for char, use 256 when it's sure only digits/chars, use array is faster than map
        for (int i = 0; i < secret.length(); ++i){
            if (secret.charAt(i) == guess.charAt(i))
                ++bull;
            else{
                if (cnt[secret.charAt(i) - '0']++ < 0) //as requested before, owe now repay
                    ++cow;
                if (cnt[guess.charAt(i) - '0']-- > 0) //as extra before, now decrease
                    ++cow;
            }
        }
        return bull + "A" + cow + "B";
    }











    public static void main(String[] args){
        HashTable ht = new HashTable();
        boolean b = ht.wordPattern("abba", "dog cat cat dog");
        System.out.print(b);



    }



















}
