class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int seq = 0;
        int tube_seq = p;
        
        for (int i = 0; i <= t * m; i ++){ // 전체
            String x = Integer.toString(i, n).toUpperCase();
            
            for (int j = 0; j < x.length(); j ++){
                seq ++;
                if (seq == tube_seq){ // 튜브의 순서
                    answer += x.charAt(j);
                    if (answer.length() == t){
                        break;
                    }
                    tube_seq += m;
                }
            }
        }
        return answer;
    }
}