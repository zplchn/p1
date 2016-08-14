import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zplchn on 8/13/16.
 */

//295
public class MedianFinder {
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //maxheap
    Queue<Integer> minHeap = new PriorityQueue<>();

    // Adds a number into the data structure.
    public void addNum(int num) {
        minHeap.offer(num);
        //suppose 3,2,1here we need to make sure minHeap's min must >= max.max
        maxHeap.offer(minHeap.poll()); //maxheap only get the min from minheap

        if (minHeap.size() < maxHeap.size())
            minHeap.offer(maxHeap.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) //when odd, use minheap's head
            return minHeap.peek();
        return (double)(maxHeap.peek() + minHeap.peek()) / 2;
    }
}
