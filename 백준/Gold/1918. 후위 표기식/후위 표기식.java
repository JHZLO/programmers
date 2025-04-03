import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
/*
차량기지 알고리즘
- 문자열은 바로 출력
- 여는 괄호 나오면 스택에 추가
- 닫는 괄호 나오면 스택에 있는거 전부 출력
- 연산자를 만나면 우선순위에 따라서 차량기지 알고리즘을 적용함
    - 넣으려는 연산자보다 우선순위가 높거나 같은 연산자가 이미 있다면 없을때 까지 모두 pop해서 출력함
        - 이때 스택에 괄호도 있을텐데 괄호는 우선순위 0으로 설정
    - 모두 출력 후 넣으려는 연산자 push

*/


// 1918 후위표기식
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 문자형이면 바로 추가
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            } else if (c == '(') { // 여는 괄호 나오면
                stack.push(c);
            } else if (c == ')') { // 닫는 괄호 나오면
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    }
                    sb.append(stack.pop());
                }
            } else { // 연산자형 나오면
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                    sb.append(stack.pop());
                }
                stack.add(c);
            }
        }

        //남은 스텍처리
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int priority(char c) {
        if (c == '(' || c == ')') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 2;
        }
    }
}
