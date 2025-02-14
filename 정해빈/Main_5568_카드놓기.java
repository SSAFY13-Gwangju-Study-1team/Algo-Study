package algo_study;

import java.io.*;
import java.util.*;

public class Main_5568_카드놓기 {
	static String[] arr; // 만들 카드 수 문자열로 입력받기  
	static int n, k;
	static HashSet<String> hashSet = new HashSet<>(); // 만드는 정수 중복되면 안되므로 set 
	static boolean[] visited; // 방문여부 체크 배열 
	
	public static void dfs(int index, String num) {
		if(index == k) {
			hashSet.add(num); // k개 선택했다면 set에 넣기 
			return;
		}
		for(int i=0; i<n; i++) { // 모든 카드 확인하면서 
			if(!visited[i]) { // 방문하지 않은 카드라면 
				visited[i] = true; // 방문 표시 
				dfs(index+1, num+arr[i]); // 다음 카드 선택하고, 현재카드의 수를 붙이기 
				visited[i] = false; // 방문 체크 해제 
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        arr = new String[n];
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
        	arr[i] = br.readLine();
        }
        
        dfs(0,"");
        
        System.out.println(hashSet.size()); // 만들 수 있는 정수의 갯수이므로 set의 사이즈를 출력 
	}
}
