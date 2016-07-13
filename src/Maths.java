/**
 * Created by zplchn on 7/10/16.
 */
public class Maths {
    //7
    public int reverse(int x) {
        long res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
            res = 0;
        return (int)res;
    }

    //9
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10)
            div *= 10;
        while (x > 0){
            if (x / div != x % 10)
                return false;
            x = x % div / 10;
            div /= 100;
        }
        return true;
    }

    //66
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return digits;
        int i = digits.length -1;
        while (i >= 0){
            if (digits[i] != 9) {
                ++digits[i];
                return digits;
            }
            digits[i--] = 0;
        }
        int [] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    //168
    public String convertToTitle(int n) {
        if (n <= 0)
            return "";
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            n -=1; //convert to 0 based for every time eg 27
            sb.insert(0, (char)(n % 26 + 'A'));
            n /= 26;
        }
        return sb.toString();
    }









    //171
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;

        for (int i = 0; i < s.length(); ++i){
            res = res * 26 + Character.toUpperCase(s.charAt(i)) - 'A' + 1;
        }
        return res;
    }


















}
