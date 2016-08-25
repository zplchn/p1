import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zplchn on 8/21/16.
 */
public class Vector2D implements Iterator<Integer> {
    int index;
    List<Iterator<Integer>> iter;

    public Vector2D(List<List<Integer>> vec2d) {
        iter = new ArrayList<>();
        for (List<Integer> l : vec2d){
            if (!l.isEmpty())
                iter.add(l.iterator()); //only care the non-empty array
        }
    }


    @Override
    public Integer next() {
        Integer i = iter.get(index).next(); //note here need to point to the next available otherwise when one line ends will return false
        if (!iter.get(index).hasNext())
            ++index;
        return i;
    }

    @Override
    public boolean hasNext() {
        return index < iter.size();
    }

    public static void main(String[] args){
        List<List<Integer>> ll = new ArrayList<>();
        ll.add(Arrays.asList(1,2));
        ll.add(Arrays.asList(3));
        ll.add(Arrays.asList(4,5));
        Vector2D v= new Vector2D(ll);
        while (v.hasNext())
            System.out.println(v.next());

    }


}
