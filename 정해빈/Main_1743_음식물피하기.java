package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main_1743_음식물피하기 {
	
	static int N, M, K;
	
	static int[][] graph;
	static boolean[][] visited;
	
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	
	public static boolean isIn(int x, int y) { // 그래프범위 안에 있는지 확인하는 메서드 
		return x<N && x>=0 && y<M && y>=0;
	}
	
	public static int bfs() { // 최대 넓이 반환 
		Queue<Node> q = new ArrayDeque<>();
		int max = 0; // 최대크기를 저장하기 위한 변수 
		
		for(int i=0; i < N; i++) { // 전체 그래프(좌표)  탐색 
			for (int j=0; j<M; j++) {
				
				if(graph[i][j]==1 && !visited[i][j]) { // 음식물이 있고, 아직 방문하지 않은 좌표라면 
					q.offer(new Node(i,j)); // 큐에 저장 
					visited[i][j]=true; // 방문 표시 
					
					int cnt = 0;
					while(!q.isEmpty()) { // 큐가 빌 떄까지 
						Node cur = q.poll(); // 큐의 첫번째 요소 꺼내와서 확인 
						cnt++; // 큐에서 꺼낼때마다 음식물의 크기 +1
						int x = cur.x;
						int y = cur.y;
						
						for(int dir=0; dir<4; dir++) { // 4방 탐색 
							int nx = x + dx[dir];
							int ny = y + dy[dir];
							
							if(isIn(nx,ny) && !visited[nx][ny] && graph[nx][ny]==1) { // 범위 안에 있고, 방문하지 않았고, 음식물이라면 
								visited[nx][ny] = true; // 방문표시 
								q.offer(new Node(nx,ny)); // 새로 방문할 좌표 큐에 삽입 
							}
						}
					}
					if(max<cnt) max = cnt; // 큐에 더아싱 탐색할 좌표가 없다면, cnt가 더 크면 max 갱신 
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x-1][y-1] = 1;
		}
		 
		System.out.println(bfs());
		
	}

	
	
}
