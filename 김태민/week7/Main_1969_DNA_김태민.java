import java.io.*;
import java.util.*;

/**
 * 
 * - 각 자리수에서 가장 많이 나온 알파벳을 선정하는데(다 똑같이 나오면 사전순으로 가장 앞서는걸 출력)
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[n][m];
		
		int[] dna = new int[4]; // A,C,G,T
		char[] result = new char[m];
		int distance = 0;
		
		for(int i = 0; i<n; i++) {
			String s = br.readLine();
			for(int j = 0; j<m; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		for(int i = 0; i<m; i++) {
			dna = new int[4];
			for(int j = 0; j<n; j++) {
				char alpha = map[j][i];
				switch (alpha) {
				case 'A':
					dna[0]++;
					
					break;
				case 'C':
					dna[1]++;
					
					break;
				case 'G':
					dna[2]++;
					
					break;
				case 'T':
					dna[3]++;
					
					break;

				default:
					break;
				}
			}
			int index = 0;
			int max=0;
			
			for(int k = 0; k<4; k++) {
				if(max<dna[k]) {
					max = dna[k];
					index = k;
				} 
			}
			distance+=n-max;
			
			
			
			switch (index) {
			case 0:
				result[i] = 'A';
				
				break;
			case 1:
				result[i] = 'C';

				
				break;
			case 2:
				result[i] = 'G';

				
				break;
			case 3:
				result[i] = 'T';

				
				break;

			default:
				break;
			}
			
			
			
			
		}
		for(int i = 0; i<m;i++) {
			System.out.print(result[i]);
		}
		System.out.println();
		System.out.println(distance);
		
	}


}














