package algo_study;

import java.io.*;
import java.util.*;

public class Main_7934_도영이 {

	static int N, result;
	static int[][] food;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		food = new int[N][2]; // 한 음식의 신맛과 쓴맛을 한꺼번에 저장하기 위해 N*2 배열 저장 
		
		
		
		for(int i=0; i<N; i++) { // 입력 받기
			st = new StringTokenizer(br.readLine());
			food[i][0] = Integer.parseInt(st.nextToken()); // 신맛 입력 
			food[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛 입력 
		}
		
		result = Integer.MAX_VALUE; // 가장 작은 차이값을 출력해야하므로 초기값을 가장 큰 값으로 초기화 
		dfs(0, 1, 0, 0); // 현재 아무것도 넣지 않고(depth=0), 쓴맛은 곱하기 때문에 1을 초기값으로 넣어주고 나머지는 0을 대입 
		System.out.println(result);
	}
	
	public static void dfs(int depth, int sour, int bitter, int count) { // 음식을 넣어보고 쓴맛과 신맛 계산 
		if(depth==N) {
			if(count>0) { // 재료를 넣을 경우 result값을 계산 
				result = Math.min(result, Math.abs(sour-bitter)); // 쓴맛과 신맛의 차이를 현재 min값과 비교해 result값 갱신 
			}

			return; // 음식 N번을 다 넣고 비교했다면 재귀 종료 
		}
		
		dfs(depth+1, sour * food[depth][0], bitter + food[depth][1], count + 1); // 현재 재료 넣을 때
		dfs(depth+1, sour, bitter, count); // 현재 재료 넣지 않을 때
	}

}
