/**
 * 메모리 : 14,820kb / 시간 : 124ms
 * BFS 
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1726_로봇_정해빈 {
	
	static class Node {
		int x, y, d, cmd; // 현재 위치, 방향, 명령횟수 

		public Node(int x, int y, int d, int cmd) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cmd = cmd;
		}
		
	}

	static int N, M;
	static int[][] map;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int[] dir = {1,3,0,2}; // 동 서 남 북 
	
	public static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		// 0 based index 
		// 시작 좌표 + 방향 입력받기 
		st = new StringTokenizer(br.readLine());
		int startX = parseInt(st.nextToken())-1;
		int startY = parseInt(st.nextToken())-1;
		int startDirIn = parseInt(st.nextToken());
		
		// 도착 좌표 + 방향 입력받기 
		st = new StringTokenizer(br.readLine());
		int endX = parseInt(st.nextToken())-1;
		int endY = parseInt(st.nextToken())-1;
		int endDirIn = parseInt(st.nextToken());
		
		// 입력된 방향 내부에서 사용할 것 변환 
		int startDir = dir[startDirIn -1];
		int endDir = dir[endDirIn -1];
		
		// 좌표에서 방향 d로 방문했는지 여부 기록 
		boolean[][][] visited = new boolean[N][M][4];
		
		// BFS 위한 큐 
		Queue<Node> q = new ArrayDeque<>();
		
		// 시작 큐에 추가 + 방문 
		q.offer(new Node(startX, startY, startDir, 0));
		visited[startX][startY][startDir] = true;
		
		int ans = 0;
		
		// BFS 수행 
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			// 도착지에 도달 하면 결과 도출 
			if(cur.x == endX && cur.y == endY && cur.d == endDir) {
				ans = cur.cmd;
				break;
			}
			
			// 좌우 회전 
			int left = (cur.d+1)%4; // 왼쪽 회전
			// 다음 탐색 노드로 추가 
			if(!visited[cur.x][cur.y][left]) {
				visited[cur.x][cur.y][left] = true;
				q.offer(new Node(cur.x, cur.y, left, cur.cmd+1));
			}
			
			int right = (cur.d+3)%4; // 오른쪽 회전
			if(!visited[cur.x][cur.y][right]) {
				visited[cur.x][cur.y][right] = true;
				q.offer(new Node(cur.x, cur.y, right, cur.cmd+1));
			}
			
			// 직진 : 횟수 만큼 각각 검사 
			for(int k=1; k<=3; k++) {
				// 현재 방향으로 k만큼 직진 
				int nx = cur.x + dx[cur.d] * k;
				int ny = cur.y + dy[cur.d] * k;
				
				// 범위 밖이거나 장애물 있으면 패스 
				if(!isIn(nx,ny) || map[nx][ny]==1) break;
				
				// 해당 좌표 방문하지 않으면 방문 후 큐에 추가 
				if(!visited[nx][ny][cur.d]) {
					visited[nx][ny][cur.d] = true;
					q.offer(new Node(nx,ny,cur.d,cur.cmd+1));
				}
			}
		}
		System.out.println(ans); // 최소 명령 횟수 출력 
	}

}
