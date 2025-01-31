/*
- 문자열의 숫자를 입력받아 각각의 숫자를 가지고 소수 만들기
*/
import java.util.*;

class Solution {
    public int solution(String numbers) {
        Set<Integer> permutations = new HashSet<>();
        
        generatePermutations("", numbers, permutations);
        
        int count = 0;
        for (int number : permutations){
            if (isPrime(number)){
                count ++;
            }
        }
        
        return count;
    }
    
    // 가능한 경우의 수 문자열로 따져보고 정수로 바꾸기
    private void generatePermutations(String prefix, String remaining, Set<Integer> permutations){
        if (!prefix.isEmpty()){
            permutations.add(Integer.parseInt(prefix));
        }
        for (int i = 0; i < remaining.length(); i++){
            generatePermutations(prefix + remaining.charAt(i),
                                 remaining.substring(0,i) + remaining.substring(i+1),
                                 permutations);
        }   
    }
    
    private boolean isPrime(int number){
        if (number < 2) {
            return false;
        }
        if (number == 2){
            return true;
        }
        if (number % 2 == 0){
            return false;
        }
        if (number % 2 != 0){
            for (int i = 3; i <= Math.sqrt(number); i++){
                if (number % i == 0){
                    return false;
                }
            }
        }
        return true;
    }
}