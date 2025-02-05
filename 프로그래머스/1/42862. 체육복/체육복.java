/*
Greedy

<문제>
- 학생들의 번호는 체격 순
- 이웃하는 번호의 학생에만 체육복을 빌려줄 수 있다.
    - 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있다.
    - 만약 여벌 체육복을 가진 학생이 도난을 당하면 빌려줄 수 없다.
- 최대한 많은 학생이 체육수업을 들어야 한다.

<입력>
- n : 전체 학생 수
- lost[] : 체육복을 도난 당한 학생들의 번호가 담긴 배열 
    - 1명 ~ n명 이하 (중복 x)
- reserve[] : 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열
    - 1명 ~ n명 이하 (중복 x)

<출력>
- return : 체육수업을 들을 수 있는 학생의 최댓값
*/
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        int student[] = new int[n];
        Arrays.fill(student, 1);
        
        for (int i : lost){
            student[i - 1] --;
        }
        
        for (int j : reserve){
            student[j - 1] ++;
        }
        
        for (int i = 0; i < student.length; i++){
            if (student[i] == 0){
                if (i > 0 && student[i - 1] == 2){
                    student[i]++;
                    student[i - 1]--;
                }else if (i + 1 < n && student[i + 1] == 2){
                    student[i]++;
                    student[i + 1]--;
                }else{
                    answer--;
                }
            }
        }
        
        return answer;
    }
}