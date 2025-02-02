import java.io.*;
import java.util.*;

public class Main_10158_개미 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		
//		int dx = 1; // 방향 x,y는 
//		int dy = 1;
//		
//		//t의 최대값이 200,000,000이므로 반복문은 시간초과 
//		for(int i=0; i<t; i++) {
//			int tx = p + dx; // t만큼 이동 시 x+dx*t, y+dy*t만큼 이동 
//			int ty = q + dy;
//			
//			if(tx==0 || tx==w) { // x축이나 벽을 만나을 때 
//				dx = dx*(-1); // x축 대칭(반사) 
//			}
//			if(ty==0 || ty==h) { // y축이나 벽을 만났을 때 
//				dy = dy*(-1); // y축 대칭(반사) 
//			}
//			
//			p = tx;
//			q = ty;
//			System.out.println(p+", "+q); // p와 q가 0,1,2,3,4,5,...,w(h),w-1(h-1),w-2(h-2),...,2,1,0 규칙이 있
//
//		}
		
		//규칙을 알았으므로 2*w, 2*h 주기마다 나오는 나머지의 값으로 t시간 이후 p,q값을 추측 
		p = (p+t) % (2*w);
		if(p>w) { //내림차순 구간 2*w-p
			p = 2*w - p;
		}
		q = (q+t) % (2*h); // y좌표도 동일 
		if(q>h) {
			q = 2*h - q;
		}
		
		System.out.printf("%d %d\n", p,q);
		
	}

}
