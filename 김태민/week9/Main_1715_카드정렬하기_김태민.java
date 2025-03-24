package Week9;
import java.util.*;
import java.io.*;


/**
 * 메모리: 25268kb
 * 실행시간: 320ms
 *  
 *  우선순위 큐 사용
 * 
 */

public class Main_1715_카드정렬하기_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
        int result = 0;
        while (pq.size()!=1){
            int x = pq.poll();
            sum = x+pq.poll();
            result+=sum;
            pq.add(sum);
        }
        System.out.println(result);
    }
}
