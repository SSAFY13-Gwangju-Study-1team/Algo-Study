/**
 * 메모리 : 14,304kb / 시간 : 104ms
 * 난이도 : 2/10
 * 우선순위 큐를 사용하는 문제..
 * 임을 알고 풀어서 쉬웠지만 만약 몰랐다면,,? 
 * 순위나 많고 적음을 갱신하면서 확인해야할 때 우선순위큐를 사용해보자~~
 */

package algo_study.week09;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1417_국회의원선거_정해빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 표가 많은 순으로 pq를 처리해야하므로 큰 수가 우선순위 높음 
		int dasomVoted = parseInt(br.readLine());
		for(int i=0; i<N-1; i++) {
			pq.add(parseInt(br.readLine()));
		}
		int cnt = 0; // 다솜이가 매수한 사람 수 
		while(!pq.isEmpty() && pq.peek() >= dasomVoted) { // pq가 비어있지 않고(다솜이가 혼자일 경우), 다솜이보다 같거나 더 많은 표를 가진 후보가 있다면 
			int cur = pq.poll(); // 매수하기 
			cnt++; // 매수한 횟수 카운트 
			pq.add(cur-1); // 다시 우선순위 정해주기 위해 pq에 삽입 
			dasomVoted++; // 다솜이가 매수해서 갱신된 표 
		}

		System.out.println(cnt); // 매수한 횟수 출력 
	}
	
}
