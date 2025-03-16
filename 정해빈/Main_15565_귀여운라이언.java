package algo_study.week05;

import java.util.*;
import java.io.*;

public class Main_15565_귀여운라이언 {
	
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] dolls = new int[N];
		int[] dollType = new int[3]; // 1에는 라이언 인형갯수, 2에는 어피치 인형갯수 
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			dolls[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		
		//초기 K개 인형 확인하기 
		for(int i=0; i<K; i++) {
			dollType[dolls[i]]++;
		}
		
		if(dollType[1]==K) {
			System.out.println(K); // 초기K개 인형이 전부 라이언이라면 K 출력 후 탐색 종료 
			return;
		}
		
		// 투포인터, 슬라이딩 윈도우 방법으로 O(N) 시간복잡도 
		
		int ptr1 = K; // idx K부터 탐색시작 
		int ptr2 = 0;
		while(ptr1<N) {
			dollType[dolls[ptr1++]]++; // 포인터 이동시켜서 넣어주기..
			while(dollType[1]>=K) { // 라이언 인형 갯수가 K개 이상이라면 
				if(dollType[1]==K) { // 라이언 인형의 갯수가 K개 일 때 
					min = Math.min(min, ptr1-ptr2); // len 갱신 
				}
				dollType[dolls[ptr2++]]--; // 포인터2 이동시켜서 가장 짧은 길이 찾기 
				
				
			}
		}
		if(min==Integer.MAX_VALUE) System.out.println(-1); // min값이 여전히 갱신되지 않았다면 존재하지 않으므로 -1 출력 
		else System.out.println(min); // min값 출력 
	}
	
}
