import java.util.*;

class Solution {
    private List<int[]> moves = new ArrayList<>();

    public int[][] solution(int n) {
        hanoi(n, 1, 3, 2); // 1번 기둥에서 3번 기둥으로 이동 (2번은 보조)
        return moves.toArray(new int[moves.size()][2]);
    }

    private void hanoi(int n, int from, int to, int aux) {
        if (n == 1) {
            moves.add(new int[]{from, to}); // 가장 작은 원판 이동
            return;
        }

        // Step 1: n-1개 원판을 보조 기둥(aux)으로 이동
        hanoi(n - 1, from, aux, to);

        // Step 2: 가장 큰 원판을 목표 기둥(to)로 이동
        moves.add(new int[]{from, to});

        // Step 3: 보조 기둥(aux)에 있던 n-1개 원판을 목표 기둥(to)으로 이동
        hanoi(n - 1, aux, to, from);
    }
}
