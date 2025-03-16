package algo_study;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1744_수묶기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(br.readLine());
		PriorityQueue<Integer> pos = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> neg = new PriorityQueue<>();
		int zero = 0;
		int one = 0;
		int sum = 0;
		for(int i=0; i<N; i++) {
			int num = parseInt(br.readLine());
			if(num>1) pos.offer(num);
			else if(num==1) one++;
			else if(num==0) zero++;
			else {
				neg.offer(num);
			}
		}
		while(pos.size()>1) {
			int num1 = pos.poll();
			int num2 = pos.poll();
			sum+= num1*num2;
		}
		if(!pos.isEmpty()) sum+=pos.poll();
		
		sum+= one;
		
		while(neg.size()>1) {
			int num1 = neg.poll();
			int num2 = neg.poll();
			sum+= num1*num2;
		}
		if(!neg.isEmpty()) {
			if(zero!=0) {
				neg.remove();
				zero--;
			}
			else sum+=neg.poll();
		}
		
		System.out.println(sum);
		
	}

}
