package algo_study.week08;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_10815_숫자카드_정해빈 {
	static StringBuilder sb;
	static int N, M, cards[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = parseInt(br.readLine());
		cards = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cards[i] = parseInt(st.nextToken());
		}
		
		Arrays.sort(cards); // 오름차순 정렬 -> 이분 탐색 위해서 
		
		M = parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			if(binarySearch(parseInt(st.nextToken()))) sb.append(1).append(" ");
			else sb.append(0).append(" ");
		}
		
		System.out.println(sb);

	}
	
	public static boolean binarySearch(int num) {
		int low = 0;
		int high = N-1;
		int mid;
		while(low <= high) {
			mid = (low + high) /2;
			if(num == cards[mid]) {
				return true;
			}
			else if (num > cards[mid]) {
				low = mid+1;
			}
			else high = mid-1;
		}
		return false;
		
	}

}
