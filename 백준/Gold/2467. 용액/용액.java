import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 2467 용액
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left  = 0, right = N-1;
        int answer_left = -1, answer_right = -1;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        while (left < right) {
            sum = arr[left] + arr[right];
            if (Math.abs(sum) < Math.abs(min)){
                min = sum;
                answer_left = left;
                answer_right = right;
            }
            if (sum == 0){
                break;
            }else if (sum > 0){
                right --;
            }else{
                left ++;
            }
        }

        System.out.println(arr[answer_left]+ " " + arr[answer_right]);
        br.close();
    }
}
