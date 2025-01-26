import java.io.*;
import java.util.*;

public class Main_1966_프린터큐 {
	
	static class Data {
		int index;
		int priority;
		
		public Data(int index, int priority) { // 인덱스와 우선순위를 함께 저장할 객체 생성 
			this.index = index;
			this.priority = priority;
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			Queue<Data> queue = new LinkedList<>(); // 순서 확인할 큐 생성 
			PriorityQueue<Data> priorityQueue = new PriorityQueue<>(new Comparator<Data>() { // 중요도 내림차순으로 저장할 큐 생성 
				public int compare(Data d1, Data d2) { // 중요도가 큰 순서대로 삽입해야하므로 내림차순 우선순위 큐를 만듦
					return (d1.priority - d2.priority)*(-1); // data의 priority로 순서 비교 + (-1)을 곱하므로 내림차순으로 저장 가능 
				}
			});
			
			for(int i = 0; i < N; i++) { // 입력데이터 큐에 삽입 
				int priority = Integer.parseInt(st.nextToken());
				Data d = new Data(i,priority);
				queue.offer(d);
				priorityQueue.offer(d);
			}
			
			int result = 1; // 출력할 답 >=1
			while(!queue.isEmpty()) { // 큐가 빌 때까지 
				if(queue.peek().priority == priorityQueue.peek().priority) { 
					if(queue.peek().index == M) break; // 우선순위가 큐의 첫번째값과 일치하고, index == M이라면 반복문 종료.
					else { // 현재 뽑으려는 값이 우선순위 큐값과 일치하면 
						result++; // 프린트하기 
						queue.poll(); // 큐에서 제거 
						priorityQueue.poll(); // 우선순위 큐에서 제거 
					}
				}
				else queue.offer(queue.poll()); // 뽑으려는 우선순위와 queue가 일치하지 않으면 맨뒤로..
				
			}
			queue.clear(); // 초기화 
			priorityQueue.clear();
			
			System.out.println(result); // 결과값 출력
		}
		
		
		
		
	}
}
