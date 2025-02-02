import java.io.*;
import java.util.*;

public class Main_2605_줄세우기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		LinkedList<Integer> list = new LinkedList<>(); // 원하는 인덱스에 해당 값을 add하기 위해 LinkedList가 적합하다고 생각! 
		int[] index = new int[N];
		
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(st.nextToken());
			index[i] = a;
		}
		
		for(int idx=0; idx<N; idx++) {
			list.add(idx-index[idx],idx+1); // add(int index, int element) 메서드를 통해 원하는 index에 요소를 삽입, 이때 index는 현 인덱스에서 뽑은 수를 뺀 만큼 
		}
		
		StringBuilder sb = new StringBuilder(); // 빠른 출력을 위해 StringBuilder를 통해 list의 요소들을 append 
		for(int a : list) {
			sb.append(a);
			sb.append(" "); // 조건에 맞게 공백 출력 
		}
		
		System.out.println(sb);
	}

}
