/**
 * ChatGPT가 도와줬어요 ^_^...
 * 처음에 PriorityQueue를 활용해서 풀이하였지만 실패
 * - 이유1 : 문서의 우선순위를 확인하고 <재배치>하는 과정을 제대로 처리하지 않았기 때문
 * - 이유2 : PriorityQUeue는 우선순위가 높은 요소를 빠르게 꺼낼 수 있지만 <큐의 원래 순서를 보장하지 않음>
 *
 * PriorityQueue와 Queue를 함께 쓰기
 * - PriorityQueue의 역할 : 우선순위를 내림차순으로 정렬
 * - Queue의 역할 : 문서들의 순서를 관리
 *   - queue에서 꺼낸 문서의 우선순위가 pq.peek()과 같다면 가장 우선순위가 크다는 뜻
 *     => queue에서 꺼내고 count++
 *     => 이 때, 문서의 인덱스(order)가 찾고 있던 M과 같다면 종료
 *   - queue에서 꺼낸 문서의 우선순위가 pq.peek()과 다르다면 우선순위가 더 큰 문서가 남아있다는 것
 *     => queue에서 꺼내고 다시 맨 뒤에 add()
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Print {
    int order;
    int priority;

    public Print(int order, int priority) {
        this.order = order;
        this.priority = priority;
    }
}

public class Main_1966_프린터_큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] line1 = br.readLine().split(" ");
            int N = Integer.parseInt(line1[0]); // 문서의 개수
            int M = Integer.parseInt(line1[1]); // 몇 번째로 인쇄되었는지 궁금한 문서의 인덱스

            // pq는 가장 높은 우선순위부터 낮은 우선순위까지를 저장
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            // Queue는 들어온 순서대로 문서들을 저장
            Queue<Print> queue = new LinkedList<>();

            String[] line2 = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                pq.add(Integer.parseInt(line2[j]));
                queue.add(new Print(j, Integer.parseInt(line2[j])));
            }

            int count = 0;
            while(!queue.isEmpty()) {
                Print cur = queue.poll();

                // 현재 꺼낸 문서의 우선순위가 가장 높은 경우
                if (cur.priority == pq.peek()) {
                    pq.poll();
                    count++;

                    // 찾고있던 문서일 경우
                    if (cur.order == M) {
                        break;
                    }
                } else {
                    // queue의 맨 뒤로 이동
                    queue.add(cur);
                }
            }

            System.out.println(count);
        }
    }
}
