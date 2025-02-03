import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_10158_개미2 {
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

        int res_x = (x+t)%(w*2);
        int res_y = (y+t)%(h*2);
        
        // 감소하는 경우 값이 넘치는 경우 발생
        // w에서 해당 부분 만큼 차감해주기
        if (res_x>w){
            res_x = 2*w-res_x;
        }

        if (res_y>h){
            res_y = 2*h-res_y;
        }
        sb.append(res_x).append(" ").append(res_y);
        System.out.println(sb);
    }
}
