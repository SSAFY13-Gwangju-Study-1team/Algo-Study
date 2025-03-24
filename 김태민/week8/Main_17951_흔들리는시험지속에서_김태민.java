package Week8;

import java.io.*;
import java.util.*;

/**
 * 조건: 주어진 배열을 k그룹으로 나눈뒤, 그 그룹의 합중 최솟값을 출력
 *
 *  설계
 *   - 단순히 생각하면 주어진 배열을 k그룹으로 나누어서 각각의 그룹의 합을 구함
 *     그중에서 최솟값중에 최대값을 구하면 됨
 *   - 이분탐색의 기준: 처음 right는 시험점수의 총합에서 k를 나눈게 될듯?
 *
 *
 *
 */

public class Main_17951_흔들리는시험지속에서_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] map = new long[n];
        long sum = 0;
        long max = 0;
        for(int i = 0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
            sum+=map[i];
            max = Math.max(max, map[i]);
        }
        long right = sum/m;
        long left = 0;
        long realSum = Integer.MIN_VALUE; // 이게 진짜 최종값
        while(left<=right){
            ArrayList<Long> list = new ArrayList<>();
            long mid = (right+left)/2;
            int count = 0;  // 시험지 갯수를 세는 변수
            long countSum = 0;

            for(long i: map){
                if(countSum+i>=mid){
                    count++;
                    countSum+=i;
                    list.add(countSum);
                    countSum = 0;


                } else {
                    countSum += i;
                }

            }


            if(count>=m){
                realSum = Math.max(realSum,Collections.min(list));
                left = mid+1;
            } else {
                right = mid-1;
            }

        }
        System.out.println(realSum);


    }
}






