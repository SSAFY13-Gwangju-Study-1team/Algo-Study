/**
 * 메모리 : 92252kb / 시간 : 608ms
 */
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15810_풍선공장_정해빈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		
		int max = 0; // 가장 느린 직원 
		int[] staff = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			staff[i] = parseInt(st.nextToken());
			max = Math.max(max,staff[i]); // 가장 느린 직원의 시간 찾기 
		}
		// 이분 탐색 
		long left =1; // 최소 시간 
		long right = (long)max*M; // 최악의 경우 (가장 느린 직원이 모든 풍선을 부는 경우) 
		
		while(left<=right) { //left가 right와 만나면 탐색 종료 
			
			long cnt = 0; // 만들 수 있는 풍선의 갯수 
			long mid = (left+right)/2; // 이분 탐색을 위해 기준값 mid 설정 
			
			for(int i=0; i<N; i++) {
				cnt += mid/(long)staff[i]; // mid시간 동안 만들 수 있는 풍선 갯수 찾기 
			} 
			
			if(cnt>=M) { // 만약 만들 수 있는 랜선의 갯수가 조건과 부합하면 
				right = mid-1; // 가능한 최소 시간 찾기 위해 시간 줄이기 
			}
			else left = mid+1; // 기준에 부합하지 않으면 더 긴 시간 선택 
			
		}
		
		// 최소시간 출력 -> right를 mid-1로 줄여가며 탐색하므로 탐색끝나면 left가 최소시간이 됨 
		System.out.println(left);
	}
}
