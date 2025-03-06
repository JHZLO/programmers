class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int cur = timeToSecond(pos);
        int startOp = timeToSecond(op_start);
        int endOp = timeToSecond(op_end);
        int videoEnd = timeToSecond(video_len);
        
        if (cur >= startOp && cur <= endOp) cur = endOp;
        
        for (String command: commands){
            cur += executeCommand(command);
            if (cur < 0) cur = 0;
            if (cur > videoEnd) cur = videoEnd;
            if (cur >= startOp && cur <= endOp) cur = endOp;
        }

        return intToString(cur);
    }
    
    private int executeCommand(String command){
        if (command.equals("next")){
            return 10;
        }else{
            return -10;
        }
    }
    
    private int timeToSecond (String time){
        String[] splitTime = time.split(":");
        int minute = Integer.parseInt(splitTime[0]);
        int second = Integer.parseInt(splitTime[1]);
        
        return minute * 60 + second;
    }
    
    private String intToString (int time){
        String minute = String.valueOf(time / 60);
        if (minute.length() == 1){
            minute = "0" + minute;
        }
        String second = String.valueOf(time % 60);
        if (second.length() == 1){
            second = "0" + second;
        }
        
        return minute + ":" + second;
    }
}