import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < str.length(); i++){
            char x = str.charAt(i);
            if (x == '('){
                stack.push(x);
            }else if(x == ')' && !stack.isEmpty()){
                stack.pop();
            }
        }

        if (stack.isEmpty()){
            System.out.print("Yes");
        }else{
            System.out.print("No");
        }
    }
}