import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 시간 :1948ms
 * greedy 대비 문제로 풀어봤음
 * 무게를 기준으로 정렬을 시켜야 함이 핵심이고 우선순위 큐를 활용하여 보석의 가치가 높은 것을 써야한다
 * 문제의 답에 long을 써야 한다!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 가방에는 1개의 보석만 담을 수 있음
 *
 */
class Diamond implements Comparable<Diamond>{
    int weight;
    int value;

    public Diamond(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Diamond o) {
        return (this.weight!=o.weight)? this.weight-o.weight:o.value-this.value; // 무게로는 오름차순, 무게가 같을 때는 가치를 기준으로 내림차순
    }
}
public class Main_1202_보석도둑 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder()); // 보석의 가치를 담을 큐 선언(가치가 큰 것부터 먼저 나와야 함)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());
        long ans=0;
        Diamond[] diamonds = new Diamond[n]; // 보석을 담을 우선순위 큐
        int[] bags = new int[k];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            diamonds[i]=new Diamond(parseInt(st.nextToken()),parseInt(st.nextToken()));
        }
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            bags[i] = parseInt(st.nextToken());
        }

        // 보석과 가방을 무게로 오름차순 정렬
        Arrays.sort(diamonds);
        Arrays.sort(bags);
        int index = 0;

        // 가방의 개수만큼 가방을 채우면서 반복
        for(int i=0;i<k;i++){
            // 가방의 무게 보다 보석의 무게가 작으면 큐에 넣기
            int bagWeight = bags[i];
            while(index<n && bagWeight>=diamonds[index].weight){
                q.add(diamonds[index].value);
                index++;
            }
            // 가방 하나가 끝나기 전에 큐의 맨 첫 번째 가방 중 가격이 가장 높은 것
            if(!q.isEmpty()){
                ans+=q.poll();
            }
        }
        System.out.println(ans);
    }
}
