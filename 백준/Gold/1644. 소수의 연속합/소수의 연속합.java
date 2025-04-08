import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> prime = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        prime.add(2);
        for (int i = 3; i <= N; i++) {
            if (isPrime(i)) {
                prime.add(i);
            }
        }

        int left = 0, right = 0, sum = 0, cnt = 0;

        while (true) {
            if (sum >= N){
                sum -= prime.get(left++);
            }else if (right == prime.size()){
                break;
            }else{
                sum += prime.get(right++);
            }
            if (sum == N){
                cnt ++;
            }
        }
        System.out.println(cnt);
        br.close();
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
