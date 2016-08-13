import java.util.*;

/**
 * Created by zplchn on 7/9/16.
 */
public class Strings {

    //8
    public int myAtoi(String str) {
        if (str == null)
            return 0;
        str = str.trim();
        if (str.length() == 0)
            return 0;
        boolean isNeg = false;
        int start = 0;
        if (str.charAt(0) =='+')
            start = 1;
        if (str.charAt(0) == '-'){
            start = 1;
            isNeg = true;
        }
        long res = 0;
        for (int i = start; i < str.length(); ++i){
            if (!Character.isDigit(str.charAt(i)))//discard the rest
                break;
            res = res * 10 + str.charAt(i) - '0';
            if (!isNeg && res > Integer.MAX_VALUE) //the string could even > max long
                return Integer.MAX_VALUE;
            else if (isNeg && -res < Integer.MIN_VALUE)//need reverse sign
                return Integer.MIN_VALUE;
        }

        return (int)(isNeg? -res : res);

    }

    //14
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        int min = strs[0].length();
        for (int i = 1; i < strs.length; ++i){
            int j = 0;
            for (; j < Math.min(min, strs[i].length()); ++j)
                if (strs[0].charAt(j) != strs[i].charAt(j))
                    break;
            min = Math.min(min, j);
        }
        return strs[0].substring(0, min);
    }

    //20
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if ("({[".indexOf(c) >= 0)
                stack.push(c);
            else if(stack.isEmpty())
                return false;
            else {
                char t = stack.pop();
                if ((c == ')' && t !='(') || (c == '}' && t !='{') || (c == ']' && t !='['))
                    return false;

            }
        }
        return stack.isEmpty();
    }

    //28
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return -1;
        for (int i = 0; i <= haystack.length() - needle.length(); ++i){
            int j = 0;
            while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j))
                ++j;
            if (j == needle.length())
                return i;
        }
        return -1;
    }

    //38
    public String countAndSay(int n) {
        if (n < 1)
            return "";
        StringBuilder res = new StringBuilder("1");
        StringBuilder t = new StringBuilder();
        int i = 1, j = 1, cnt = 1;

        while (i < n){
            j = cnt = 1;
            while (j < res.length()){
                if (res.charAt(j) == res.charAt(j-1))
                    ++cnt;
                else {
                    t.append(cnt);
                    t.append(res.charAt(j-1));
                    cnt = 1;
                }
                ++j;//dont forget
            }
            t.append(cnt);
            t.append(res.charAt(j-1));
            res = t;
            t = new StringBuilder();
            ++i; //dont forget
        }
        return res.toString();
    }

    //58
    public int lengthOfLastWord(String s) {
        if (s == null)
            return 0;
        String[] tokens = s.split("\\s");
        return tokens.length == 0 ? 0 : tokens[tokens.length - 1].length();
    }


    //67
    public String addBinary(String a, String b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        int carry = 0, i = a.length() - 1, j = b.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0){
            int sum = (i >= 0? a.charAt(i--) - '0': 0) + (j >= 0? b.charAt(j--) - '0': 0) + carry;
            carry = sum / 2;
            sb.append(sum % 2);
        }
        return new String(sb.reverse());

    }

    //71
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return path;
        Deque<String> deque = new ArrayDeque<>();
        String res = "";

        String[] tokens = path.split("/");
        for (String str : tokens){
            if (str.isEmpty()  || str.equals(".")) //dont use == to compare string
                continue;
            else if (str.equals("..")){
                if (!deque.isEmpty())
                    deque.pop();
            }
            else {
                deque.push(str);
            }
        }
        if (deque.isEmpty())
            return "/";
        while (!deque.isEmpty()){
            res += ("/" + deque.pollLast()); //deque when use as stack alsays push/pop at head. Need to pollLast() when queue
        }
        return res;
    }


    //125
    public boolean isPalindrome(String s) {
        if (s == null)
            return false;
        s = s.trim();
        int i = 0, j = s.length() - 1;
        while(i < j){
            if (!Character.isLetterOrDigit(s.charAt(i)))
                ++i;
            else if (!Character.isLetterOrDigit(s.charAt(j)))
                --j;
            else if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--)))
                return false;
        }
        return true;
    }

    //151
    public String reverseWords(String s) {
        if (s == null)
            return s;
        s = s.trim(); //s is possible all whitespace "      "
        if (s.length() == 0)
            return s;
        String[] token = s.split("\\s"); // \\s will only match just one space, so "a  b" will split into "a", "b", ""-> the empty between the middle two spaces
        //while \\s+ will match from 1 to as many as spaces grouped and will result in "a" "b" only
        String res = token[token.length - 1];
        for (int i = token.length - 2; i >= 0; --i)
            res += (" " + token[i]);
        return res;
    }

    //161
    public boolean isOneEditDistance(String s, String t) {
        //1. cannot be diff >= 2. 2. make s shorter or swap, then they cannot be same. or once they diff, shorter(or both) advance and all equal
        if (s == null || t == null)
            return false;
        int diff = s.length() - t.length();
        if (Math.abs(diff) > 1)
            return false;
        //make s shorter
        if (diff > 0)
            return isOneEditDistance(t, s);
        //diff is 0 or -1
        int i = 0;
        while (i < s.length() && s.charAt(i) == t.charAt(i))
            ++i;
        if (i == s.length())
            return diff == -1; //they must differ in len sinc eno content diff
        if (diff == 0)
            ++i;
        while (i < s.length() && s.charAt(i) == t.charAt(i - diff))
            ++i;
        return i == s.length();
    }

    //165
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null)
            return -2;
        String[] v1 = version1.split("\\."); //split uses regex and . need escape \\
        String[] v2 = version2.split("\\.");

        for (int i = 0; i < Math.max(v1.length, v2.length); ++i){
            int gap = (i < v1.length? Integer.parseInt(v1[i]) : 0) - (i < v2.length? Integer.parseInt(v2[i]) : 0);
            if (gap != 0)
                return gap > 0 ? 1 : 0;
        }
        return 0;
    }

    //179
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return "";
        String[] s = new String[nums.length];
        for (int i = 0; i < s.length; ++i)
            s[i] = Integer.toString(nums[i]);
        Arrays.sort(s, (s1, s2) -> (s2 + s1).compareTo(s1 + s2)); //note java string needs to call compareTo
        String res = "";
        for (String str : s)
            res += str;
        while (res.length() > 1 && res.charAt(0) == '0') //deal with nums={0, 0, 0....
            res = res.substring(1);
        return res;

    }

    //186
    public void reverseWords(char[] s) {
        if (s == null || s.length <= 1)
            return;
        reverseWordsHelper(s, 0, s.length - 1);
        int l = 0, r = 1;
        while (r < s.length){
            if (s[r] != ' ')
                ++r;
            else {
                reverseWordsHelper(s, l, r - 1);
                l = r = r + 1;
            }
        }
        reverseWordsHelper(s, l, r - 1);
    }

    private void reverseWordsHelper(char[] s, int l, int r){
        while (l < r){
            char x = s[l];
            s[l] = s[r];
            s[r] = x;
            ++l;
            --r;
        }
    }

    //293
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length() - 1; ++i){
            if (sb.charAt(i) == '+' && sb.charAt(i+1) == '+'){
                sb.replace(i, i+1, "--");
                res.add(sb.toString());
                sb.replace(i, i+1, "++");
            }
        }
        return res;

    }

    //344
    public String reverseString(String s) {
        if (s == null)
            return s;
        return new StringBuilder(s).reverse().toString();

    }







    public static void main(String[] args){
        Strings s = new Strings();
        //s.compareVersion("1", "0");
        s.reverseWords("   a  b ");
    }

















}
