package algo_study;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	private int x;
	private int y;
	private int cost;
	
	Node(){};
	
	Node(int x, int y, int cost){
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getCost() {
		return this.cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main_4485_녹색옷{
	
	static int N;
	static int[][] graph;
	static int[][] dist;
	
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	
	static PriorityQueue<Node> pq;
	
	public static boolean isIn(int x, int y) { // 그래프범위 안에 있는지 확인하는 메서드 
		return x<N && x>=0 && y<N && y>=0;
	}
	
	public static void dijkstra() {
		
		pq.add(new Node(0, 0, graph[0][0]));
		dist[0][0] = graph[0][0];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				while(!pq.isEmpty()) {
					Node cur = pq.poll();
					int x = cur.getX();
					int y = cur.getY();
					int cost = cur.getCost();
					
					if(cost > dist[x][y]) continue; // 이미 큰 값이면 패스 
					
					for(int dir=0; dir<4; dir++) {
						int nx = x + dx[dir];
						int ny = y + dy[dir];
						
						if(isIn(nx,ny)) { // 범위 안에 있다면 
							int nextCost = graph[nx][ny] + dist[x][y];
							if(nextCost < dist[nx][ny]) { // 방문 안한 좌표이면 
								dist[nx][ny] = graph[nx][ny] + dist[x][y]; // 거리 비교 후 최단거리 갱신 
								pq.add(new Node(nx,ny,graph[nx][ny])); // 방문예정 좌표 우선순위 큐에 추가 
							}
							
							
						}
						
						
					}
					
				}
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			
			graph = new int[N][N];
			dist = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			pq = new PriorityQueue<Node>();
			
			dijkstra();
			
			System.out.println("Problem " + tc + ": " + dist[N-1][N-1]);
			tc++;
		}
	}

}
