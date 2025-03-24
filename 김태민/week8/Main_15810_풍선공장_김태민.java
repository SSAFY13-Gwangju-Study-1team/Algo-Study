package Week8;

import java.io.*;
import java.util.*;

/**
 * 조건: 각 스태프가 풍선하나를 만드는 데 Ai분이 걸림
 *         풍선 M개를 만드는데 최소 몇분이 걸릴까?
 *  - 구하고자 하는것: 풍선이 다 만들어지는 최소 시간
 *
 *  메모리: 92236kb
 *  실행시간: 500ms
 *
 *
 *
 */

public class Main_15810_풍선공장_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] map = new int[n];
        st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min,map[i]);

        }

        long right = (long)min*m;
        long left  = min;
        long result  = (long)min*m;

        while(left<=right){
            long mid = (right+left)/2;
            long count = 0;

            for(long i: map){
                count+=(long)mid/i;
            }
            if(count>=m){
                right = mid-1;
                result = Math.min(mid,result);
            } else {
                left = mid+1;
            }

        }
        System.out.println(result);


    }
}
