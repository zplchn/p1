import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zplchn on 8/21/16.
 */

//281
public class ZigzagIterator {

    private List<Iterator<Integer>> iter;
    int turn;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iter = new ArrayList<>(2); //JAVA DOES NOT ALLOW ARRAY OF GENERICS!!!!!!!!!!!! MUST BE COLLECTION TYPES
        iter.add(v1.iterator());
        iter.add(v2.iterator());
    }

    public int next() {
        //end condition first
        if (!iter.get(0).hasNext())
            return iter.get(1).next();
        else if (!iter.get(1).hasNext())
            return iter.get(0).next(); //generate exception when both are used up
        else {
            int res = iter.get(turn).next();
            turn = (turn + 1) % 2; //inter switch
            return res;
        }
    }

    public boolean hasNext() {
        return iter.get(0).hasNext() || iter.get(1).hasNext();
    }

    public static void main(String[] args){
        ZigzagIterator z = new ZigzagIterator(Arrays.asList(1,2),Arrays.asList(3,4,5,6));
        while (z.hasNext())
            System.out.println(z.next());

    }
}
