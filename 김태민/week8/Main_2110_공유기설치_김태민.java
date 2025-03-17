package Week8;

import java.io.*;
import java.util.*;

/**
 * 조건
 *  - c개의 공유기를 n개의 집에 설치해서, 가장 입접한 두 공유기 사이의 거리가 최대로'
 *  - 결국은 공유기 사이의 거리의 최솟값중 최대값!!!!!!!
 *
 *
 *
 */


public class Main_2110_공유기설치_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] map = new long[n];
        long maxNum = 0;
        for(int i = 0; i<n; i++){
            map[i] = Long.parseLong(br.readLine());
            maxNum = Math.max(maxNum, map[i]);
        }
        long right = maxNum;

        long left = 1;
        Arrays.sort(map);
        long result = Integer.MIN_VALUE;

        while(left<=right){
            int count = 1;

            long mid = (right+left)/2;
            // mid는 최소길이인데 집들에 설치하면서
            // 집을 계속 바꿔줘야함
            long lasthose = map[0];
            for(int i = 1; i<map.length; i++){
                long now = map[i];
                if(now-lasthose>=mid){
                    count++;
                    lasthose = now;
                }
            }
            if(count>=m){
                result = Math.max(result,mid);
                left = mid+1;
            } else {
                right= mid-1;
            }

        }
        System.out.println(result);
    }
}
