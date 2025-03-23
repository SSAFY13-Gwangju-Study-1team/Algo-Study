package Week9;

import java.util.*;
import java.io.*;

/**
 * 조건
 *  - n의 크기 1,500개 n^2->2,250,000
 *  - n번쨰 큰수 구하기
 *
 * 설계
 *  - 우선순위 큐를 사용해서 넣은다음 구하기
 *
 *  메모리: 273824kb
 *  실행시간: 772ms
 *
 */

public class Main_2075_N번쨰큰수_김태민 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }
        int result = 0;
        for(int i = 0; i<n; i++){
            result = pq.poll();
        }
        System.out.println(result);
    }
}
