/*
1781 컵라면

- 문제를 푸는 데 단위시간  1이 걸림
- 데드라인은 N 이하의 자연수
- 컵라면 수

<주의> 컵라면 수는 2^31

>> 데드라인이 같은 것들 중에서 컵라면 수가 높은걸 선택하면 되는 구조 아닌가?

>> 출력: 동호가 받을 수 있는 최대 컵라면 수
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int C;
    static int[] bag;
    static List<Integer> left, right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x, y) -> {
                    if (x[0] != y[0]) {
                        return Integer.compare(x[0], y[0]);
                    } else {
                        return Integer.compare(y[1], x[1]);
                    }
                }
        );

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int ramenCount = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{deadLine, ramenCount});
        }

        PriorityQueue<Integer> ramenHeap = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            ramenHeap.offer(cur[1]); 
            if (ramenHeap.size() > cur[0]) {
                ramenHeap.poll();
            }
        }

        long total = 0;
        while (!ramenHeap.isEmpty()) {
            total += ramenHeap.poll();
        }

        System.out.println(total);
    }
}
