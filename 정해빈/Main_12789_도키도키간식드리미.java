import java.util.*;
import java.io.*;

public class Main_12789_도키도키간식드리미 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] queue = new int[N]; // 현재 승환이 앞에 서있는 번호
		int[] stack = new int[N]; // 통로에 서있는 번호 
		
		int snack = 1; // 현재 간식 받을 번호 
		int queueSize = N;
		int stackSize = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			
			queue[i] = Integer.parseInt(st.nextToken()); // 승환이 앞에서 기다리는 사람 

		}
		
		for(int i = 0; i < N; i++) { // 큐 순회 
			if(queue[i]==snack) { // 큐에 가장 앞에 서있는 사람이 간식받는 사람이면  
				snack++; // 다음 번호로 pass 
			}
			else { // 큐 가장 앞에 서있는 사람이 간식받는 사람이 아니라면 
				//스택 확인 하기
				if(stackSize!=0) {
					if(stack[stackSize-1] == snack) {// 스택 top이 간식받는 사람이라면 pop하고 다음 번호로 pass
							snack++;
							stackSize--;
					}
					else {
						if(stack[stackSize-1] < queue[i]) {// 스택에 들어오는 값이 이전값보다 크다면 SAD
						 	System.out.println("Sad");
						 	return;
						}
						else stack[stackSize++] = queue[i]; // 스택에 삽입 
					}
					
				}
				 
			}
		}
		if(snack==N)
			System.out.println("Nice");
		else System.out.println("Sad");
		
		
		
		
		
	}

}
