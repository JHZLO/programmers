/*
- 1초 간격으로 주식의 가격 변동
- 주식의 가격이 앞으로 떨어지지 않은 횟수 만큼 ++
- 스택 활용 어떻게?
    - 주식 가격이 증가하거나 동일할 경우 스택에다가  index 값을 넣음
        - prices[stack.peek()] 값과 비교
    - 감소하는 값이 등장하면 감소한 시점의 result에 index - stack.peek() 값을 넣음
        - 해당 인덱스(stack.peek()) pop
        - 감소한 index 스택에 push
    - 이렇게 하면 스택에 있는 값들은 끝까지 주식 가격이 떨어지지 않은 인덱스임
        - 맨 끝 results index부터  results[stack.peek()] = prices.length - stack.peek() - 1;
 */
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        		int[] answer = new int[prices.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < prices.length; i++) {
			while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
				answer[stack.peek()] = i - stack.peek();
				stack.pop();
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			answer[stack.peek()] = prices.length - stack.peek() - 1;
			stack.pop();
		}
		return answer;
    }
}