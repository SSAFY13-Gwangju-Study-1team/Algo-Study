import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node>{
        int vertex;  // 정점 번호
        int weight;  // 시작 정점부터 이 정점까지의 누적 가중치
        int startVertex;  // 시작 정점
        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    // 다익스트라 알고리즘 구현
    public static void dijkstra(int start, List<List<Node>> graph, int[] dist){

        // 거리 값을 담아놓는 배열인 dist를 무한대로 초기화
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0; // 시작 정점은 거리를 0으로 설정

        // 우선순위 큐 사용
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        while(!queue.isEmpty()){
            Node current = queue.poll();
            int currentVertex = current.vertex;
            int currentWeight = current.weight;

            if(dist[currentVertex]<currentWeight){
                continue;
            }

            for(Node node : graph.get(currentVertex)){
                int nextVertex = node.vertex;
                int newDist = dist[currentVertex]+node.weight;

                if(newDist<dist[nextVertex]){
                    dist[nextVertex] = newDist;
                    queue.offer(new Node(nextVertex, newDist));
                }
            }


        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());



        int v = Integer.parseInt(st.nextToken()); // 정점의 갯수
        int m = Integer.parseInt(st.nextToken()); // 간선의 갯수
        int k = Integer.parseInt(st.nextToken()); // 최단거리가 k인
        int startVertex = Integer.parseInt(st.nextToken());

        // 정점과 가중치 정보들을 2차원 배열로 관리
        List<List<Node>> graph = new ArrayList<>();

        // 인접 리스트로 정점의 갯수만큼 그래프를 초기화 하기
        for(int i = 0; i<v+1; i++){
            graph.add(new ArrayList<>());
        }

        // 간선 추가하기(도착 정점, 가중치) 형태임
        for(int i = 0; i<m; i++){
//            int start, vertex, weight;
            // 시작정점(start)-> 목표정점(vertex)까지 가는 가중치가(weight)인 간선추가

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int vertex = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(vertex,1));
        }

        // 각 정점까지의 최단 거리를 저장할 배열
        // 나중에 초기값을 무한대로 할거임
        int[] dist = new int[v+1];


        // 다익스트라 알고리즘 실행
        dijkstra(startVertex, graph, dist);

        boolean bool = false;
        // 결과값 출력하기: dist를 돌면서 원하는 값을 출력하면 됨
        for(int i = 0; i<v+1; i++){
            if(dist[i] == k){
                System.out.println(i);
                bool = true;
            }
        }
        if(!bool){
            System.out.println(-1);
        }
    }
}















