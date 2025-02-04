/*
- 주어진 word가 몇 번째 단어인지 return

<순서 패턴>
A, E, I, O, U
- A -> AA -> ... -> AAAAA -> AAAAE -> ... -> AAAAU -> AAAE -> AAAEA

<문제 풀이>
- 순서를 차례대로 리스트에 넣은 후 최종 word에 대한 값의 인덱스를 return
*/

import java.util.*;

class Solution {
    public int solution(String word) {
        char[] characters = {'A', 'E', 'I', 'O', 'U'};
        
        List<String> dic = new ArrayList<>();
        
        sequentialWords("", dic, characters);
        
        return dic.indexOf(word);
    }
    
    public void sequentialWords(String word, List<String> dic, char[] characters){
        dic.add(word);
        
        if (word.length() == 5){
            return;
        }
        
        for (char c : characters){
            sequentialWords(word + c, dic, characters);
        }
    }
}