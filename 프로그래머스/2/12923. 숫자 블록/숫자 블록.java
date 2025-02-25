class Solution {
    public int[] solution(long begin, long end) {
        int size = (int)(end - begin + 1);
        int[] arr = new int[size];
        
        for (int i = 0; i < size; i++){
            long num = begin + i;
            
            int max_divisor = 1;
            for (long div = 2; div <= Math.sqrt(num); div ++){
                if (num % div == 0){
                    if (num / div > 10000000){
                        arr[i] = (int) div;
                        continue;
                    } else{
                        arr[i] = (int) (num / div);
                    }
                    break;
                }
            }
            if (arr[i] == 0){
                arr[i] = 1;
            }
        }
        if (begin == 1)
            arr[0] = 0;
        
        return arr;
    }
}