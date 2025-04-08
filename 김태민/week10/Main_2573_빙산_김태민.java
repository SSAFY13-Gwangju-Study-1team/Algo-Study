package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 설계
 *  - dfs로 덩어리가 2개로 분리 되는지 판단(함수 만들기)
 *  - 숫자가 적혀진 부분들만 탐색해서 0이 얼마나 있는지 저장
 *      그다음 그 숫자만큼 원본 배열의 수를 감소시키기
 *      그다음 다시 dfs로 덩어리 검색
 *  - 덩어리가 두덩어리 이상으로 분리되는 시간 검색
 *
 *
 */


public class Main_2573_빙산_김태민 {

    static int[][] map;
    static int[][] meltNum;
    static int n,m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean isCheck = false;
        int cnt = 0;
        while (true){
            int cCnt =  checkCnt();
            if(cCnt>=2) {
                isCheck =true;
                break;
            }
            if (cCnt==0) break;
            meltNum = new int[n][m];
            melt();
            cnt++;
        }
        if(isCheck){
            System.out.println(cnt);
        } else {
            System.out.println(0);
        }

    }

    static int checkCnt(){
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j]>0&&!visited[i][j]){
                    cnt++;
                    visited[i][j] = true;
                    dfs(i,j,visited);
                }
            }
        }
        return cnt;
    }

    static void dfs(int x, int y,boolean[][] visited){
        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0&&nx<n&&ny>=0&&ny<m&&map[nx][ny]>0&&!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx,ny,visited);
            }
        }
    }
    static void melt(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j]>0){
                    for(int k = 0; k<4; k++){
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if(nx>=0&&nx<n&&ny>=0&&ny<m&&map[nx][ny]==0){
                            meltNum[i][j]++; // 주위의 0의 갯수를 셈 나중에 녹임
                        }
                    }
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j]>0){
                    map[i][j] = map[i][j] - meltNum[i][j];
                    if(map[i][j]<0){
                        map[i][j] = 0;
                    }
                }
            }
        }

    }
}
