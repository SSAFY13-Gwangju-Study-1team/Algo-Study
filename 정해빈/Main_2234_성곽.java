package algo_study;
import java.io.*;
import java.util.*;

class Node{
	int x;
	int y;
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main_2234_성곽 {
	static int N, M;
	static String[][] graph; // 입력받을 2차원 배열 
	static int[] roomSize; // 방의 넓이 
	static int[][] roomNum; // 좌표의 방번호 저장 
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static int[] newdx = new int[4];
	static int[] newdy = new int[4];
	
	public static String decToBin(int num) { // 전달받은 십진수를 문자열 이진수로 반환하는 메서드 
		String str = "";
		for(int i=0; i<4; i++) {
			str = Integer.toString(num%2) + str;
			num /= 2;
		}
		return str;
	}
	
	public static void checkWall(String s) { // 탐색가능한 방향을 알려주는 메서드 
		// 남 동 북 서 
		newdx = dx;
		newdy = dy;
		for(int i=0; i<4; i++) {
			if(s.charAt(i)=='1') { // 벽이 있으면 
				newdx[i] = dx[i]*0; // 0을 곱해줘서 이동하지 못하도록 
				newdy[i] = dy[i]*0;
			}
		}
		for(int i=0; i<4; i++)
			System.out.println(newdx[i] +", "+newdy[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new String[M][N];
		roomNum = new int[M][N];
		roomSize = new int[50];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = decToBin(Integer.parseInt(st.nextToken())); // 이진수 문자열로 변환 후 graph에 저장
				System.out.print(graph[i][j]+" ");
			}
		}
		
		Queue<Node> q = new ArrayDeque<>();
		int roomCnt = 0;
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				q.add(new Node(i,j));
				while(!q.isEmpty()) { // 큐가 빌 때까지 하나의 방을 탐색하는 것이기 때문에
					roomCnt++; // 방의 갯수 + 1
					Node cur = q.poll();
					int x = cur.x;
					int y = cur.y;
					
					
					
					checkWall(graph[x][y]); // 현재 좌표의 방문가능 방향을 체크 후 
					for(int dir=0; dir<4; dir++) {
						int nx = x + newdx[dir];
						int ny = y + newdy[dir];
						if(nx==x && ny==y) continue; // 이동한 좌표가 현재 좌표와 같다면 방향 탐색 필요 없음 
						roomSize[roomCnt]++; // 현재 방 번호에 넓이 +1
						// 큐에 넣기
						q.add(new Node(nx,ny));
						// 이 좌표에 방 번호 배정해주기
						roomNum[x][y] = roomCnt;
						
					}
					
				}
				
				
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(roomNum[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

}
