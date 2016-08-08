import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 7/31/16.
 */

//155
public class MinStack {
    Deque<Integer> stack;
    Deque<Integer> minst;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<Integer>();
        minst = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minst.isEmpty() || x <= minst.peek())
            minst.push(x);
    }

    public void pop() {
        int x = stack.pop();
        if (!minst.isEmpty() && minst.peek() == x)
            minst.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minst.peek();
    }
}
