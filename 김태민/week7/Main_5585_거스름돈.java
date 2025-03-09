import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int result = 1000-n;
		
		int[] coins = {500,100,50,10,5,1};
		int cnt = 0;
		for(int i:coins) {
			cnt+=result/i;
			result= result%i;
		}
		System.out.println(cnt);
	}

}