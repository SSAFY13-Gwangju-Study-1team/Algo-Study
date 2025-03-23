import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_5567_결혼식_송준영 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static List<List<Integer>> graph;
	static Set<Integer> visited;

	public static void main(String[] args) throws Exception {
		N = parseInt(br.readLine());
		M = parseInt(br.readLine());
		visited = new HashSet<>();
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x, y;
			x = parseInt(st.nextToken());
			y = parseInt(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		BFS();
		System.out.println(visited.size());
		
	}
	
	public static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int num : graph.get(1)) {
			visited.add(num);
			q.offer(num);
		}
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for (int num : graph.get(temp)) {
				if (num == 1) continue;
				visited.add(num);
			}
		}
	}

}
