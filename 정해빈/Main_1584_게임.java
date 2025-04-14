/**
 * 메모리 : 30,420kb / 시간 : 468ms
 * BFS + dijkstra 
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1584_게임 {
	static class Node implements Comparable<Node> {
		int x, y, life;

		public Node(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.life, o.life);
		}
		
	}

	static int N, M;
	static int[][] map;
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[501][501];
		N = parseInt(br.readLine());
		StringTokenizer st;
		// 위험한 구역
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = parseInt(st.nextToken());
			int y1 = parseInt(st.nextToken());
			int x2 = parseInt(st.nextToken());
			int y2 = parseInt(st.nextToken());

			// 작은값~큰값으로 범위 설정 위해 x1 < x2로 swap 
			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			// 위험한 구역은 1로 설정 
			for (int x = x1; x <= x2; x++) {
				for (int y = y1; y <= y2; y++) {
					map[x][y] = 1;
				}
			}
		}
		
		M = parseInt(br.readLine());
		// 죽음의 구역
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = parseInt(st.nextToken());
			int y1 = parseInt(st.nextToken());
			int x2 = parseInt(st.nextToken());
			int y2 = parseInt(st.nextToken());

			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			
			// 죽음의 구역은 2로 설정 (덮어쓰기) 
			for (int x = x1; x <= x2; x++) {
				for (int y = y1; y <= y2; y++) {
					map[x][y] = 2;
				}
			}
		}

		System.out.println(dijkstra());
		
		
	}
	
	// map[x][y]가 0이면 그대로 Life 유지,
	// map[x][y]가 1이면 life+1 
	// map[x][y]는 이동 불가로 설정 
	public static int dijkstra() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		int[][] dist = new int[501][501];
		for(int i=0; i<=500; i++) {
			Arrays.fill(dist[i],Integer.MAX_VALUE);
		}
		q.offer(new Node(0,0,0));
		dist[0][0] = 0;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			if(cur.life > dist[x][y]) continue; // 이미 최소값이라면 패스 
			
			// 도착하면 
			if(x == 500 && y==500) {
				if(map[x][y] == 2) { // 죽음의 구역이면 -1 출력 
					return -1;
				}
				else {
					return dist[500][500]; // 현재까지 사용한 Life 출력 
				}
				
			}
			
			// 4방탐색 
			for(int dir=0; dir<4; dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				
				// 범위 밖이거나 죽음의 구역이면 패스 
				if(!isIn(nx,ny) || map[nx][ny]==2) continue;
				
				// 최단경로(최소 생명) 갱신하기 
				int newLife = cur.life + map[nx][ny]; // 이 좌표를 통과할때의 life 계산 
				if(dist[nx][ny]>newLife) { // 이전에 저장된 것보다 더 최솟값이라면 
					dist[nx][ny] = newLife; // 갱신하고 
					q.offer(new Node(nx,ny,newLife)); // 다음 탐색 노드로 큐에 추가 
				}
			}
		}
		return -1; // 도달하지 못하면 -1 출력  
	}

	public static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<=500 && y<=500;
	}
}
