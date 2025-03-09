import java.util.*;
import java.io.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;
/**
 * 실행시간: 243 ms, 메모리: 31,376 kb
 * core의 개수가 적으니 backtrack으로 풀 수 있다
 * 백트랙으로 위, 아래, 오른쪽, 왼쪽, 선택 안함
 * 이렇게 5가지의 조합을 구할 수 있는데 n이 적으니 가능한것!!
 * 힘들지만 풀었따..
 *
 */
public class SWEA_1767_프로세서연결하기2 {
    static int n;
    static int[][] map;
    static boolean[][] isVisited;
    static ArrayList<int[]> processor;
    static int minLength = Integer.MAX_VALUE;
    static int coreCnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st ;
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++) {
            n = parseInt(br.readLine());
            map = new int[n][n];
            isVisited = new boolean[n][n];
            processor = new ArrayList<>();
            minLength = Integer.MAX_VALUE;
            coreCnt = 0;

            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++) {
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            // 리스트로 프로세스를 관리하고 백트랙시 넘겨줌
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j]==1 && i!=0 && i!=n-1 && j!=0 && j!=n-1)
                        processor.add(new int[]{i, j});
                }
            }


            backtrack(0, 0, 0);
            sb.append("#"+t+" "+minLength).append("\n");
        }
        System.out.println(sb);
    }

    public static void backtrack(int depth, int cnt, int sum) {
        // 기저 조건
        // 프로세스가 모두 연결되었다면
        if(depth==processor.size()) {
            if (coreCnt < cnt) {
                coreCnt = cnt;
                minLength = sum;
            } else if (coreCnt == cnt) {
                minLength = Math.min(sum, minLength);
            }

            return;
        }

        int r = processor.get(depth)[0];
        int originR =r;
        int c = processor.get(depth)[1];
        int originC =c;
        // 조합 느낌으로 백트래킹을 시도한다
        // 위로 연결할 때
        // 만약 경계에 붙어있으면 바로 연결
        // 다른 프로세서가 있거나, 다른 전선이 있으면 백트랙(가면 안됨)
        boolean pos = isConnected(0, r-1, c); // 위쪽으로 연결할 수 있는지 확인
        if(pos){
            int lineCnt=0;
            r--; // 이걸 한번 뺴줘야 함 왜냐하면 연결선이 의미없이 1개가 추가된다.. 이것땜에 시간 다 잡아먹음 ㄷㄷ
            while(r>=0){
                isVisited[r][c] = true;
                r--;
                lineCnt++;
            }
            backtrack(depth+1, cnt+1, sum+lineCnt);
            r = originR;
            while(r >= 0){
                isVisited[r][c] = false;
                r--;
            }
            r = originR;
        }
        // 아래로 연결할 때
        pos = isConnected(1, r+1, c); // 아래쪽으로 연결할 수 있는지 확인
        if(pos){
            int lineCnt=0;
            r++;
            while(r<n){
                isVisited[r][c] = true;
                r++;
                lineCnt++;
            }
            backtrack(depth+1, cnt+1, sum+lineCnt);
            r = originR;
            while(r < n){
                isVisited[r][c] = false;
                r++;
            }
            r = originR;
        }

        // 왼쪽으로 연결할 때
        pos = isConnected(2, r, c-1); // 왼쪽으로 연결할 수 있는지 확인
        if(pos){
            int lineCnt=0;
            c--;
            while(c>=0){
                isVisited[r][c] = true;
                c--;
                lineCnt++;
            }
            backtrack(depth+1, cnt+1,sum+lineCnt);
            c = originC;
            while(c >= 0){
                isVisited[r][c] = false;
                c--;
            }
            c = originC;
        }
        // 오른쪽으로 연결할 때
        pos = isConnected(3, r, c+1); // 오른쪽으로 연결할 수 있는지 확인
        if(pos){
            int lineCnt=0;
            c++;
            while(c < n){
                isVisited[r][c] = true;
                c++;
                lineCnt++;
            }
            backtrack(depth+1, cnt+1,sum+lineCnt);
            c = originC;
            while(c < n){
                isVisited[r][c] = false;
                c++;
            }
        }
        // 연결이 안되었을 때
        backtrack(depth+1, cnt, sum);

    }

    // 방향과 위치를 넣으면 그 라인에 프로세서나 선이 있는지 확인하는 함수
    // 0, 1, 2, 3 => 상, 하, 좌, 우
    public static boolean isConnected(int dir, int r, int c){
        switch (dir){
            case 0:
                while(r>=0){
                    if(map[r][c]==1 || isVisited[r][c]){
                        return false;
                    }
                    r--;
                }
                break;
            case 1:
                while(r<n){
                    if(map[r][c]==1 || isVisited[r][c]){
                        return false;
                    }
                    r++;
                }
                break;
            case 2:
                while(c>=0){
                    if(map[r][c]==1 || isVisited[r][c]){
                        return false;
                    }
                    c--;
                }
                break;
            case 3:
                while(c<n){
                    if(map[r][c]==1 || isVisited[r][c]){
                        return false;
                    }
                    c++;
                }
                break;
        }
        return true;
    }

}