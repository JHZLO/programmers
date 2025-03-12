import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        int[][] map = new int[arr1.length][arr1.length];
        
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], 0);
        }
        
        fillMap(arr1, map);
        fillMap(arr2, map);
        
        for (int i = 0; i < map.length; i++){
            String value = "";
            for (int j = 0; j < map[i].length; j++){
                if (map[i][j] == 1){
                    value += "#";
                }else {
                    value += " ";
                }
            }
            System.out.println();
            answer[i] = value;
        }
        
        return answer;
    }
    private void fillMap(int[] arr, int[][] map){
        for (int i = 0; i < arr.length; i++){
            String binary = Integer.toString(arr[i], 2);
            while(binary.length() != arr.length){
                binary = "0" + binary;
            }
            //System.out.println(binary);
            for (int j = 0; j < binary.length(); j++){
                int value = Integer.parseInt(binary.substring(j, j+1));
                if (value == 1){
                    map[i][j] = 1;
                }
            }
        }
    }
}