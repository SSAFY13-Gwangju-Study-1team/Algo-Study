import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_5585_거스름돈 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int remain = 1000 - n;
        int cointCnt = 0;
        cointCnt += remain / 500;
        remain%=500;
        cointCnt += remain / 100;
        remain%=100;
        cointCnt += remain / 50;
        remain%=50;
        cointCnt += remain / 10;
        remain%=10;
        cointCnt += remain / 5;
        remain%=5;
        cointCnt += remain;

        System.out.println(cointCnt);
    }
}
