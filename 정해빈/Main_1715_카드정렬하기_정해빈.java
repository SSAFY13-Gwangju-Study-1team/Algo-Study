/**
 * Integer범위 항상 조심 또 조심!!!
 */
package algo_study.week09;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1715_카드정렬하기_정해빈 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		long sum = 0;
		for(int i=0; i<n; i++) {
			pq.add(parseInt(br.readLine()));
		}
		
		while(pq.size()>1) {
			int first = pq.poll();
			int second = pq.poll();
			int preSum = first + second;
			sum+=preSum;
			pq.add(preSum);
		}
		
		System.out.println(sum);
	}

}
