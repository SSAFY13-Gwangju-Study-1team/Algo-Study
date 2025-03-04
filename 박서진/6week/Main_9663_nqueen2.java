import java.util.Arrays;
import java.util.Scanner;

/**
 * 백트래킹
 * 같은 열, 같은 대각선 처리를 리스트로 각각 구현하지 않고
 * 바로 계산하는 방법으로 구현하였습니다
 */
public class Main_9663_nqueen2 {
    static int n;
    static int[] used; // 방문 처리를 가지고 처리
    static int ans=0;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        used = new int[n+1]; // index: row값, value: col 값
        ans=0;
        backtrack(1);
        System.out.println(ans);
    }
    
    // 가지 치기
    // 같은 열에 있는지 체크하기
    // 같은 대각선 방향에 있는지 체크하기
    // 만약 조건이 이루어지지 않으면 다시 백트랙하기
    public static void backtrack(int row){
        // 기저 조건 - 행이 마지막 행보다 커지게 되었을 때
        if(row>n){
            ans++;
            return;
        }

        // 행렬을 리스트로 가지고 있어서 계속 비교하기
        for(int c=1;c<=n;c++){
            boolean flag=false;
            for(int uesdR=1; uesdR<row;uesdR++){
                // 열 확인 대각선 확인, 백대각선 확인
                if(used[uesdR]==c || (uesdR+used[uesdR])==c+row || uesdR-used[uesdR] == row-c)
                    flag = true; // 같은 값이 있다면
            }
            if (flag) continue;
            used[row] = c;
            backtrack(row+1);
            used[row] = 0;
        }
    }
    
}
