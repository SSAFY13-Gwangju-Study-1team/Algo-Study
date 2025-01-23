import java.util.*;

public class Main_17608 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int h=0;
		int ans = 0;
		Stack<Integer> s = new Stack<>();
		for(int i=0; i<N; i++) {
			h = sc.nextInt();
			
			s.push(h);
			
		}
		int building = s.peek();
		int max = 0;
		while(!s.isEmpty()) {
			if(max<s.peek()) {
				ans++;
				max = Math.max(max, s.peek());
			}
			s.pop();
			
		}
		System.out.println(ans);
	}

}
