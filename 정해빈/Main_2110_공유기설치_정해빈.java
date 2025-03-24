/**
 * 메모리 : 29,100kb / 시간 : 272ms
 * 난이도 : 4/10
 * 이분탐색
 * 공유기의 간격을 이분탐색의 대상으로 정하면 쉽게 구할 수 있다! 
 */

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_공유기설치_정해빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		
		long left = 1; 
		long right = 0; 
		int[] routers = new int[N];
		for(int i=0; i<N; i++) {
			routers[i] = parseInt(br.readLine());
		}
		Arrays.sort(routers);
		right = routers[N-1] - routers[0]; // 최대로 설정할 수 있는 간격 (처음집~마지막집)
		
		// 이분 탐색 => 가장 인접한 최대 공유기 간격 
		while(left <= right) {
			long mid = (left+right)/2;
			int count = 1;
			int installed = routers[0]; // 처음 집에는 무조건 설치 
			for(int i=0; i<N; i++) { // 현재 간격이 실제 간격을 만족하는지 확인 
				if(routers[i]-mid >= installed) { // 만족하면 
					count++; // 공유기 설치 
					installed = routers[i]; // 설치한 위치 다음 간격과 계산하기 위해 저장 
				}
			}
			if(count>=M) { // 만약 조건을 만족하면 
				left = mid+1; // 최대 간격 있는지 찾아보기 
			}
			else right = mid-1; // 그렇지 않으면 간격 줄여주기 
		}
		System.out.println(right); // 최대 간격 출력 

	}

}
