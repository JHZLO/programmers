/*
25325

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, String> integerToStudent = new HashMap<>();
        Map<String, Integer> studentToInteger = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        int[] popularIndex = new int[N];
        Arrays.fill(popularIndex, 0);

        String[] students = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            integerToStudent.putIfAbsent(i, students[i]);
            studentToInteger.putIfAbsent(students[i], i);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                popularIndex[studentToInteger.get(st.nextToken())]++;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a[1] != b[1]) {
                        return Integer.compare(b[1], a[1]);
                    }
                    return Integer.compare(a[0], b[0]);
                }
        );
        for (int i = 0; i < N; i++) {
            pq.offer(new int[]{i, popularIndex[i]});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            System.out.println(integerToStudent.get(cur[0]) + " " + cur[1]);
        }
    }
}
