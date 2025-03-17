import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2630_색종이만들기 {
    static int n, white, blue;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        map = new int[n][n];
        white = blue = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }

        divideConquer(0,0,n);
        System.out.println(white);
        System.out.println(blue);


    }

    public static void divideConquer(int r, int c, int n){
        // 전체의 색종이가 같은 색인지 본다
        boolean same = true;
        OUT:
        for(int i=r;i<r+n;i++){
            for(int j=c;j<c+n;j++){
                if (map[r][c] != map[i][j]){
                    same = false;
                    break OUT;
                }
            }
        }

        // 만약 같다면
        if(same){
            if(map[r][c]==1) blue++;
            else white++;
            return;
        }else{
            // 4분할을 하고 conquer하러 간다
            divideConquer(r,c,n/2); // 1사분면
            divideConquer(r,c+n/2,n/2); // 2사분면
            divideConquer(r+n/2,c,n/2); // 3사분면
            divideConquer(r+n/2,c+n/2,n/2); // 4사분면
        }

    }
}
