import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int M;
    private static List<Integer> list;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new TreeSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = new ArrayList<>(set);
        dfs(0, new ArrayList<>(), 0);

        System.out.print(sb);
    }

    private static void dfs(int depth, List<Integer> path, int start) {
        if (depth == M) {
            for (int num : path) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < list.size(); i++) {
            path.add(list.get(i));
            dfs(depth + 1, path, i);  // 중복 사용 가능하므로 i
            path.remove(path.size() - 1);
        }
    }
}
