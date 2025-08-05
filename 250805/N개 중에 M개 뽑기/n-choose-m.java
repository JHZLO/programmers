import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++){
            arr[i] = i+1;
        }

        backtracking(new ArrayList<>(), n, m, 1);
    }
    public static void backtracking(List<Integer> list, int n, int m, int start){
        if (list.size() == m) {
            for (int x : list) {
                System.out.print(x + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= n; i++){
            list.add(i);
            backtracking(list, n, m, i + 1);
            list.remove(list.size() - 1);
        }        
    }
}