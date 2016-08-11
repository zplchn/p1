import java.util.Iterator;

/**
 * Created by zplchn on 8/8/16.
 */

//284
public class PeekingIterator implements Iterator<Integer> {

    //we cache if peek. othersie behave the same as Iterator<E>
    private boolean hasNext;
    private int next;
    private Iterator<Integer> iter;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
    }


    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (hasNext)
            return next;
        next = iter.next();
        hasNext = true;
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (hasNext){
            hasNext = !hasNext;
            return next;
        }
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return hasNext || iter.hasNext();
    }

}
