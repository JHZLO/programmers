class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int move = length;
        int index = 0;
        
        for (int i = 0; i < length; i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            index = i + 1; // 현 위치
            while (index < length && name.charAt(index) == 'A'){
                index ++; // A가 나오는 index
            }
            
            move = Math.min((length-index) * 2 + i , (Math.min(move, i + (length - index) + i))); 
        }
        
        
        return answer + move;
    }
}