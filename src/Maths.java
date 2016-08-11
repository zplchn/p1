import java.util.*;

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

    //43
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0 )
            return "";
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        StringBuilder s1 = new StringBuilder(num1).reverse();
        StringBuilder s2 = new StringBuilder(num2).reverse();
        StringBuilder res = new StringBuilder();
        int[] m = new int[s1.length() + s2.length()];

        for (int i = 0; i < s1.length(); ++i)
            for (int j = 0; j < s2.length(); ++j)
                m[i+j] += (s1.charAt(i) - '0') * (s2.charAt(j) - '0'); //here is += cuz one slot could be hit multiple times

        int carry = 0, i = 0;
        while (i < m.length || carry != 0){
            m[i] += carry;
            res.append(m[i] % 10);
            carry = m[i] / 10;
            ++i;
        }
        res = res.reverse();
        if (res.charAt(0) == '0')
            res.deleteCharAt(0); //when no carry
        return res.toString();

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

    //89
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if (n < 0) //graycode 0 is 0
            return res;
        for (int i = 0; i < (1 << n); ++i){
            res.add(i ^ (i >> 1)); //xor i>>1
        }
        return res;
    }

    //121
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int max = 0, min = prices[0];//max is 0 meaning no transaction
        for (int i = 1; i < prices.length; ++i){
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    //150
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (String s : tokens){
            if ("+-*/".contains(s)){
                int b = stack.pop(); //note b first and then a
                int a = stack.pop();
                switch(s){
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            }
            else
                stack.push(Integer.parseInt(s));
        }
        return stack.pop();
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

    //172
    public int trailingZeroes(int n) {
        int res = 0;
        while (n >= 5){
            res += n / 5;
            n /= 5;
        }
        return res;
    }

    //202
    public boolean isHappy(int n) {
        if (n <= 0)
            return false;
        Set<Integer> hs = new HashSet<>();

        while(n != 1){
            if (hs.contains(n))
                return false;
            hs.add(n); //must add before test
            int sum = 0;
            while (n != 0){
                int t = n % 10;
                n /= 10;
                sum += t * t;
            }
            n = sum;
        }
        return true;
    }

    //223
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //sum of two . no overlap,(top/btm/left/right) overlap
        int sum = (G - E) * (H - F) + (C - A) * (D - B);
        if (H < B || C < E || D < F || G < A)
            return sum;
        int right = Math.min(C, G);
        int left = Math.max(A, E);
        int top = Math.min(D, H);
        int btm = Math.max(B, F);
        return sum - (right - left) * (top - btm);
    }


    //258
    /*
    20的所有的树根：repeat every 9. so we need to mod 9 to get the digital root. when use natual number to mod, must -1 to make cover 0 case
and becuase resulte is 1 based so add 1 in the end
1    1
2    2
3    3
4    4
5    5
6    6
7    7
8    8
9    9
10    1
11    2
12    3
13    4
14    5
15    6
16    7
17    8
18    9
19    1
20    2
     */

    public int addDigits(int num) {
        if (num <= 0)
            return 0;
        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args){
        Maths m = new Maths();
        boolean b = m.isHappy(7);
        System.out.println(b);
    }




















}
