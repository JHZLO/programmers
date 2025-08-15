/*
28069

1. 계단 한 칸을 올라갑니다.
2. 민희가 집에서 가지고 온 지팡이를 계단에 두드립니다. 만약 민희가  i번째에 계단을 두드리면,  i + i/2.floor

계단 개수: N, 오르는 횟수: K

K안에 오를 수 있으면 "minigimbob", 없으면  "water"
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            if (i + 1 <= N) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            }
            if (i + i / 2 <= N) {
                dp[i + i / 2] = Math.min(dp[i + i / 2], dp[i] + 1);
            }
        }
        System.out.println(dp[N] <= K ? "minigimbob" : "water");
    }
}
