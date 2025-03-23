package algo_study.week09;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_14235_크리스마스선물_정해빈 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = parseInt(st.nextToken());
			if(a==0) {
				if(pq.isEmpty()) sb.append("-1").append("\n");
				else sb.append(pq.poll()).append("\n");
			}
			else {
				for(int j=0; j<a; j++) {
					pq.add(parseInt(st.nextToken()));
				}
			}
		}
		System.out.print(sb);
	}

}
