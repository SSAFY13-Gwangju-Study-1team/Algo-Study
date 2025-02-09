
import java.util.*;
import java.io.*;
public class Main_10158 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
       //최대 좌표
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        //현재 좌표
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());
        //현재위치 p, q에 시간(t)를 더해준다.
        int a = (p + t) / w; // 가로 방향에서 얼마나 빈복 되었는지 구한다.
        int b = (q + t) / h; // 세로 방향에서 얼마나 반복 되었는지 구한다.
        // 반복된 횟수가 짝수라면 개미의 원래 방향이고 홀수라면 반대 방향이다.
        // 가로방향과 세로방향을 각각 구해준다.
        if(a % 2 == 0){
            a = (p + t) % w;
        }else{
            a = w - (p + t) % w;
        }

        if(b % 2 == 0){
            b = (q + t) % h;
        }else{
            b = h - (q + t) % h;
        }
        System.out.println(a + " " + b);
    }
}
