/*
1부터 1000개의 정거장

버스 갈아탈 때 탑승료 다시 지불
버스가 마지막 지점에 도착하면 반드시 내려야함 -> 버스로 연속된 두 지점 사이 이동하는데에 1초

A지점부터 B지점까지 이동할 때 필요한 최소 비용과 버스 탑승 시간 중 최솟값을 구하는 프로그램

- A의 번호, B의 번호, 버스의 수 N
- 버스 탑승료, 통과하는 지점의 개수
- 통과하는 점

*/
import java.util.*;

public class Main {
    static class State implements Comparable<State> {
        int stop;     // 정류장
        int bus;      // 현재 타고 있는 버스 번호 (0이면 아직 안 탐)
        int cost;     // 누적 비용
        int time;     // 누적 시간

        State(int stop, int bus, int cost, int time) {
            this.stop = stop;
            this.bus = bus;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(State o) {
            if (this.cost == o.cost) return Integer.compare(this.time, o.time);
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();  // 시작 정류장
        int B = sc.nextInt();  // 도착 정류장
        int N = sc.nextInt();  // 버스 개수

        // 버스 번호 → 요금
        int[] busFare = new int[N + 1];

        // 버스 번호 → 정류장 리스트
        List<Integer>[] busRoutes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            int cost = sc.nextInt();
            int stopCount = sc.nextInt();
            busFare[i] = cost;
            busRoutes[i] = new ArrayList<>();
            for (int j = 0; j < stopCount; j++) {
                int stop = sc.nextInt();
                busRoutes[i].add(stop);
            }
        }

        // 정류장 → 탈 수 있는 버스들
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            for (int stop : busRoutes[i]) {
                stopToBuses.putIfAbsent(stop, new ArrayList<>());
                stopToBuses.get(stop).add(i);
            }
        }

        // (정류장, 버스) → 최소 비용, 시간
        Map<String, int[]> dist = new HashMap<>();

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(A, 0, 0, 0)); // 시작은 어떤 버스도 안 탐

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            String key = cur.stop + "-" + cur.bus;

            if (dist.containsKey(key)) {
                int[] prev = dist.get(key);
                if (cur.cost > prev[0] || (cur.cost == prev[0] && cur.time >= prev[1])) continue;
            }

            dist.put(key, new int[]{cur.cost, cur.time});

            // 도착하면 종료
            if (cur.stop == B) {
                System.out.println(cur.cost + " " + cur.time);
                return;
            }

            // ① 같은 버스로 이동
            if (cur.bus != 0) {
                List<Integer> route = busRoutes[cur.bus];
                for (int i = 0; i < route.size() - 1; i++) {
                    if (route.get(i) == cur.stop) {
                        int nextStop = route.get(i + 1);
                        pq.offer(new State(nextStop, cur.bus, cur.cost, cur.time + 1));
                        break;
                    }
                }
            }

            // ② 다른 버스로 환승 (혹은 처음 탑승)
            for (int bus : stopToBuses.getOrDefault(cur.stop, new ArrayList<>())) {
                if (bus == cur.bus) continue; // 같은 버스면 이미 타고 있음
                pq.offer(new State(cur.stop, bus, cur.cost + busFare[bus], cur.time));
            }
        }

        System.out.println("-1 -1"); // 도달 못하는 경우
    }
}