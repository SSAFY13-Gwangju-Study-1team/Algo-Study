/**
 * 메모리 : 15,608kb / 시간 : 164ms
 * 플로이드-워셜 알고리즘 (역사랑 비슷하게 풀었다)
 * 무겁고 가벼움을 플로이드워셜을 통해 정리 하고 
 * 자신보다 무거운 애들이 전체 수의 과반수가 되면 작은애 중 한명
 * 자신보다 가벼운 애들이 전체 수의 과반수가 되면 무거운 애 중 한명 
 * 이 경우를 더해서 출력하면 됨!!
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2617_구슬찾기_정해빈 {
	static int N, M;
	static int[][] weigh;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		weigh = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			weigh[a][b] = 1; // a > b 무거우면 1
			weigh[b][a] = 2; // b < a 가벼우면 2
		}
		
		// 플로이드 워셜 알고리즘 
		// 모든 정점에 대하여 무게 관계 정의하기 
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(weigh[i][k]==0) continue; // 관계 없으면 패스 
				for(int j=1; j<=N; j++) {
					if(weigh[j][k]==0) continue;
					// i<k, k<j이면 i<j
					// i>k, k>j이면 i>j
					if(weigh[i][k]==weigh[k][j]) weigh[i][j] = weigh[i][k];
				}
			}
		}
	
		int result = 0;
		for(int i=1; i<=N; i++) {
			int lighter=0, heavier=0; // 자신보다 가볍거나 무거운 애들 수 
			for(int j=1; j<=N; j++) {
				if(weigh[i][j]==1) { // 내가 더 무거우면 
					heavier++; // 무거운 애 추가 
				}
				if(weigh[i][j]==2) { // 내가 더 가벼우면 
					lighter++; // 가벼운 애 추가 
				}
			}
			
			// 내가 무거움쪽에 속하거나 가벼움쪽에 속하면 중간이 될 수 없으므로 카운팅 
			if(heavier>N/2) result++;
			else if(lighter>N/2) result++;
		}
		System.out.println(result);
	}

}
