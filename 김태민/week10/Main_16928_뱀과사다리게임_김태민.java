package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 조건
 *  - 주사위, 크기 10x10, 주사위를 굴려 나온 수만큼 이동
 *  - i번 칸에 있고 나온 숫자가 4라면 i+4번 칸으로 이동
 *  - 주사위 굴린 횟수가 100번이 넘어가면 이동이 안됨.
 *  - 도착한 칸이 사다리 칸이면->위로 올라감(특정 좌표에 도착하면 그 자리로감)
 *  - 뱀도 마찬가지
 *  - 주사위를 몇번 굴러야 도착칸에 갈지
 *
 *  설계
 *   - 빨리 갈려면 뱀을 피해서 가야 빠름
 *   WHY? 결국은 뱀을 만나면 뒤로 가기때문
 *   그래서
 *   - 뱀을 안 마주치면서 사다리가 있는 곳으로 가면됨
 *      근데 이때 사다리로 이동했다가 갈 수 있는 칸 모두가 갈 수 없다면(뱀이라면)
 *      그거는 어짜피 불가능임 절대 못가기 떄문
 *      이거는 고려 안해도 될듯
 * - 백트래킹 돌리면 될듯?
 *
 *
 */

public class Main_16928_뱀과사다리게임_김태민 {
    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static int[] map = new int[101];
    static boolean[] visited = new boolean[101];
    static int result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        for(int i = 1; i<=x; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            map[k] = p;
        }
        for(int i = 1; i<=y; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            map[k] = p;
        }
        result = bfs();
        System.out.println(result);

    }
    static int bfs(){
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(1,0));
        visited[1] = true;

        while (!deque.isEmpty()){
            Node now = deque.pollFirst();
            int x = now.x;
            int y = now.y;
            if(x==100) return y;

            for(int i = 1; i<=6; i++){
                int a = x+i;
                if(a>100) continue;
                if(map[a]>0){
                    a = map[a];
                }
                if(!visited[a]){
                    visited[a] = true;
                    deque.add(new Node(a,y+1));
                }
            }
        }
        return -1;

    }

}
