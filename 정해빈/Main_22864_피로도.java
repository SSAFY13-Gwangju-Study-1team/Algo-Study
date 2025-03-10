package algo_study;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_22864_피로도 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = parseInt(st.nextToken());
		int B = parseInt(st.nextToken());
		int C = parseInt(st.nextToken());
		int M = parseInt(st.nextToken());
		
		int work = 0;
		int fatigue = 0;
		
		for(int i=0; i <24; i++) {
			if(fatigue + A <= M) { // 지금 일해서 피로도가 M 이하라면 
				fatigue += A; // 일해.
				work += B; // 일의 양 
			} else { // 아니면 쉬어 
				if(fatigue < C) fatigue = 0; // fatigue 음수되면 0으로 처리 
				else fatigue -= C;
			}
		}
		System.out.println(work);
	}

}
