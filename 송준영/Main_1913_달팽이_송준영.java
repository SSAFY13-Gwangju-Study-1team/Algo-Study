
import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_1913_달팽이_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        
        int N = parseInt(br.readLine()); // 배열의 크기
        int target = parseInt(br.readLine()); // 찾을 숫자

        int[][] snail = new int[N][N];
        
        // 방향 설정 (하, 우, 상, 좌)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int startX = 0, startY = 0; // 시작 위치
        int dir = 0; // 시작 방향 (하)
        int num = N * N; // 시작 숫자 (N²부터 시작)
        
        // 타겟 숫자의 위치를 저장할 변수
        int targetRow = 0, targetCol = 0;
        
        // 달팽이 배열 채우기
        while (num > 0) {
            snail[startX][startY] = num;
            
            // 타겟 숫자의 위치 저장
            if (num == target) {
                targetRow = startX + 1; // 1-indexed로 변환
                targetCol = startY + 1; // 1-indexed로 변환
            }
            
            // 다음 위치 계산
            int nx = startX + dx[dir];
            int ny = startY + dy[dir];
            
            // 방향 전환 조건: 배열 범위를 벗어나거나 이미 채워진 칸인 경우
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || snail[nx][ny] != 0) {
                dir = (dir + 1) % 4; // 방향 전환
                nx = startX + dx[dir];
                ny = startY + dy[dir];
            }
            
            startX = nx;
            startY = ny;
            num--;
        }
        
        // 결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(snail[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        if (target > 0) {
            sb.append(targetRow).append(" ").append(targetCol);
        }
        
        System.out.print(sb);
    }
}