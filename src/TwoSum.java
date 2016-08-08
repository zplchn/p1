import java.util.HashMap;
import java.util.Map;

/**
 * Created by zplchn on 7/31/16.
 */
public class TwoSum {
    private Map<Integer, Integer> hm = new HashMap<Integer, Integer>();; //need to use map instead of set, because need two different ones add to the target,
    //otherwise set will find itself when 5 + 5 = 10 but if there is only one 5

    // Add the number to an internal data structure.
    public void add(int number) {
        hm.put(number, hm.containsKey(number) ? 2 : 1);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (int i : hm.keySet()){
            int j = value - i;
            if (hm.containsKey(j) && (i != j || (i == j && hm.get(i) > 1)))
                return true;
        }
        return false;
    }


    public static void main(String[] args){
        TwoSum ts = new TwoSum();
        //ts.add(0);
        System.out.println(ts.find(0));
    }

}
