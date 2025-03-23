import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 너무 어려웠다 쉽다고 생각했는데 3개 이상 합쳐질 경우를 생각하고
 * 리스트 삭제를 for문 안에서 하려고 하니 너무 힘들었다..
 */
public class SWEA_2382_미생물격리2 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m, k;

    static class Point implements Comparable<Point>{
        int r;
        int c;
        int kcnt;
        int dir;

        public Point(int r, int c, int kcnt, int dir) {
            this.r = r;
            this.c = c;
            this.kcnt = kcnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Point o) {
            if(this.r != o.r){
                return this.r-o.r;
            }else if(this.c != o.c){
                return this.c-o.c;
            }else {
                return o.kcnt-this.kcnt;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            m = parseInt(st.nextToken());
            k = parseInt(st.nextToken());
            ArrayList<Point> list = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                Point point = new Point(parseInt(st.nextToken()), parseInt(st.nextToken()),parseInt(st.nextToken()),parseInt(st.nextToken()) - 1);
                list.add(point);
            }

            // m초 동안 반복
            for (int time = 0; time < m; time++) {
                // 군집 이동
                for(int i=0;i<list.size();i++) {
                    Point p = list.get(i);
                    p.r = p.r +  dr[p.dir];
                    p.c = p.c +  dc[p.dir];

                    // 경계(빨간 부분)에 도달했다면
                    if (p.r <= 0 || p.r >= n - 1 || p.c  <= 0 || p.c >= n - 1) {
                        p.kcnt /=2;
                        if (p.kcnt== 0) {
                            list.remove(i);
                            i--;
                            continue;
                        }
                        if (p.dir == 0) p.dir = 1;
                        else if (p.dir == 1) p.dir = 0;
                        else if (p.dir == 2) p.dir = 3;
                        else if (p.dir == 3) p.dir = 2;
                    }

                }
                // 정렬을 시켜서 list를 r, c기준으로 만들기 -> 만약 같다면 kcnt가 큰게 앞에 나옴
                // 하지만 하나씩 접근해서 maxK가 뭔지를 확인하고 방향을 설정해야 함
                Collections.sort(list);

                for (int i = 0; i < list.size()-1; i++) {  //
                    Point now = list.get(i);
                    Point next = list.get(i + 1);
                    if (now.r == next.r && now.c == next.c) {
                        now.kcnt += next.kcnt;
                        list.remove(i+1);
                        i--;
                    }
                }

            }

            int res = 0;
            for (int i=0;i<list.size();i++){
                res += list.get(i).kcnt;
            }
            sb.append("#" + t + " " + res + "\n");

        }
        System.out.println(sb);
    }
}