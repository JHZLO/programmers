/*
1450 냅색문제

N,C: N개의 물건을 가지고 있고 최대 C만큼 무게를 넣을 수 있는 가방을 하나 가지고 있다.
둘째 줄 : N개의 무게
>>가방에 넣는 방법의 수
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int C;
    static int[] bag;
    static List<Integer> left, right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        bag = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bag[i] = Integer.parseInt(st.nextToken());
        }

        left = new ArrayList<>();
        right = new ArrayList<>();
        combination(left, 0, N / 2, 0);
        combination(right, N / 2, N, 0);

        left.sort((a, b) -> (a - b));
        right.sort((a, b) -> (a - b));

        int cnt = 0;
        int idx;
        for (int i = 0; i < left.size(); i++) {
            idx = upperbound(0, right.size() - 1, left.get(i)); // left의 부분집합 중 차례대로 값 하나랑, right의 부분집합 중 헙과 비교
            cnt += idx;
        }
        System.out.println(cnt);
        br.close();
    }

    public static void combination(List<Integer> list, int start, int end, int sum) {
        if (sum > C) {
            return;
        }
        if (start == end) {
            list.add(sum);
            return;
        }
        combination(list, start + 1, end, sum); // 현재 원소를 포함하지 않음
        combination(list, start + 1, end, sum + bag[start]); // 현재 원소를 포함함
    }

    // left의 부분집합 한개와 right의 부분집합 한 개를 최대로 가져갈 수 있는 idx를 가져오기
    public static int upperbound(int start, int end, int leftVal) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (right.get(mid) <= C - leftVal) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end  + 1; // 인덱스 + 1
    }
}
