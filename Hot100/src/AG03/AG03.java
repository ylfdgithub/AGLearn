package AG03;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 */
public class AG03 {

    public static int lengthOfLongestSubstring(String s) {
        int max=0;
        Set<Character> set = new HashSet<>();
        int p2 = 0;
        for (int p1 = 0; p1 < s.length(); p1++) {
            if (!set.contains(s.charAt(p1))){
                set.add(s.charAt(p1));
                if(p1==s.length()-1) max = Math.max(p1-p2+1,max);
            } else {
                max = Math.max(max,p1-p2);
                while (s.charAt(p2)!=s.charAt(p1)){
                    set.remove(s.charAt(p2));
                    p2++;
                }
                p2++;
            }
        }
        return max;
    }
}
