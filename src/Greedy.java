/**
 * Created by zplchn on 8/7/16.
 */
public class Greedy {

    //42
    public int trap(int[] height) {
        if (height == null || height.length<=2)
            return 0;
        int l = 0, r = height.length - 1, res = 0;
        while (l < r){
            int min = Math.min(height[l], height[r]);
            if (height[l] == min){
                ++l;
                while (l < r && height[l] <= min)
                    res += min - height[l++];
            }
            else {
                --r;
                while (l < r && height[r] <= min)
                    res += min - height[r--];
            }
        }
        return res;
    }


    //134
    /*
    1. we start from s and each step we accumulate gas[i] - cost[i], if at point p we r negative and cannot move forward.
    then any points between [s, p] cannot be a start point. -> suppose x between s and p can be a start,
     s -> x -> p is - and x -> p is +, then s->x must be over - and s cannot even reach x.
    2. So the whole pass will be some negative sequence, n1, n2, n3... p >=0 if we made it. and p >= -(n1+n2+...)
     so if we count all gas[i]-cost[i] and is >= 0, we know we have a solution.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
            return 0;
        int total = 0, sum = 0, start = 0;
        for (int i = 0; i < gas.length; ++i){
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0){
                start = i+1;
                sum = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    //122
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int res = 0,delta = 0;
        for (int i = 1; i < prices.length; ++i) {
            delta = prices[i] - prices[i - 1];
            res += delta > 0 ? delta : 0;
        }
        return res;
    }
}
