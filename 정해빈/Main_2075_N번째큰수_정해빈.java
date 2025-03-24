package algo_study.week09;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_2075_N번째큰수_정해빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pq.add(parseInt(st.nextToken()));
			}
		}
		
		while(N-- >1) {
			pq.poll();
		}
		System.out.println(pq.peek());
	}

}
