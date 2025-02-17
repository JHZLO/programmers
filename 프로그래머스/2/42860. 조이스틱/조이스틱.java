class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int move = length - 1;
        int y;
        
        for (int x = 0; x < length; x++){
            answer += Math.min(name.charAt(x) - 'A', 'Z' - name.charAt(x) + 1);
            
            y = x + 1; // 현 위치
            while (y < length && name.charAt(y) == 'A'){
                y ++; // A가 나오는 index
            }
            
            move = Math.min(move, (Math.min(x+x+(length-y), x+(length-y)+(length-y)))); 
        }
        
        
        return answer + move;
    }
}