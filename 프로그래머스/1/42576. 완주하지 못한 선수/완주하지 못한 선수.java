import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.StringBuilder;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> marathon = new HashMap<String, Integer>();
        List<String> ret = new ArrayList<String>();
        
        for(String name : participant){
            marathon.put(name, marathon.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion){
            marathon.put(name, marathon.getOrDefault(name, 0) - 1);
        }
        
        for (String key : marathon.keySet()){
            if (marathon.get(key) > 0) {
                return key;
            }
        }
        
        return "";
    }
}