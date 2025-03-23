package Week9;

import java.io.*;
import java.util.*;

/**
 * 조건
 *  - 아이들을 만났을 때 줄 선물이 없다면 -1을 저장
 *  - 가장 큰 선물부터 줌
 *
 *  설계
 *   - 우선순위 큐 사용
 *
 * 메모리: 34412kb
 * 실행시간: 340ms
 *
 */

public class Main_14235_크리스마스선물_김태민 {
    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n>0){  // 선물을 충전할거임
                for(int i = 0; i<n; i++){
                    int k = Integer.parseInt(st.nextToken());
                    pq.add(k);
                }
            } else if(n==0){
                if(pq.isEmpty()){
                    sb.append(-1).append("\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
