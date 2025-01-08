import java.util.*;
import java.util.stream.Collectors;
/*
1. 속한 노래가 많이 재생된 장르 먼저 수록.
2. 장르 내에서 많이 재생된 노래를 먼저 수록.
3. 재생횟수 같으면 고유번호 낮은 순으로 수록.

자료구조
HashMap<genre, HashMap<id, plays>>

- plays의 value의 개수 => 속한 노래의 개수
- plays의 value의 합 => 많이 재생된 노래
- plays의 순서 => plays값을 기준으로 내림차순 진행
    - 재생횟수 같으면 고유번호 낮은 게 먼저 오도록

* 주의
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택.
- 모든 장르는 재생된 횟수가 다름.
- 장르 별로 가장 많이 재생된 노래를 최대 두 개까지만 
*/
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> results = new ArrayList<>();
        
        Map<String, Map<Integer, Integer>> streaming = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            // 장르별로 데이터 저장
            streaming.putIfAbsent(genres[i], new HashMap<>());
            streaming.get(genres[i]).put(i, plays[i]);
        }

        for (String genre : streaming.keySet()) {
            Map<Integer, Integer> songs = streaming.get(genre);

            // 재생 횟수 내림차순 -> 고유번호 오름차순
            List<Integer> keySet = new ArrayList<>(songs.keySet());
            keySet.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (!songs.get(o1).equals(songs.get(o2))) {
                        return songs.get(o2).compareTo(songs.get(o1)); // 재생 횟수 내림차순
                    }
                    return o1.compareTo(o2); // 고유번호 오름차순
                }
            });

            Map<Integer, Integer> sortedSongs = new LinkedHashMap<>();
            for (Integer key : keySet) {
                sortedSongs.put(key, songs.get(key));
            }
            
            streaming.put(genre, sortedSongs);
        }
        
        // 속한 노래 재생된 합 순서 내림차순 정렬
        Map<String, Map<Integer, Integer>> sortedStreaming = streaming.entrySet()
            .stream()
            .sorted((entry1, entry2) -> {
                int sum1 = entry1.getValue().values().stream().mapToInt(Integer::intValue).sum();
                int sum2 = entry2.getValue().values().stream().mapToInt(Integer::intValue).sum();
                
                return Integer.compare(sum2, sum1);
            })
            .collect(Collectors.toMap(
                Map.Entry :: getKey,
                Map.Entry :: getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
        
        for (String key : sortedStreaming.keySet()){
            int count = 0;
            
            Map<Integer, Integer> innerMap = sortedStreaming.get(key);
            
            for (Integer id : innerMap.keySet()) {
                if (count < 2) { // 상위 2개까지만
                    results.add(id);
                    count++;
                } else {
                    break;
                }
            }
        }
                    
        return results.stream().mapToInt(Integer::intValue).toArray();
    }
}