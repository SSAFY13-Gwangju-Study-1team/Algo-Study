package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18352_특정거리의도시찾기 {
	
	static int N, M, K, X;
	
	static int dist[];
//	static int graph[][]; // 메모리 효율을 위해 arraylist에
	static List<List<Integer>> graph;
	
	static Queue<Integer> q;
	
	public static void bfs() {
		while(!q.isEmpty()) {
			int cur = q.poll();
		
			for(int next : graph.get(cur)) {
				
				if(dist[next]==-1) { // 방문하지 않은 노드라면 
					dist[next] = 1 + dist[cur];
					q.add(next);
				}
				else{
					if(dist[next]>dist[cur]+1) // 최단거리 설정 
						dist[next]=dist[cur]+1; // 거리 갱신  
				}
					
				
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<>());
		}
		dist = new int[N+1];
		
		q = new ArrayDeque<>();
		Arrays.fill(dist, -1);
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
		}
				
		q.add(X);
		dist[X] = 0;
 		bfs();
		
		boolean flag = false;
		for(int i=1; i<dist.length; i++) {
			if(dist[i]==K) {
				System.out.println(i);
				flag = true;
			}
		}
		if(!flag) System.out.println(-1);
	}

}
