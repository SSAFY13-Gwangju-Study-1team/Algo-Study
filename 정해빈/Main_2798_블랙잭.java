import java.io.*;
import java.util.*;


public class Main_2798_블랙잭 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] cards = new int[N];
		
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		int t_sum = 0;
		int max_sum = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					t_sum = cards[i] + cards[j] + cards[k];
					if(t_sum > M) {
						continue;
					}
					else {
						max_sum = Math.max(t_sum, max_sum);
					}
				}
			}
		}
		System.out.println(max_sum);
	}

}
