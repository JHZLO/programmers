import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> answer = new Stack<>();
        
        for (int i = 0; i < number.length(); i++){
            char cur = number.charAt(i);
            while(!answer.isEmpty() && cur > answer.peek() && k > 0){
                answer.pop();
                k--;
            }
            answer.push(cur);
        }
        
        StringBuilder st = new StringBuilder();
        while (!answer.isEmpty()) {
            st.append(answer.pop());
        }
        
        if (k > 0) {
            return st.reverse().toString().substring(0, st.toString().length() - k);
        }
        
        return st.reverse().toString();
    }
}