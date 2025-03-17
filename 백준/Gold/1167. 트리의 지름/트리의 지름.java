import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//1167. 트리의 지름 
public class Main {
	static class Node{
		int to;
		int cost; 
		public Node(int to, int cost) {
			this.to=to;
			this.cost=cost;
		}
	}

	static int V;
	static ArrayList<Node> edges[];
	static boolean visit[];
	static int candidate;
	static int answer;
	static int max;

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V=Integer.parseInt(br.readLine());
		edges = new ArrayList[V+1];
		visit = new boolean[V+1];
		
		for(int i=0; i<=V; i++) {
			edges[i]= new ArrayList<Node>();
		}
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(br.readLine());
			int vertex = Integer.parseInt(st.nextToken());
			while(true) {
				int next = Integer.parseInt(st.nextToken());
				if(next==-1)
					 break;
				int cost = Integer.parseInt(st.nextToken());
				edges[vertex].add(new Node(next,cost));
			}
		}
		

		dfs(1,0); // 임의의 한 지점에서 dfs를 돌려서 이 정점으로 부터 가장 먼 정점 구하기 
		
		visit=new boolean[V+1];
		dfs(candidate, 0);
		
		System.out.println(max);
	}
	
	static public void dfs(int v, int len) {
		if(len>max) {
			max=len;
			candidate=v;
		}
		visit[v]=true;
		for(int i=0; i<edges[v].size(); i++) {
			Node next = edges[v].get(i);
			if(visit[next.to]==false) {
				dfs(next.to,len+next.cost);
			}
		}
	}
}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

//1167. 트리의 지름
public class Main {
    private static Map<Integer, List<int[]>> graph;
    private static int V;
    private static int max;
    private static boolean[] visited;
    private static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        graph = new HashMap<>();

        V = Integer.parseInt(br.readLine());

        for (int i = 1; i <= V; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());

                graph.get(vertex).add(new int[]{to, cost});
            }
        }

        visited = new boolean[V + 1];
        max = 0;
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(n, 0);

        System.out.println(max);
    }

    private static void dfs(int start, int distance) {
        if (distance > max) {
            max = distance;
            n = start;
        }
        visited[start] = true;

        for (int i = 0; i < graph.get(start).size(); i++) {
            int[] v = graph.get(start).get(i);
            if (!visited[v[0]]) {
                visited[v[0]] = true;
                dfs(v[0], distance + v[1]);
            }
        }
    }
}
