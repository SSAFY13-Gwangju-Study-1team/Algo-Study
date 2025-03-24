import java.io.*;
import java.util.*;

/**
 * 
 *  - 정렬후 양끝 값끼리 합하기
 *   - 홀수이면 마지막꺼뺴기
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] map = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			map[i] = Long.parseLong(st.nextToken());
		}
		
		long min = Long.MIN_VALUE;
		Arrays.sort(map);
		
		 if (n == 1) {
	            System.out.println(map[0]);
	            return;
	        }
		
		// 짝수일 경우
		if(n%2==0) {
			for(int i = 0; i<n/2; i++) {
				min = Math.max(min, map[i]+map[n-1-i]);
			}
		} else {  // 홀수일 경우
			for(int i = 0; i<(n-1)/2; i++) {
				min = Math.max(min, map[i]+map[n-2-i]);
			}
			min = Math.max(min, map[n-1]);
			
		}
		System.out.println(min);
		
	}

}
