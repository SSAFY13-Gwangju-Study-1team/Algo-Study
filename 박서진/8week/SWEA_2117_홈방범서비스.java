import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_홈방범서비스 {
    static int n, m, k;
    static int[][] map, homeLoc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = n+2;
            map = new int[n][n];
            homeLoc = new int[n*n][2];
            int homeCnt=0;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 집으로 저장된 곳을 리스트로 관리한다
                    if(map[i][j]==1){
                        homeLoc[homeCnt][0] = i;
                        homeLoc[homeCnt][1] = j;
                        homeCnt++;
                    }
                }
            }

            //<--구현-->//
            // 전략 1: k의 범위는 1~n+1(한변이 n인 사각형을 감싸는 k의 최대 값은 n+1)이다
            // 저장된 집들의 배열을 이용해서 마름모 중심과 거기 계산 후 개수 세기
            // 각 위치의 이익을 구하기(집을 통해 얻는 수익(m*집의 수) - 운영비용(k*k + (k-1)*(k-1))
            int ans=0; //손해를 보지 않고 서비스 가능한 최대 집의 수
            while(k-->0) { //k를 n+1부터 1줄여가면서 계산(1까지)
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        int cnt = 0;
                        for(int h=0;h<homeCnt;h++){
                            // 집의 좌표
                            int hr = homeLoc[h][0];
                            int hc = homeLoc[h][1];
                            
                            // 집과 마름모 중심 사이 거리 계산하기
                            int dist = Math.abs(hr-r) + Math.abs(hc-c);
                            if(dist<k) cnt++;
                        }
                        // 집의 개수를 다 구했으면 이익 구하기
                        // 실이 없다면(0도 가능) ans를 update 하기 -> 최대 집의 개수
                        int cost = m*cnt - (k*k + (k-1)*(k-1));
                        if(cost>=0){
                            ans = Math.max(cnt, ans);
                        }
                    }
                }
            }
            sb.append("#"+t+" "+ans+"\n");
        }
        System.out.println(sb);
    }

}
