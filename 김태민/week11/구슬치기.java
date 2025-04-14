import java.io.*;
import java.util.*;

/**
 * 조건
 *  - 구슬의 갯수가 n(홀수)개 있음, 이중에 중간이 될 수 없는 구슬들을 찾아라.
 *  - 그러면 자신 앞에 구슬이 3개 이상이 있거나, 뒤에 3개 이상이 있으면 안됨
 *  - 자기를 따르는 애들 또는 자기가 가르키는 것들 갯수가  n/2+1 이상이면 불가
 *  
 * 
 * 설계
 *  - in 배열과 out 배열 만들기
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {
	static int[] inputList;
	static int[] outputList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] inputList = new ArrayList[n+1];
		ArrayList<Integer>[] outputList = new ArrayList[n+1];
		for(int i = 0; i<=n; i++) {
			outputList[i] = new ArrayList<>();
			inputList[i] = new ArrayList<>();
		}
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			inputList[from].add(to);
			outputList[to].add(from);
		}
		int middleNum = (n-1)/2;
		int result=0;
		for(int i = 1; i<=n; i++) {
			int inputCnt = 0;
			int outputCnt = 0;
			
			inputCnt = dfs(i, inputList, new boolean[n+1]);
			
			outputCnt = dfs(i,outputList,new boolean[n+1]);
			if(inputCnt>middleNum||outputCnt>middleNum) {
				result++;
			}
			
		}
		System.out.println(result);

		
	}
	static int dfs(int n, ArrayList<Integer>[] list, boolean[] visited) {
		int cnt = 0;
		visited[n] = true;
		for(int i: list[n]) {
			if(!visited[i]) {
				cnt++;
				cnt+=dfs(i,list,visited);
			}
		}
		return cnt;
	}
}








