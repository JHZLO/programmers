import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
	static int N, M;
	static int[] arr, graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		Main twelve = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		graph = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		twelve.dfs(0, 0);
		System.out.println(sb);
	}
    
    public void dfs(int depth, int value) {
		if(depth == M) {
			for(int x : graph) {
				sb.append(x + " ");
			}
			sb.append("\n");
		}else {
			int before = 0;
			for(int i = value; i<N; i++) {
				if(before != arr[i]) {
					graph[depth] = arr[i];
					before = arr[i];
					dfs(depth+1, i);
				}else {
					continue;
				}
			}
		}
	}
}