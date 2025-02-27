/*
<주차요금>
기본 시간 180분 - 기본 요금 5000
단위 시간 10분 - 단위 요금 600

<조건>
- 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주
- 00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산
- 기본 시간 이하이면 기본 요금 청구
- 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면 올림

<출력>
차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담음

<문제 풀이>
Map<String, int[]>
Map<차량번호: [IN_TIME, OUT_TIME]> -> 이렇게 저장하고자함 IN과 OUT은 조건문으로 분기

=> 근데 이렇게 하니까 한 번 들어왔다가 다시 또 들어오는 차량 처리를 못하네..

<해결방안>
Map<String, inTime>
Map<String, 주차시간>
나중에 inTime 빼고 .remove(value)  => 한 번 더 조건 거친 후 inTime만 있는 경우 23:59 - inTime
Set<String> TreeSet -> 차량 번호 순서대로 저장
*/
import java.util.*;

class Solution {
    public List<Integer> solution(int[] fees, String[] records) {
        Map<String, Integer> parkRecord = new HashMap<>();
        Map<String, Integer> inTimeRecord = new HashMap<>();
        Set<String> carNums = new TreeSet<>();
        
        // 기본값들 불러오기
        int basic_time = fees[0];
        int basic_fee = fees[1];
        int unit_time = fees[2];
        int unit_fee = fees[3];
        
        // records에서 값 불러와서 parkRecord에 넣기
        for (String record: records){
            String[] cars = record.split(" ");
            int time = convertToMinutes(cars[0]);
            String carNum = cars[1];
            String type = cars[2];
            
            carNums.add(carNum);
    
            if (type.equals("IN")){
                inTimeRecord.put(carNum, time);
            } else{
                int inTime = inTimeRecord.get(carNum);
                int timeDiff = time - inTime;
                parkRecord.put(carNum, parkRecord.getOrDefault(carNum, 0) + timeDiff); // 주차 시간 합산
                inTimeRecord.remove(carNum);
            }
        }
        
        for (String carNum : inTimeRecord.keySet()) {
            int inTime = inTimeRecord.get(carNum);
            int timeDiff = convertToMinutes("23:59") - inTime;
            parkRecord.put(carNum, parkRecord.getOrDefault(carNum, 0) + timeDiff);
        }
        
        List<Integer> parkFee = new ArrayList<>();
        for (String num : carNums){
            int parkTime = parkRecord.getOrDefault(num, 0);
            int fee = calculateFee(parkTime, basic_time, basic_fee, unit_time, unit_fee);
            parkFee.add(fee);
        }
  
        return parkFee;
    }
    
     private int convertToMinutes(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
    
    private int calculateFee(int parkTime, int basic_time, int basic_fee, int unit_time, int unit_fee) {
        if (parkTime <= basic_time) {
            return basic_fee;
        }
        int exceedTime = parkTime - basic_time;
        return basic_fee + (int) Math.ceil((double) exceedTime / unit_time) * unit_fee;
    }
}