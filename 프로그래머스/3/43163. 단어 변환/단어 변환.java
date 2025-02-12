import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        Queue<String> que = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        que.offer(begin);
        int cnt = 0;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String x = que.poll();
                
                if (x.equals(target)) {
                    return cnt;
                }

                for (int j = 0; j < words.length; j++) {
                    if (!visited[j] && canConvert(x, words[j])) {
                        visited[j] = true;
                        que.offer(words[j]);
                    }
                }
            }
            cnt++; 
        }

        return 0; 
    }
    
    public boolean canConvert(String x, String word) {
        int diff = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != word.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return diff == 1;
    }
}
