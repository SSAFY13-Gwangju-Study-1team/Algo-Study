/**
 * 메모리 : 51,228kb / 시간 : 388ms
 * union-find
 * 연결되어있는 건물이 한 집합에 있다고 할 때
 * 건물 숫자가 더 작은 건물을 대표자로 설정 
 * 건물 간 같은 집합에 있는지 확인하고 있으면 패스, 없으면 +1 카운팅 
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_24391_귀찮은해강이_정해빈 {
	
	static int N, M;
	static int[] parent;
	
	// union-find 
	public static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot==bRoot) return;
		if(aRoot>bRoot) { // 번호 작은 애를 대표자로 
			int tmp = bRoot;
			bRoot = aRoot;
			aRoot = tmp;
		}
		parent[bRoot] = aRoot;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = parseInt(st.nextToken());
			int b = parseInt(st.nextToken());
			
			union(a,b); // 두 건물 연결되어있으면 한 집합으로 합치기 
		}
		
		int result = 0;
		
		// 한줄로 입력받기 
		st = new StringTokenizer(br.readLine());
		int start = parseInt(st.nextToken()); // 첫번째 강의 장소 
		for(int i=1; i<N; i++) {
			// start - next 가 한 집합안에 있으면 패스, 없으면 이동해야하므로 +1 카운팅 
			int next = parseInt(st.nextToken());
			if(find(start)==find(next)) continue; 
			result++;
			start = next; // 비교 대상 바꾸기 
		}
		System.out.println(result); // 결과 출력 

	}

}
