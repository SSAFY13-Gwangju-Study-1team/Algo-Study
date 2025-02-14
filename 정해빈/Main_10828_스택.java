import java.io.*;
import java.util.*;
public class Main_10828_스택 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("push")) {
				int X = Integer.parseInt(st.nextToken());
				s.push(X);
			}
			else if(cmd.equals("pop")) {
				if(!s.isEmpty()) {
					System.out.println(s.pop());
				}
				else System.out.println(-1);
			}
			else if(cmd.equals("size")) {
				System.out.println(s.size());
			}
			else if(cmd.equals("empty")) {
				if(!s.isEmpty()) 
					System.out.println(0);
				else System.out.println(1);
			}
			else if(cmd.equals("top")) {
				if(!s.isEmpty()) {
					System.out.println(s.peek());
				}
				else System.out.println(-1);
			}
		}
	}

}
