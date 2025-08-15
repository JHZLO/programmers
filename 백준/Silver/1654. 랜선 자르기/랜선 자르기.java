/*
1654

랜선 개수: K, 필요한 개수 : N
K개는 길이

가지고 있는 랜선으로  N개 이상의  랜선을 만들 수 있는 최대 길이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] lines = new int[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lines);

        long right = lines[K - 1];
        long left = 1;
        long count, half;

        while (left <= right) {
            count = 0;
            half = (left + right) / 2;

            for (int i = 0; i < K; i++) {
                count += lines[i] / half;
            }

            if (count < N) {
                right = half - 1;
            } else {
                left = half + 1;
            }
        }

        System.out.println(right);
    }
}
