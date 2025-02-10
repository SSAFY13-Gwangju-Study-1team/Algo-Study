// 시간초과로 인터넷 검색, 챗 GPT 사용
// 1. 인터넷 검색: 집과 치킨집의 좌표를 class로 만들어 사용하는 것을 확인
// -> 코드가독성이 훨씬 좋아보여서 int[2]로 x,y좌표 저장하던 것을 position 클래스로 만듬
// 2. 챗GPT: 시간 초과가 나는 것은 매번 dfs를 할 때마다 치킨 거리를 계산하는 것으로 의심됨
// -> 미리 모든 집과 치킨집의 거리를 계산하고 저장해 둠(메모이제이션 적용)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class position {
    int y;
    int x;
    position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;

    static ArrayList<position> house;
    static ArrayList<position> kfc;
    static boolean[] closed;
    static int ans;
    static int closeNum;
    static int[][] allDistance;

    public static void main(String args[]) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시 크기
        M = Integer.parseInt(st.nextToken());   // 폐업시키지 않을 최소 치킨집 수

        house = new ArrayList<>();  // 집 위치 저장
        kfc = new ArrayList<>();    // 치킨집 위치 저장

        int[][] city = new int[N][N];   // 0=빈칸, 1=집, 2=치킨집
        // 지도 채우기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 1)
                    house.add(new position(i, j));
                else if(city[i][j] == 2)
                    kfc.add(new position(i,j));
            }
        }

        closed = new boolean[kfc.size()];
        ans = Integer.MAX_VALUE;    // 치킨거리의 최솟값을 저장하는 변수
        closeNum = kfc.size() - M;  // 닫아야 하는 치킨집의 수
        
        // 각 집의 각 치킨집별 치킨거리를 미리 계산(중복 계산 방지 = 시간 초과 방지)
        allDistance = new int[house.size()][kfc.size()];
        int curDistance;
        for(int i=0; i<house.size(); i++) {
            for(int j=0; j<kfc.size(); j++) {
                curDistance = chickenDistance(house.get(i).x, house.get(i).y, kfc.get(j).x, kfc.get(j).y);  // 치킨거리 계산
                allDistance[i][j] = curDistance;
            }
        }

        shutDown(0, 0);
        System.out.println(ans);
    }

    public static void shutDown(int count, int start) { // 시간 초과 방지를 위해 start 인덱스 추가
        if(count == closeNum) { // 폐업하고 M개만 남았을 때
            int sumCD = 0;  // 치킨거리의 합
            int tmpCD;
            for(int i=0; i<house.size(); i++) {
                tmpCD = Integer.MAX_VALUE;
                for(int j=0; j<kfc.size(); j++) {
                    if(closed[j])
                        continue;
                    tmpCD = Math.min(tmpCD, allDistance[i][j]);
                }
                sumCD += tmpCD;
            }
            ans = Math.min(ans, sumCD);
        }

        for(int i=start; i<kfc.size(); i++) {       // start 인덱스부터 시작(이전 위치는 조회 했으므로)
            if(!closed[i]) {
                closed[i] = true;
                shutDown(count+1, i+1);
                closed[i] = false;  // 백트래킹
            }
        }
    }

    // 치킨거리 계산
    public static int chickenDistance(int ax, int ay, int bx, int by) {
        int x = Math.abs(ax-bx);
        int y = Math.abs(ay-by);
        return x+y;
    }
}
