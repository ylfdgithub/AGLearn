package AG11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AG11 {
    public boolean isValid(String s) {
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        map.put('?','?');
        LinkedList<Character> stack = new LinkedList<>();
        stack.add('?');
        if (!map.containsKey(s.charAt(0))) return false;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) stack.add(s.charAt(i));
            else if (map.get(stack.removeLast())!=s.charAt(i)) return false;
        }
        return stack.size()==1;
    }
}
