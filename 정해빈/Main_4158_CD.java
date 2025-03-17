/**
 * 메모리 : 277,548kb / 시간 : 852ms
 * testcase가 여러개라는 지문을 안보고 입력 처리를 제대로 안해줬더니 계속 오류...
 * 0 0을 넣으면 테스트케이스를 종료한다는 뜻이라는 걸 몰랐음
 * 투포인터 이용해서 상근이와 선영이의 가지고있는 cd를 비교하면 됨 
 * 문제 구현에 있어서 오름차순이라는 지문에서 힌트를 얻어 쉽게 구현 
 */

package algo_study.week05;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_4158_CD {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cnt;
		
		while(true) { // 테스트 케이스가 여러개 이므로 while문 구현 
				
			st = new StringTokenizer(br.readLine());
			
			int N = parseInt(st.nextToken());
			int M = parseInt(st.nextToken());
			
			if(N==0 && M==0) { // 0 0이 입력되면 종료 
				break;
			}
			int[] cd1 = new int[N]; // 상근이의 cd 
			int[] cd2 = new int[M]; // 선영이의 cd 초기화 
			
			for(int i=0; i<N; i++) {
				cd1[i] = parseInt(br.readLine()); // 상근이의 cd입력 
			}
			
			for(int i=0; i<M; i++) {
				cd2[i] = parseInt(br.readLine()); // 선영이의 cd 입력 
			}
			
	
			int ptr1 = 0; // 상근이 cd search pointer
			int ptr2 = 0; // 선영이 cd search pointer
			cnt = 0; // 같은 cd 갯수 저장 변수 
			while(ptr1 < N && ptr2 < M) { // 포인터들이 배열 길이 안에서 움직여야 하고 넘어가면 배열 탐색이 끝난것이므로 종료! 
				if(cd1[ptr1] < cd2[ptr2]) ptr1++; // 오름차순으로 정렬되어 있으므로 만약 선영이의 cd숫자가 더 크면 상근이의 확인할 cd idx를 1늘려준다 
				else if(cd1[ptr1] > cd2[ptr2]) ptr2++; // 위와 마찬가지 
				else { // 둘의 cd가 같다면 두 포인터 다 하나씩 이동, cnt+1 
					ptr1++;
					ptr2++;
					cnt++;
				}
			}
			System.out.println(cnt); // cd 갯수 출력 
		}
		
	}

}
