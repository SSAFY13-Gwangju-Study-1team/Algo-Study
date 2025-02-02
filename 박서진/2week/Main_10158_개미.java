import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_10158_개미 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // w h 입력 받기
        int w = parseInt(st.nextToken());
        int h = parseInt(st.nextToken());

        // 초기 x, y 입력 받기
        st = new StringTokenizer(br.readLine());
        int x = parseInt(st.nextToken());
        int y = parseInt(st.nextToken());

        // 시간 입력 받기
        int t = parseInt(br.readLine());

        // 방향 (초기값 = 오른쪽 위)
        int dx = 1, dy =1;

        for (int i=0;i<t;i++){
            x+=dx;
            y+=dy;
            // x 값이 경계를 만나면
            if (x==0 || x==w){
                dx=-dx; // 방향 반전
            }

            // y 값이 경계를 만나면
            if (y==0 || y==h){
                dy=-dy; // 방향 반전
            }
        }
        sb.append(x).append(" ").append(y);
        System.out.println(sb);
    }
}
