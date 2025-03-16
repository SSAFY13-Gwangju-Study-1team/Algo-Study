package algo_study.week05;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_21921_블로그 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = parseInt(st.nextToken());
		int X = parseInt(st.nextToken());
		int[] visitor = new int[N+1];
		st = new StringTokenizer(br.readLine());
		int max = -1;
		for(int i=1; i<=N; i++) {
			visitor[i] = parseInt(st.nextToken());
			max = Math.max(visitor[i], max); // max가 0면 sad출력하기 위해 
		}
		if(max==0) { // 최대 방문자수 0이면 sad출력 후 종료 
			System.out.println("SAD");
			return; 
		}

		// 슬라이딩 윈도우 이용
		// 1 ~ X까지 최대 방문자 수 체크 
		int start = 1;
		int end = X;
		int sum = 0; // 현재 구간의 방문자수 합 체크 
		int cnt = 0; // maxSum값 같으면 cnt 
		int firstSum = 0;
		for(int i = start; i<=end; i++) {
			sum+= visitor[i];
			firstSum = sum;
			
		}
		int maxSum = sum;
		while(end<N) {
			sum= sum + visitor[++end] - visitor[start++];
			if(sum > maxSum) {
				cnt = 1;
				maxSum = sum;
			}
			else if(sum==maxSum) {
				cnt++;
			}
		}
		if(firstSum==maxSum) cnt++; // edge-case : 5 2 / 1 2 1 2 1
		System.out.println(maxSum);
		System.out.println(cnt);
		
	}

}
