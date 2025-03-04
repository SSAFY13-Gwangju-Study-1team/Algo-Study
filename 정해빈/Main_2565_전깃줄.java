/**
 * 메모리 : 14192kb / 시간 : 104ms
 * 난이도 : 7/10
 * LIS 알고리즘에 대해서 알고 있다면 쉽게 풀 수 있는 문제라 생각
 * 하지만 나는 다 까먹어서 다시 공부함
 * 일단 A전깃줄의 번호 순 대로 정렬해야함
 * 
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2565_전깃줄 {
	
	// a-b 연결된 전깃줄을 더 편하게 비교하기 위해 클래스 생성 
	static class Wire implements Comparable<Wire>{
		int a;
		int b;
		public Wire(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Wire o) {
			return this.a - o.a; // a 전봇대 기준으로 정렬하기 위함 
		}
		
	}
	static int N, max;
	static Wire[] wires;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력 및 초기화 
		N = parseInt(br.readLine());
		
		wires = new Wire[N];
		dp = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			wires[i] = new Wire(parseInt(st.nextToken()), parseInt(st.nextToken()));
		}
		
		Arrays.sort(wires); // a전봇대 기준으로 정렬 
		
		bottomUp();
//		topDown();
		
		
	}

	// 재귀를 사용해 LIS길이 구하기 - dp[i]를 사용해 i번째 전깃줄 마지막으로 하는 LIS길이 저장/중복계산 피함 
	public static int LIS(int idx) {
		if(dp[idx] != -1) return dp[idx]; // 이미 계산한 경우, 저장된 값 반환 
		
		dp[idx] = 1; // 최소 자기 자신 포함 (길이 1) 
		for(int i=0; i<idx; i++) {
			if(wires[i].b < wires[idx].b) { // B전봇대 기준으로 증가하는 경우 
				dp[idx] = Math.max(dp[idx], LIS(i) + 1); // dp에 최댓값 갱신 
			}
		}
		return dp[idx];
	}
	
	public static void topDown() {
		max= 0;
		for(int i=0; i<N; i++) {
			dp[i] = -1; // 방문 여부 체크를 위해 -1로 초기화 
			max = Math.max(max, LIS(i)); // LIS 최댓값 찾기 
		}
		System.out.println(N-max); // 제거해야하는 전깃줄 수 
	}
	
	public static void bottomUp() {
		max = 1;
		for(int i=0; i<N; i++) {
			dp[i] = 1; // 최소 자기 자신을 포함하므로 1로 초기화 
			for(int j=0; j<i; j++) { // B전봇대 기준으로 증가하는 부분 수열 찾기 
				if(wires[i].b > wires[j].b) { // B전봇대값이 현재 i보다 작은 경우에만 연결 가능 
					dp[i] = Math.max(dp[i],  dp[j]+1); // dp[i] 갱신 
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N-max); // 전체 갯수 - LIS길이 
	}
	

}
