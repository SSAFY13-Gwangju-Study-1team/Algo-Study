import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_13251_조약돌꺼내기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int m = parseInt(br.readLine());
        int[] stones = new int[m];
        String[] line = br.readLine().split(" ");
        int total = 0;

        for(int i=0;i<m;i++) {
            stones[i] = parseInt(line[i]);
            total += stones[i];
        }

        int k = parseInt(br.readLine());

        double sum_stone=0;

        for(int i=0;i<m;i++) {
            double i_stone = 1;
            for(int l=0;l<k;l++){
                i_stone*=(double) (stones[i]-l) / (total-l);
            }
            sum_stone+= i_stone; // m만큼 곱해야함
        }

        sb.append(sum_stone);
        System.out.println(sb);
    }

}
