import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_10158_개미3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w=parseInt(st.nextToken());
        int h=parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p=parseInt(st.nextToken());
        int q=parseInt(st.nextToken());
        int t = parseInt(br.readLine());

        int x = (p+t)%(2*w);
        if(x>w){
            x = 2*w-x;
        }
        int y = (q+t)%(2*h);
        if(y>h){
            y = 2*h-y;
        }

        System.out.println(x+ " "+y);

    }
}
