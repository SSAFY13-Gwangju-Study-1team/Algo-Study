/**
 * Integer범위 항상 조심 또 조심!!!
 */
package algo_study.week09;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_15903_카드합체놀이_정해빈 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = parseInt(st.nextToken());
		int m = parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		long sum = 0;
		for(int i=0; i<n; i++) {
			int num = parseInt(st.nextToken());
			sum+= (long)num;
			pq.add((long)num);
		}
		for(int i=0; i<m; i++) {
			long a = pq.poll();
			long b = pq.poll();
			pq.add(a+b);
			pq.add(a+b);
			sum+=a+b;
		}
		
		System.out.println(sum);
	}

}
