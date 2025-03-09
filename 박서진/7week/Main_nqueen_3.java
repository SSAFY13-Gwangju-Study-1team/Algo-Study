import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_nqueen_3 {
    static int n, res;
    static boolean[] col, slash, bslash;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        col = new boolean[n+1];
        slash = new boolean[2*n+1]; //합이 같음
        bslash = new boolean[2*n]; // 차가 같음
        res = 0;
        backtrack(1);
        System.out.println(res);
    }
    public static void backtrack(int row){
        // 기저 조건
        // row가 n+1에 도달하면 끝이 납니다.
        if(row>n){
            res++;
            return;
        }
        
        // 행마다 말을 하나씩 놀 수 있으며 col만큼 반복합니다
        // 말을 놓기 전 같은 열, 대각선, 백대각선에 있는지 체크합니다.
        for(int c=1;c<=n;c++){
            if(Available(row, c)){
                col[c] = slash[row+c] = bslash[c-row+n] = true;
                backtrack(row+1);
                col[c] = slash[row+c] = bslash[c-row+n] = false;
            }
        }
    }
    public static boolean Available(int rowNo, int colNo){
        if(!col[colNo] && !slash[colNo+rowNo] && !bslash[colNo-rowNo+n]){
                return true;
        }
        return false;
    }
}
