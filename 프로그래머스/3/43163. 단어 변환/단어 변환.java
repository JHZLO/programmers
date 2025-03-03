import java.util.*;

class Solution {
    private int minCount = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        dfs(words, visited, begin, target, 0);
        return (minCount == Integer.MAX_VALUE) ? 0 : minCount; // 변환 불가능한 경우 0 반환
    }

    private void dfs(String[] words, boolean[] visited, String begin, String target, int cnt) {
        if (begin.equals(target)) {
            minCount = Math.min(minCount, cnt); // 최솟값 갱신
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canTransform(begin, words[i])) { // 한 글자만 다른 경우
                visited[i] = true;
                dfs(words, visited, words[i], target, cnt + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }

    private boolean canTransform(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            if (diff > 1) return false; // 2개 이상 다르면 변환 불가
        }
        return diff == 1; // 정확히 1개의 문자만 다를 때 변환 가능
    }
}
