import java.util.Arrays;

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


    public static void main(String[] args){

        Sort st = new Sort();
        int [] a = {};
        st.mergeSort(a);
        System.out.println(Arrays.toString(a));



    }










}
