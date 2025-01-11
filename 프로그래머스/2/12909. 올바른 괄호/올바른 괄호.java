import java.util.Stack;
/*
- 스택에 "(" 삽입 -> ")" 만나면 "(" pop
*/

class Solution {
    boolean solution(String s) {
        Stack<String> parentheses = new Stack<>();
        
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                parentheses.push("(");
            }
            if (s.charAt(i) == ')' && parentheses.isEmpty()){
                return false;
            }
            if (s.charAt(i) == ')' && !parentheses.isEmpty()){
                parentheses.pop();
            }
        }
        
        if (parentheses.isEmpty()){
            return true;
        }
        return false;
    }
}