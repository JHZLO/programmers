import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        int prefix_index = 0;
        Arrays.sort(phone_book);
        
        for (int i = 0; i < phone_book.length - 1; i++){
            if(phone_book[i+1].startsWith(phone_book[i])){
                return false;
            }
        }
        
        return true;
    }
}
