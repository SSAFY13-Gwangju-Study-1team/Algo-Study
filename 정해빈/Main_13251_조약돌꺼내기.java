package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13251_조약돌꺼내기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int[] color = new int[M];
		st = new StringTokenizer(br.readLine());
		int total = 0;
		for(int i=0; i<M; i++) {
			color[i] = Integer.parseInt(st.nextToken());
			total+=color[i];
		}
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		if(M==1 || K ==1) {
			System.out.println(1.0);
			return;
		}
		else {
			//나누는 수 = 각 색깔별합(전체갯수) * (전체갯수-1) * (전체갯수-2) * ...
			//나눠지는 수 = 색깔의 갯수 * (색깔의 갯수-1) * (색깔의 갯수-2) * ...
			double result = 0.0;
			for(int i=0; i<M; i++) {
				double prob = 1.0;
				for(int j=0; j<K; j++) {
					prob *= (double)(color[i]-j)/(total-j);
//					System.out.print(prob+" ");
				}
				result+=prob;
			}
			System.out.println(result);
		}
		/**
		 * 종류가 1개이거나 뽑는 수가 1이면 확률은 무조건 1
		 * m = 3 color={5, 6, 7} K = 2
		 * 확률합- color.length만큼 
		 * 각 확률은 5/( (5 + 6 + 7) * 4/( 5+6+7) * 3/(5+6+7)
		 * 
		 */
		
	}

}
