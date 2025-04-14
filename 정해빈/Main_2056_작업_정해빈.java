/**
 * 메모리 : 87,664kb / 시간 : 696ms
 * 위상정렬 + dp
 * 순서대로 일을 할 떄 dp에 걸리는 시간 저장 
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2056_작업_정해빈 {
	static class Work {
		int no, time;

		public Work(int no, int time) {
			this.no = no;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = parseInt(br.readLine());
		int[] worktime = new int[N+1];
		int[] inDeg = new int[N+1];
		int[] dp = new int[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer>[] list = new ArrayList[N+1];
		list[0] = new ArrayList<>();
		int result = 0;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			worktime[i] = parseInt(st.nextToken());
			dp[i] = worktime[i];
			int prevCnt = parseInt(st.nextToken());
			if(prevCnt==0) {
				q.offer(i);
			}
			list[i] = new ArrayList<>();
			for(int j=0; j<prevCnt; j++) {
				int p = parseInt(st.nextToken());
				list[p].add(i);
				inDeg[i]++;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : list[cur]) {
				inDeg[next]--;
				dp[next] = Math.max(dp[next], worktime[next]+dp[cur]);
				
				if(inDeg[next]==0) q.offer(next);
			}
		
			
		}
		
		for(int i=1; i<=N; i++) {
			result = Math.max(result, dp[i]);
		}
		

		System.out.println(result);
	}

}
