import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;


public class Main_2110_공유기설치_송준영 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, C;
	static int[] X;
	static int[] dist;
	static long result = 1;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		
		X = new int[N];
		dist = new int[N-1];
		for (int i = 0; i < N; i++) {
			int temp = parseInt(br.readLine());
			X[i] = temp; 
		}
		Arrays.sort(X);
		
		for (int i = 1; i < N; i++) {
			dist[i-1] = X[i] - X[i-1];
		}
		
		long l = 1, r = 1000000000;
		
		while(l <= r) {
			long mid = (l + r) / 2;
			
			if (calculate(mid) >= C) {
				result = Math.max(result, mid);
				l = mid + 1;
			} else {
				r = mid - 1;
			}
			
//			System.out.println(l + " " + r);
		}
		
		System.out.println(result);
	}
	
	public static int calculate(long minSize) {
		int cnt = 1;
		long temp = 0;
		for (int i = 0; i < dist.length; i++) {
			temp += dist[i];
			
			if (temp >= minSize) {
				temp = 0;
				cnt++;
			}
		}
		
		return cnt;
	}
}
