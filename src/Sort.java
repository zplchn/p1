import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zplchn on 6/26/16.
 */
public class Sort {
    /***
     * Merge Sort - Divide and Conquer o(nlogn)
     */

    public void mergeSort(int [] a){
        mergeSort(a, new int[a.length], 0, a.length - 1);
    }

    private void mergeSort(int[] a, int [] tmp, int l, int r){
        if (l < r){
            int m = (l + r) >> 1;
            mergeSort(a, tmp, l, m);
            mergeSort(a, tmp, m + 1, r);
            merge(a, tmp, l, m, m + 1, r);
        }
    }

    private void merge(int[] a, int[] tmp, int ll, int lr, int rl, int rr){
        int k = ll, t = ll;
        while (ll <= lr && rl <= rr){
            tmp[k++] = a[ll] < a[rl] ? a[ll++] : a[rl++];
        }
        while (ll <= lr){
            tmp[k++] = a[ll++];
        }
        while (rl <= rr){
            tmp[k++] = a[rl++];
        }
        while (t <= rr){
            a[t] = tmp[t++];
        }
    }

    //56
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return res;
        intervals.sort((i1, i2) -> i1.start - i2.start);

        res.add(intervals.get(0));
        for (int i = 0; i < intervals.size(); ++i){
            if (res.get(res.size()-1).end >= intervals.get(i).start)
                res.get(res.size()-1).end = Math.max(res.get(res.size()-1).end, intervals.get(i).end);
            else
                res.add(intervals.get(i));
        }
        return res;
    }

    //57
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || newInterval == null)
            return res;
        int i = 0;
        while (i < intervals.size() && newInterval.start > intervals.get(i).end) {
            res.add(intervals.get(i++));
        }

        while (i < intervals.size() && newInterval.end >= intervals.get(i).start){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            ++i; //possibly absort nothing if the interval.end is smaller than the next where it's inserted
        }
        res.add(newInterval);

        while (i < intervals.size())
            res.add(intervals.get(i++));

        return res;
    }

    //242
    public boolean isAnagram(String s, String t) {
        if (s == null)
            return t == null;
        if (t == null)
            return false;
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();

        Arrays.sort(sa);
        Arrays.sort(ta);

        return new String(sa).equals(new String(ta));

    }

    //252
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return true;
        Arrays.sort(intervals,(i1, i2) -> i1.start - i2.start);
        for (int i = 0; i < intervals.length - 1; ++i){
            if (intervals[i].end > intervals[i+1].start)
                return false;
        }
        return true;
    }


    public static void main(String[] args){

        Sort st = new Sort();
        int [] a = {};
        st.mergeSort(a);
        System.out.println(Arrays.toString(a));



    }










}
