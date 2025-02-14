import java.io.*;
import java.util.*;
public class Main_28278_스택2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int ans = -1;
			switch(cmd) {
			case 1:
				int X = Integer.parseInt(st.nextToken());
				s.push(X);
				break;
				
			case 2:
				if(!s.isEmpty()) 
					ans = s.pop();
				System.out.println(ans);
				break;
				
			case 3:
				ans = s.size();
				System.out.println(ans);
				break;
				
			case 4:
				if(s.isEmpty()) 
					ans = 1;
				else ans = 0;
				System.out.println(ans);
				break;
				
			case 5:
				if(!s.isEmpty()) 
					ans = s.peek();
				System.out.println(ans);
				break;
			}
		}
	}

}
