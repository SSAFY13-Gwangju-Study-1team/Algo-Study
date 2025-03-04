import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 백트래킹 n queen 문제 
 * 강사님께서 알려주신 방법으로 세개의 리스트를 가지고 방문처리를 하였습니다
 */
public class Main_9663_nqueen {
    static int n;
    static boolean[] col, slash, backslash; // 방문 처리를 가지고 처리
    static int ans=0;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        col = new boolean[n+1];
        slash = new boolean[2*n+1]; //합이 같음
        backslash = new boolean[2*n];
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

        for(int c=1;c<=n;c++){
            if(!Available(row, c)) continue;
            col[c] = backslash[row-c+n] = slash[row+c] = true;
            backtrack(row+1);
            col[c] = backslash[row-c+n] = slash[row+c] = false;
        }
    }

    public static boolean Available(int rowNo, int colNo){
        // 아무것도 없다면 true
        // slash 같은 경우는 n만큼 보정
        if(!col[colNo] && !backslash[rowNo-colNo+n] && !slash[rowNo+colNo])
            return true;
        else return false;
    }
}
