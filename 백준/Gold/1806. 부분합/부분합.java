import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
left, right 투 포인터로 index 0부터 시작
- sum < S : right ++
- sum > S : left ++
- sum == S : right - left 반환 ~> Math.min()
*/

// 1806 부분합
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (true) {
            if (sum >= S) {
                minLength = Math.min(minLength, right - left);
                sum -= arr[left++];
            } else if (right == N) {
                break;
            } else {
                sum += arr[right++];
            }
        }
        if (minLength == Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(minLength);
        }
    }
}
