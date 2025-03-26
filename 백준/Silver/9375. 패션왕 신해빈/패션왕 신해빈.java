import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 9375 패션왕 신해빈
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++){
            int M = Integer.parseInt(br.readLine());
            Map<String, List<String>> clothes = new HashMap<>();
            for (int j = 0; j < M; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cloth = st.nextToken();
                String type = st.nextToken();

                clothes.putIfAbsent(type, new ArrayList<>());
                clothes.get(type).add(cloth);
            }

            int sum = 1;
            for (List<String> c : clothes.values()){
                sum *= c.size() + 1;
            }
            System.out.println(sum - 1);
        }
    }
}
