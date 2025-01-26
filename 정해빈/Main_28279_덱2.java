import java.util.*;
import java.io.*;

public class Main_28279 {
	static int N, cmd, ans;
	static Deque<Integer> deque = new LinkedList<>();
	
	public static void solve(int command, int X) {
		
		
		
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(bf.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			cmd = Integer.parseInt(st.nextToken());
			switch(cmd) {
			case 1:
				deque.offerFirst(Integer.parseInt(st.nextToken()));
				break;
			case 2:
				deque.offer(Integer.parseInt(st.nextToken()));
				break;
			case 3:
				if(deque.isEmpty()) {
					ans = -1;
				}
				else ans = deque.poll();
				sb.append(ans);
				sb.append("\n");
				break;
			case 4:
				if(deque.isEmpty()) {
					ans = -1;
				}
				else ans = deque.pollLast();
				sb.append(ans);
				sb.append("\n");
				break;
			case 5:
				ans = deque.size();
				sb.append(ans);
				sb.append("\n");
				break;
			case 6:
				if(deque.isEmpty()) {
					ans = 1;
				}
				else ans = 0;
				sb.append(ans);
				sb.append("\n");
				break;
			case 7:
				if(deque.isEmpty()) {
					ans = -1;
				}
				else ans = deque.peek();
				sb.append(ans);
				sb.append("\n");
				break;
			case 8:
				if(deque.isEmpty()) {
					ans = -1;
				}
				else ans = deque.peekLast();
				sb.append(ans);
				sb.append("\n");
				break;
			}
		}
		
		System.out.println(sb);
	}
}
