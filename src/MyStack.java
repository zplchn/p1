import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 7/31/16.
 */

//225
public class MyStack {
    private Deque<Integer> mn = new ArrayDeque<>();
    private Deque<Integer> se = new ArrayDeque<>();
    private Deque<Integer> t;

    // Push element x onto stack.
    public void push(int x) {
        mn.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        while (mn.size() > 1)
            se.offer(mn.poll());
        mn.poll();
        t = mn;
        mn = se;
        se = t;
    }

    // Get the top element.
    public int top() {
        while (mn.size() > 1)
            se.offer(mn.poll());
        int x = mn.poll();
        se.offer(x);
        t = mn;
        mn = se;
        se = t;
        return x;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return mn.isEmpty();
    }

}
