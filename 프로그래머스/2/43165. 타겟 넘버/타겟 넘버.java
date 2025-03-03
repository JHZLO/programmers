class Solution {
    private int cnt = 0; // 정답 개수 카운트

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0); // 시작: 인덱스=0, 합=0
        return cnt;
    }

    private void dfs(int[] numbers, int target, int index, int sum) {
        if (index == numbers.length) { // 모든 숫자를 사용한 경우
            if (sum == target) { // 목표값과 일치하면 카운트 증가
                cnt++;
            }
            return;
        }

        // 현재 숫자를 더한 경우
        dfs(numbers, target, index + 1, sum + numbers[index]);

        // 현재 숫자를 뺀 경우
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}
