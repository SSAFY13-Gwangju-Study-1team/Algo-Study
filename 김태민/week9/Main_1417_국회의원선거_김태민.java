package Week9;
import java.io.*;
import java.util.*;

/**
 * 조건
 *  - 기호 1번이 나머지 후보들보다 숫자가 커야함
 *  - 우선순위 큐를 활용하여 가장 높은숫자들에서 1씩 뻄
 * 설계
 *  - 우선순위 큐 사용
 *  - 일반적으로는 최소 힙을 사용하므로 최솟값이 우선순위를 가지게 됨
 *  - 그래서 최대힙으로 바꿈
 *
 *
 */

public class Main_1417_국회의원선거_김태민 {

    public static void main(String[] args) throws Exception{
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        for(int i = 0; i<n-1; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }
        int cnt = 0;
        while (!pq.isEmpty()){
            int x = pq.poll();
            if(x<target){
                break;
            } else {
                x--;
                cnt++;
                target++;
                pq.add(x);
            }
        }
        System.out.println(cnt);

    }
}
