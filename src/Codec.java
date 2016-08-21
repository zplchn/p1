import java.util.ArrayList;
import java.util.List;

/**
 * Created by zplchn on 8/20/16.
 */

//271
public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        // use len + # to encode
        if (strs == null || strs.size() == 0)
            return "";
        StringBuilder res = new StringBuilder();
        for (String s : strs){
            res.append(s.length()); //stringbuilder is faster than string + string
            res.append("#");
            res.append(s);
        }
        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        if (s == null)
            return res;
        int start = 0;
        while (start < s.length()){
            int i = s.indexOf('#', start); //indexof(char, startINDEX) //every subsring is costing lot of time. should minimize
            int len = Integer.parseInt(s.substring(start, i));
            res.add(s.substring(i+1, i +len+1));
            start = i + len + 1;
        }
        return res;
    }
}
