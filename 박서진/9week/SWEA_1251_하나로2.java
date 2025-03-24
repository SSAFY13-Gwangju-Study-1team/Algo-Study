import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class SWEA_1251_하나로2 {
    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        // 오름차순
        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }
    static int n;
    static double envRate;
    static int[] xPoints;
    static int[] yPoints;
    static Point[] islands;
    static int[] parents;
    static Double resCost;
    static List<Edge> edgeList;

    /**
     * 전략: 순열을 구하면서 백트래킹 이용
     *
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t = 1;t<=tc;t++) {
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            xPoints = new int[n];
            yPoints = new int[n];
            islands = new Point[n];
            parents = new int[n];
            edgeList = new ArrayList<>();
            // x를 받는다
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                xPoints[i] = parseInt(st.nextToken());
            }
            // y를 받는다
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                yPoints[i] = parseInt(st.nextToken());
            }
            envRate = Double.parseDouble(br.readLine());
            // 섬들을 받는다
            for(int i=0;i<n;i++){
                islands[i] = new Point(xPoints[i], yPoints[i]);
            }
            // 간선 리스트 생성
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    double cost = envRate*calcCost(islands[i], islands[j]);
                    edgeList.add(new Edge(i, j, cost));
                }
            }
            // 부모 초기화
            for(int i=0;i<n;i++){
                parents[i] = i;
            }
            resCost = kruskal();
            sb.append("#"+t+" "+Math.round(resCost)).append("\n");
        }
        System.out.println(sb);

    }

    private static double kruskal(){
        Collections.sort(edgeList);
        double totalCost = 0;
        double edgeCount = 0; // 이건 노드-1이어야 한다
        for(Edge edge: edgeList){
            if(union(edge.from, edge.to)){
                totalCost+=edge.cost;
                edgeCount++;
                
                if(edgeCount==n-1) break; //MST 완성
            }
        }
        return totalCost;
    }

    private static boolean union(int from, int to) {
        int rootF = find(from);
        int rootT = find(to);
        if(rootF == rootT) return false;
        parents[rootT] = rootF;
        return true;
    }

    private static int find(int from) {
        if(parents[from]==from) return from;
        return parents[from] = find(parents[from]);
    }


    private static double calcCost(Point prev, Point current){
        long dx = (long) prev.r - current.r;
        long dy = (long) prev.c - current.c;
        return dx * dx + dy * dy;
    }
}
