/**
 * 메모리 : 231,468kb / 시간 : 1924ms
 * 난이도 : 2/10
 * N,M 갯수가 많아 빠른 탐색을 위해 이분탐색 알고리즘 사용 
 * 10815번 숫자카드 문제와 똑같음 
 * 출력이 길기 때문에 stringbuilder사용 
 * 출력방식 잘 확인하기.. tc끝나고 줄바꿈이 두번 생기지는 않는지..
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2776_암기왕_정해빈 {
	
	static int N,M;
	static int[] memo;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// 입력 및 초기화 
			sb = new StringBuilder();
			N = parseInt(br.readLine());
			memo = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				memo[i] = parseInt(st.nextToken());
			}
			
			// 이분탐색을 위해 찾으려는 수들 정렬 
			Arrays.sort(memo);
			M = parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<M; i++) {
				binarySearch(parseInt(st.nextToken())); // 찾으려는 수들 입력받아 바로 탐색하기 
			}
			
			
			System.out.print(sb); // println쓰면 줄바꿈 두번 되므로 확인하기..
		}

	}
	
	/**
	 * 이분 탐색 메서드 
	 * @param num 찾으려는 수 
	 */
	public static void binarySearch(int num) {
		boolean find = false; // 찾으면 true => 불필요한 반복문 수행 줄이고 결과출력 위해 선언 
		int left = 0; // 왼쪽 포인터 
		int right = N-1; // 오른쪽 포인터 
		while(left<=right) { // 왼 포인터가 범위 넘어가면 수를 찾지 못한 것이므로 탐색 종료 
			int mid = (left+right) / 2; // 기준이 되는 인덱스 
			if(memo[mid]==num) { // 수를 찾으면 플래그 처리 후 반복문 종료 
				find = true;
				break;
			}
			else if(memo[mid] < num) { // 찾으려는 수보다 현재 포인터의 수가 작으면 오른편에 위치한 것이므로 왼쪽포인터 이동  
				left = mid+1;
			}
			else if(memo[mid] > num) { // 반대로 왼쪽에 찾으려는 수가 위치한 것이므로 오른쪽 포인터 이동 
				right = mid-1;
			}
		}
		// 결과 출력 
		if(find) sb.append(1).append("\n");
		else sb.append(0).append("\n");
	}

}
