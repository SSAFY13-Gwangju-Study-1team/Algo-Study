package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

class Node {
	private int x;
	private int y;
	
	Node(){};
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}

public class Main_2667_단지번호붙이기 {
	
	static int N;
	static int[][] graph;
	static boolean[][] visited; 
	
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		ArrayList<Integer> building = new ArrayList<>(); // 각 단지마다 집의 수를 저장하기 위한 배열 
		
		//지도 입력 
		for(int i=0; i<N; i++) {
			String str = br.readLine(); // 띄어쓰기가 없으므로 문자열로 받고 잘라서 정수로 변환 
			for(int j=0; j<N; j++) {
				graph[i][j] = str.charAt(j)-'0';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(graph[i][j] == 0 || visited[i][j]) continue; // 방문한 좌표거나 집이 아니라면 다른 방향 탐색
				int cnt = 0; // 현재 단지의 집의 수 
				Queue<Node> queue = new ArrayDeque<>(); // bfs를 위해 큐를 만들고 현재 위치 큐에 push 
				queue.add(new Node(i,j));
				
				visited[i][j] = true; // 현재 좌표 방문 체크 
				
				while(!queue.isEmpty()) { // 큐가 빌때까지 같은 단지인지 탐색 
					Node cur = queue.poll(); // 제일 앞에 있는 좌표 가져옴 
					cnt++;
					
					for(int dir=0; dir<4; dir++) { // 4방 탐색 시작 
						int nx = cur.getX() + dx[dir];
						int ny = cur.getY() + dy[dir];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue; // 다음 좌표가 범위 밖이라면 다른 방향 탐색
						if(graph[nx][ny] != 1 || visited[nx][ny]) continue; // 집이 아니고 방문한 좌표라면 다른 방향 탐색 
						
						visited[nx][ny] = true; // 다음좌표 방문 체크 
						queue.add(new Node(nx,ny)); // bfs를 위해 큐에 넣어줌 
					}
				}
				building.add(cnt); // 단지의 집의 수 추가 
			}
		}
		Collections.sort(building);
		System.out.println(building.size()); // arraylist의 크기가 단지의 수 
		for(int i : building) { // 각 단지마다 집의 수를 출력 
			System.out.println(i);
		}

	}

}
