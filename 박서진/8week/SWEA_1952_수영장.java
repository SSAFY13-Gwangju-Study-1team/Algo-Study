import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_1952_수영장 {
    static int minPrice = Integer.MAX_VALUE;
    static int[] price;
    static int[] plan;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t = 1;t<=tc;t++){
            price = new int[4]; //1일권 1달권 3달권 1년권
            plan = new int[13];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<4;i++){
                price[i] = parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=12;i++){
                plan[i] = parseInt(st.nextToken());
            }
            minPrice = price[3];
            backtrack(1, 0);
            sb.append("#" + t + " "+minPrice).append("\n");
        }
        System.out.println(sb);
    }

    private static void backtrack(int month, int totalPrice) {
        if(totalPrice>minPrice) return;
        if(month >12){
            minPrice = totalPrice;
            return;
        }
        //만약 0의 값을 갔는다면 그냥 넘어감
        if(plan[month]==0){
            backtrack(month+1, totalPrice);
        }else{
            // 1일권
            backtrack(month+1, totalPrice+price[0]*plan[month]);
            // 1달권
            backtrack(month+1, totalPrice+price[1]);
            // 3달권
            backtrack(month + 3, totalPrice + price[2]);
        }
    }
}
