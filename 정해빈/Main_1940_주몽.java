package algo_study.week05;

/**
 * 메모리 : 16,484kb, 시간: 168ms
 * 알고리즘 시간에 배운 투포인터 내용 그대로 적용해서 풀면 쉬운 문제!
 * 난이도 : 1/10
 * 중복되지 않는 값일때 사용할 수 있는 방법
 */
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1940_주몽 {
	
	static int N, M;
	static int[] armors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = parseInt(br.readLine());
		M = parseInt(br.readLine());
	
		armors = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			armors[i] = parseInt(st.nextToken());
		}
		Arrays.sort(armors); // 중복되지 않은 수열에서 정렬을 해줘야 투포인터 사용이 가능 
		
		// 1 2 3 4 5 7
		int start = 0; // 앞에서부터 탐색하는 포인터. 정렬되어 있으므로 ++만 가능 
		int end = N-1; // 제일 뒤에서부터 탐색하는 포인터. 정렬되어 있으므로 --만 가능
		int sum = 0; // target값과 비교할 합 
		int cnt = 0; // 출력값 
		while(true) {
			sum = armors[start] + armors[end]; // 현재 가리키는 값들의 합을 저장 
			
			if(start>=end) break; // 두개의 포인터가 같은 곳을 가리키거나 앞포인터가 뒤 포인터를 넘어가는 경우에는 모든 탐색을 마친 것이므로 종료 
			
			if(sum==M) { // 값을 찾으면 종료하는 것이 아니라 
				cnt++; // 일단 카운터 해주고 
				start++; // 다른 값들을 찾아야하기 때문에 두 포인터 모두 움직여 준다 
				end--;
			}
			if(sum<M) { // 타겟값보다 합이 더 작으면 더 큰 수를 넣어 비교해야하므로 앞 포인터를 움직여준다 
				start++;
			}
			if(sum>M) { // 타겟값보다 합이 더 크면 더 작은 수를 넣어 비교해야하므로 뒤 포인터를 움직여준다 
				end--;
			}
		}
		System.out.println(cnt);
	}

}
