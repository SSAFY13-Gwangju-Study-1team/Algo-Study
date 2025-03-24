package algo_study;

import java.io.*;
import java.util.*;


public class Main_2589_보물섬 {
	
	static class Node { // x, y좌표와 거리를 저장하는 노드 클래스 선언 
		int x;
		int y;
		int cost;
		
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	
	static int N, M, result;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	static char[][] graph; // 보물지도 저장 
	static boolean[][] visited; // 좌표 방문여부 저장 
	static Queue<Node> q; // bfs를 위한 큐 선언 
	
	public static boolean isIn(int x, int y) { // 범위 안에 있는지 확인하는 메서드 
		return x < N && x >= 0 && y < M && y >= 0;
	}
	
	public static void bfs(Node node) { // 시작노드
		q = new ArrayDeque<>(); // 큐 초기화 
		q.add(node); // 시작 노드 큐에 삽입 
		visited[node.x][node.y] = true; // 방문 표시 
		
		while(!q.isEmpty()){ // 큐가 빌때까지 bfs 탐색 
			Node cur = q.poll(); // 탐색할 노드 = 큐의 첫번째 원소
			int x = cur.x;
			int y = cur.y;
			int dist = cur.cost;
			
			for(int dir=0; dir<4; dir++) { // 4방 탐색 
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(isIn(nx,ny) && graph[nx][ny]=='L' && !visited[nx][ny]) { // 다음 좌표가 범위안에 있고, 육지이고, 방문하지 않았다면 
					visited[nx][ny] = true; // 방문 표시 
					q.offer(new Node(nx, ny, dist+1)); // 큐에 bfs할 좌표 추가 (거리는 +1해서)
					result = Math.max(result,  dist+1); // 최단거리가 가장 큰 거리를 result에 저장 후 비교하면서 갱신 
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new char[N][M];
		
		result = 0;
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				graph[i][j] = str.charAt(j);
			}
		}
		
		/**
		 * 육지인지 확인하는 bfs 
		 */
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j] == 'L') { // 육지 확인 
					visited = new boolean[N][M]; // 방문 정보 초기화 
					bfs(new Node(i,j,0)); // 순회하면서 노드 추가 
					
				}
				
			}
		}
		System.out.println(result);
	
	}

}
