import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
        s.compareVersion("1", "0");
    }

















}
