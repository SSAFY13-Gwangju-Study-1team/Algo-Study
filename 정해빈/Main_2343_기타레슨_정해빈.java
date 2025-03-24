/**
 * 메모리 : 24,328kb / 시간 : 268ms
 * 난이도 : 4/10
 * 이분탐색이라는 카테고리 내에 있어서 쉽게 느껴졌을지두,,
 * 적절한 블루레이 크기를 찾기 위해서 <<블루레이크기>> 를 이분탐색의 대상으로 설정해야한다는 것을
 * 생각하는게 조금 어려웠다
 * 대상을 설정하고
 * 최소 크기, 최대크기를 구해 left, right로 설정하고 조건에 맞게 탐색하면 어렵지 않다!!
 * 골드 문제 다덤벼!
 */

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2343_기타레슨_정해빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		
		long left = 0; // 가장 긴 비디오 
		long right = 0; // 블루레이의 최대 크기 
		int[] videos = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			videos[i] = parseInt(st.nextToken());
			left = Math.max(left,videos[i]); // 블루레이 크기의 가장 작은 값 
			right += (long)videos[i]; // 블루레이 크기의 가장 큰 값 
		}
		// 이분 탐색 => 모든 블루레이의 갯수를 만족하며 최소 블루레이 크기 찾기 
		while(left <= right) {
			long mid = (left+right)/2;
			int count = 1;
			long sum = 0;
			for(int video : videos) { // 현재 블루레이 크기에 비디오 넣어 블루레이 갯수 세기 
				if(sum + video > mid) { // 만약 크기가 넘으면 
					count++; // 새로운 블루레이에 넣기 
					sum = 0;
				}
				sum+=video;
			}
			if(count<=M) { // 만약 조건을 만족하면 
				right = mid-1; // 최소 크기를 구하기 위해 right 포인터 이동 
			}
			else left = mid+1; // 그렇지 않으면 크기 키워주기 
		}
		System.out.println(left); // 최소 크기 출력 

	}

}
