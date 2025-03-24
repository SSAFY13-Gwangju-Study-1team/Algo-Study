package algo_study.week10;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_16928_뱀과사다리게임_정해빈 {
	static int[] map = new int[101];
	static int[] dist = new int[101];
	static int[] ladderOrSnake = new int[101];
	
	public static void main(String[] args) throws Exception {
		int N, M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		for(int i=0; i<N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			ladderOrSnake[a] = b;
		}
		
		// 최소 이동횟수와 같은 로직
		// BFS 수행 
		Queue<Integer> q = new ArrayDeque<>();
		Arrays.fill(dist, -1);
		q.offer(1); // 1번칸부터 출발 
		dist[1] = 0; 
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=1; i<=6; i++) { // 주사위 굴리기 
				int next = cur + i; // 다음 칸 설정 
				if(next>100) continue; // 만약 100을 넘으면 패스 
				if(ladderOrSnake[next]!=0) next = ladderOrSnake[next]; // 사다리나 뱀을 만나면 해당 칸으로 이동 
				if(dist[next] == -1) { // 방문하지 않은 칸이라면 
					dist[next] = dist[cur]+1; // 현재 주사위 굴린 횟수 + 1
					q.offer(next); // 다음 칸으로 이동했으므로 큐에 넣기 
				}
			}
		}
		System.out.println(dist[100]); // 100번째 칸일 때 dist 구하기
		
	}
}
