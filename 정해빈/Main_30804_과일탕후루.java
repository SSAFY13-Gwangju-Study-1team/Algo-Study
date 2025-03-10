package algo_study;

import java.util.*;
import java.io.*;

public class Main_30804_과일탕후루 {
	static int N, max, ptr1, ptr2, cnt;
	static int[] fruits;
	static int[] fruitType;
	

	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		fruits = new int[N]; // 탕후루에 들어간 과일 순서대로 저장하는 배열 
		fruitType = new int[10]; // 과일종류 1~9 각각 갯수 저장하기 위한 배열 
		
		// 입력받기
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken()); 
		}
		
		ptr1 = 0; // 먼저 출발하는 포인터 
		ptr2 = 0; // 이후 출발하는 포인터 
		max = 0; // 탕후루 최대 길이 저장하는 변수 
		cnt = 0; // 과일을 제거한 현재 탕후루의 과일 종류 갯수 
	
		while(ptr1<N) {
			if(fruitType[fruits[ptr1]] == 0) { // 현재 포인터1이 가리키는 과일의 수가 0이라면
				cnt++; // 넣는 순간 새로운 과일 수가 추가되어야 함!!
			}
			fruitType[fruits[ptr1++]]++; // 포인터 다음 탕후루 과일로 이동 
			
			while(cnt > 2) { // 현재 탕후루의 종류 갯수가 3개 이상이라면 포인터2 움직여서 빼야함 
				fruitType[fruits[ptr2]]--;
				if(fruitType[fruits[ptr2]]==0) { // 포인터2가 가리킨 과일 하나가 빠져서 종류 하나가 사라졌다면 
						cnt--; // 종류 한개 감소 
				}
				ptr2++; // 과일 종류 2이하 될 때까지 포인터2 오른쪽으로 이동하기 
			}
			max = Math.max(max, ptr1 - ptr2);
		}
		System.out.println(max);
		
	}

}
