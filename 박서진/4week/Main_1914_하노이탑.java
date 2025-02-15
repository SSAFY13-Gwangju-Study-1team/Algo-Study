import java.io.*;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1914_하노이탑 {
    static BigInteger count = new BigInteger("2"); //값이 2인 BigInteger 객체를 만드는 코드
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n= parseInt(br.readLine());

        System.out.println(count.pow(n).subtract(new BigInteger("1"))); //2^n-1을 위한 식
        if(n<=20) {
            hanoi(n, 1, 2, 3); // 내가 원하는 n번째의 원판을 3으로 이동 시킨다
        }


    }
    public static void hanoi(int n, int start, int mid, int end){
        if(n==0) return; //종료조건

        hanoi(n-1, start, end, mid); // 목적 원판 위 원판을 mid로 이동 시킨다
        System.out.println(start+" "+end);; // 원판 이동 기록
        hanoi(n-1, mid, start, end); // 다시 옮겼던 원판을 목적 원판 위로 옮긴다.
    }
}
