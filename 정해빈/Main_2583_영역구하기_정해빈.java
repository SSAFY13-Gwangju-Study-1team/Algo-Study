/**
 * 메모리 : 15,884kb / 시간 : 128ms
 * BFS
 * 직사각형 좌표를 -> map으로 구현하는 게 포인트!
 * 이후 BFS로 탐색 후 넓이 갱신하기 
 */
import java.io.*;
import java.util.*;

public class Main_2583_영역구하기_정해빈 {
	static class Node{
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static int M, N, K;
	static int[][] map;

	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};	
	static List<Integer> result;
	
	public static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int k=0; k<K; k++) {
        	st = new StringTokenizer(br.readLine());
        	int x1 = Integer.parseInt(st.nextToken());
        	int y1 = Integer.parseInt(st.nextToken());
        	int x2 = Integer.parseInt(st.nextToken());
        	int y2 = Integer.parseInt(st.nextToken());
        	
        	// 직사각형 범위 안의 좌표를 1로 표시 
        	for(int i=x1; i<x2; i++) {
        		for(int j=y1; j<y2; j++) {
        			map[i][j] = 1;
        		}
        	}
        }
        
        // BFS 수행 
        boolean[][] visited = new boolean[N][M];
        result = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(map[i][j]==0 &&!visited[i][j]) { // 모든 좌표 탐색하면서 0이고, 아직 방문하지 않은 좌표라면 
        			int cnt = 0;
        			// 큐에 넣기 
        			q.offer(new Node(i,j));
        			visited[i][j] = true;
        			
        			while(!q.isEmpty()) {
        				Node cur = q.poll();
        				cnt++; // 영역 넓이 카운팅 
        				int x = cur.x;
        				int y = cur.y;
        				for(int dir=0; dir<4; dir++) {
        					int nx = x + dx[dir];
        					int ny = y + dy[dir];
        					
        					if(isIn(nx,ny) && !visited[nx][ny] && map[nx][ny]==0) {
        						q.offer(new Node(nx,ny));
        						visited[nx][ny] = true;
        					}
        				}
        			}
        			result.add(cnt); // 현재 방문한 영역의 크기 리스트에 저장 
        		}
        	}
        }
        System.out.println(result.size()); // 영역 갯수 
        Collections.sort(result); // 오름차순으로 정렬 이후 넓이 출력 
        for(int r : result) {
        	System.out.print(r+" ");
        }
	}

}
