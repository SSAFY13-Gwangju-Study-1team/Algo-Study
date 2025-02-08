package algo_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main_2527_직사각형 {
	
	static int x1, y1, p1, q1, x2, y2, p2, q2;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			p1 = Integer.parseInt(st.nextToken());
			q1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			p2 = Integer.parseInt(st.nextToken());
			q2 = Integer.parseInt(st.nextToken());
			
			
			// d : 안 만나는 경우 
			if(p1<x2 || p2<x1 || q1 < y2 || q2 < y1) { 
				System.out.println("d");
			}// c : 꼭지점에서 만나는 경우
			else if((p1==x2 && q1 == y2)|| (p1==x2 && y1 == q2) ||
						(p2 == x1 && q2 == y1) || (p2 == x1 && q1 == y2)) {  
				System.out.println("c");
			} // b : 한 변이 겹치는 경
			else if(p1 == x2 ||p2 == x1 || q1 == y2 || y1 == q2) {
				System.out.println("b");
			} // a : 직사각
			else {
				System.out.println("a");				
			}
			
			
			
		}
	}
}
