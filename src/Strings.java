import java.util.ArrayDeque;
import java.util.Deque;

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
























}
