/*
7662 이중 우선순위 큐

- INSERT
- DELETE
    - DELETE 1 : 최댓값 삭제
    - DELETE -1: 최솟값 삭제

모든 연산을 진행하고 Queue에 남아있는 값 중 최댓값과 최솟값 구하기.

큐에 들어간 정수 값이 우선순위
 */

import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    static Map<Integer, Integer> countMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            minHeap.clear();
            maxHeap.clear();
            countMap = new HashMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (op.equals("I")) {
                    insert(num);
                } else {
                    if (num == 1) delete(maxHeap);
                    else delete(minHeap);
                }
            }

            clean(minHeap);
            clean(maxHeap);

            if (countMap.isEmpty()) sb.append("EMPTY\n");
            else {
                int max = maxHeap.peek();
                int min = minHeap.peek();
                sb.append(max).append(" ").append(min).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void insert(int num) {
        minHeap.offer(num);
        maxHeap.offer(num);
        countMap.put(num, countMap.getOrDefault(num, 0) + 1);
    }

    private static void delete(PriorityQueue<Integer> heap) {
        clean(heap);
        if (!heap.isEmpty()) {
            int num = heap.poll();
            countMap.put(num, countMap.get(num) - 1);
            if (countMap.get(num) == 0) countMap.remove(num);
        }
    }

    private static void clean(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && !countMap.containsKey(heap.peek())) {
            heap.poll();
        }
    }
}
