import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/*
 * 0 : x, 1 : y 2 : 회전 (1 : 가로, -1 : 세로), 3 : 몇 번째
 */

public class Main_1938_통나무옮기기_송준영 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, result = 0;
	static char[][] map;
	static boolean[][][] visited;
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	static int Ei = 0;
	static int EX = 0;
	static int EY = 0;
	static int EP = 0;

	public static void main(String[] args) throws Exception {
		N = parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2];
		int[][] tempB = new int[3][2];
		int[][] tempE = new int[3][2];
		int Bi = 0;
		int BX = 0;
		int BY = 0;
		
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B') {
					tempB[Bi][0] = i;
					tempB[Bi][1] = j;
					BX += i;
					BY += j;
					Bi++;
				}
				
				if (map[i][j] == 'E') {
					tempE[Ei][0] = i;
					tempE[Ei][1] = j;
					EX += i;
					EY += j;
					Ei++;
				}
			}
		}
		
		EX = EX / 3;
		EY = EY / 3;
		if (tempE[0][0] == tempE[1][0] && tempE[1][0] == tempE[2][0]) {
			EP = 0;
		} else {
			EP = 1;
		}
		
		int realX = BX / 3, realY = BY / 3;
		if (tempB[0][0] == tempB[1][0] && tempB[1][0] == tempB[2][0]) {
			BFS(realX, realY, 0);
		} else {
			BFS(realX, realY, 1);
		}
		
		System.out.println(result);
		
	}
	
	public static void BFS(int sx, int sy, int phase) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {sx, sy, phase, 0});
		visited[sx][sy][phase] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[0] == EX && cur[1] == EY && cur[2] == EP) {
				result = cur[3];
				return;
			}
			
			for (int i = 0; i < 5; i++) {
				if (i == 4) {
					if (cur[2] == 0) {
						if (!isPossible(cur[0], cur[1], cur[2], i)) continue;
						if (!visited[cur[0]][cur[1]][1]) {
							visited[cur[0]][cur[1]][1] = true;
							q.offer(new int[] { cur[0], cur[1], 1, cur[3] + 1});
						}
					} else {
						if (!isPossible(cur[0], cur[1], cur[2], i)) continue;
						if (!visited[cur[0]][cur[1]][0]) {
							visited[cur[0]][cur[1]][0] = true;
							q.offer(new int[] { cur[0], cur[1], 0, cur[3] + 1});
						}
					}
				} else {
					if (cur[2] == 0) {
						if (!isPossible(cur[0], cur[1], cur[2], i)) continue;
						if (!visited[cur[0] + dx[i]][cur[1] + dy[i]][0]) {
							visited[cur[0] + dx[i]][cur[1] + dy[i]][0] = true;
							q.offer(new int[] { cur[0] + dx[i], cur[1] + dy[i], 0, cur[3] + 1});
						}
					} else {
						if (!isPossible(cur[0], cur[1], cur[2], i)) continue;
						if (!visited[cur[0] + dx[i]][cur[1] + dy[i]][1]) {
							visited[cur[0] + dx[i]][cur[1] + dy[i]][1] = true;
							q.offer(new int[] { cur[0] + dx[i], cur[1] + dy[i], 1, cur[3] + 1});
						}
					}
				}
				
			}
		}
	}
	
	public static boolean isPossible(int x, int y, int p, int com) {
		
		if (isIn(x, y) == false) return false;
		
		switch (com) {
		case 0:
			if (p == 0) {
				for (int i = -1; i <= 1; i++) {
					if (isIn(x - 1, y + i) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x - 1][y + i] == '1') {
						return false;
					}
				}
			} else {
				if (isIn(x - 2, y) == false) {
					return false;
				}
				
				if (map[x - 2][y] == '1') {
					return false;
				}
			}
			
			break;
		case 1:
			if (p == 0) {
				for (int i = -1; i <= 1; i++) {
					if (isIn(x + 1, y + i) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x + 1][y + i] == '1') {
						return false;
					}
				}
			} else {
				if (isIn(x + 2, y) == false) {
					return false;
				}
				
				if (map[x + 2][y] == '1') {
					return false;
				}
			}
			
			break;
		case 2:
			if (p == 0) {
				if (isIn(x, y - 2) == false) {
					return false;
				}
				
				if (map[x][y - 2] == '1') {
					return false;
				}
				
			} else {
				for (int i = -1; i <= 1; i++) {
					if (isIn(x + i, y - 1) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x + i][y - 1] == '1') {
						return false;
					}
				}
			}
	
			break;
		case 3:
			if (p == 0) {
				if (isIn(x, y + 2) == false) {
					return false;
				}
				
				if (map[x][y + 2] == '1') {
					return false;
				}
				
			} else {
				for (int i = -1; i <= 1; i++) {
					if (isIn(x + i, y + 1) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x + i][y + 1] == '1') {
						return false;
					}
				}
			}
	
			break;
		case 4:
			if (p == 0) {
				for (int i = -1; i <= 1; i++) {
					if (isIn(x - 1, y + i) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x - 1][y + i] == '1') {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (isIn(x + 1, y + i) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x + 1][y + i] == '1') {
						return false;
					}
				}
				
			} else {
				for (int i = -1; i <= 1; i++) {
					if (isIn(x + i, y - 1) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x + i][y - 1] == '1') {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (isIn(x + i, y + 1) == false) {
						return false;
					}
				}
				
				for (int i = -1; i <= 1; i++) {
					if (map[x + i][y + 1] == '1') {
						return false;
					}
				}
			}
	
			break;
		}
		
		return true;
	}
	
	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
