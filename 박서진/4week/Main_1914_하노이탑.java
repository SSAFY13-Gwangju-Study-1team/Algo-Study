import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1914_하노이탑 {
    static BigInteger count = new BigInteger("2");
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n= parseInt(br.readLine());

        System.out.println(count.pow(n).subtract(new BigInteger("1")));
        if(n<=20) {
            hanoi(n, 1, 2, 3);
        }


    }
    public static void hanoi(int n, int start, int mid, int end){
        if(n==0) return; //종료조건

        hanoi(n-1, start, end, mid);
        System.out.println(start+" "+end);; // 원판 이동 기록
        hanoi(n-1, mid, start, end);
    }
}
