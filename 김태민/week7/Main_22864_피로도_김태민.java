import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());  // 피로도
		int b = Integer.parseInt(st.nextToken());  // 일
		int c = Integer.parseInt(st.nextToken());  // 피로도 줄어듬
		int m = Integer.parseInt(st.nextToken());  // 피로도 최대치
		int cnt = 0;
		int result = 0;
		int pilodo = 0;
		while(result<24) {
			
			if(pilodo+a<=m) {
				cnt+=b;
				pilodo+=a;
			} else {
				pilodo-=c;
				if(pilodo<0) pilodo=0;
			}
			
			result++;
		}
		
		System.out.println(cnt);
	}

}