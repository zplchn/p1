import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zplchn on 8/2/16.
 */

//244
public class WordDistance {
    private Map<String, List<Integer>> hm;

    public WordDistance(String[] words) {
        hm = new HashMap<>();
        if (words == null)
            return;
        for (int i = 0; i < words.length; ++i){
            if (!hm.containsKey(words[i]))
                hm.put(words[i], new ArrayList<Integer>());
            hm.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = hm.get(word1);
        List<Integer> l2 = hm.get(word2);
        int min = Integer.MAX_VALUE, i = 0, j = 0;
        while (i < l1.size() && j < l2.size()){
            min = Math.min(min, Math.abs(l1.get(i) - l2.get(j)));
            if (l1.get(i) < l2.get(j))
                ++i;
            else
                ++j;
        }
        return min; // one array finshes, no need to compare the rest cuz wont be smaller
    }
}
