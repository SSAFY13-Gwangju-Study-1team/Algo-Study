package algo_study;

import java.io.*;
import java.util.*;

public class Main_1759_암호만들기 {
	
	static int L, C;
	static char[] pw;
	static char[] arr;

	public static void main(String[] args) throws IOException {
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	L = Integer.parseInt(st.nextToken());
	C = Integer.parseInt(st.nextToken());
	
	st = new StringTokenizer(br.readLine(), " ");
	arr = new char[C];
	pw = new char[L];
	
	
	
	for(int i=0; i<C; i++) { 
		arr[i] = st.nextToken().charAt(0);
	}

	Arrays.sort(arr); // 오름차순(사전순)으로 암호를 만들기 때문 
	
	dfs(0,0);
	
	}
	
	// 암호문이 조건에 부합하는 지 확인하는 메서드 
	public static boolean check(char[] pw) {
		int consonant = 0; // 자음 개수 
		int vowel = 0; // 모음 개수 
		for(int i=0; i<pw.length; i++) {
			if(pw[i]=='a' || pw[i]== 'e' || pw[i] == 'i' || pw[i]=='o' || pw[i]=='u')
				vowel++; // 모음 들어 있으면 모음카운트 
			else consonant++; // 자음 카운트 
		}
		if( consonant >= 2 && vowel >= 1) return true; // 자음 2개 이상, 모음 1개 이상이면 ok 
		else return false; // 아니면 False 
	}
	
	// 순서가 있고, 중복을 포함하지 않음! -> 순열 
	public static void dfs(int start, int depth) {
		if (depth == L) {
			if(check(pw)) { // 암호 조건 확인 
				System.out.println(new String(pw)); // pw 출력값을 잘못줘서 계속 틀려서 GPT써서 알아냄..
			}
			return;
		}
		
		// 자리수 채우기 
		for(int i = start; i < C; i++) {
			pw[depth] = arr[i]; // 다음으로 넘어가기 위해 현재 얻은 암호값 출력값에 저장 
			dfs(i+1, depth+1); // 시작점으로 사용된 요소는 사용 안함 으로 다음 값을 넘겨준다 
		}
	}

}
