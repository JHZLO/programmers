/*
- 주어진 숫자들을 적절히 더하거나 빼서 타겟 넘버를 만들 수 있는 경우의 수
    - 타겟 넘버는 자연수
    - +가 하나일 때 부터 numbers.length 만큼 계산해보기
        - 만약 계산한 값이 음수이면 continue
*/

class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    private int dfs(int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length) {
            if (sum == target){
                return 1;
            }
            return 0;
        }

        int add = dfs(numbers, target, depth + 1, sum + numbers[depth]);
        int subtract = dfs(numbers, target, depth + 1, sum - numbers[depth]);

        return add + subtract;
    }
}