package PACKAGE_NAME;
import java.util.Scanner;

/**
 * 팩토리얼 문제는 결과값이 크기때문에 꼭 리스트 값을 long으로 할당해 주어야 한다
 */
public class Main_27433_팩토리얼 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n==0){
            System.out.println(1);
            return;
        }

        long[] mul = new long[n+1];
        mul[1] = 1;
        for(int i=2;i<=n;i++){
            mul[i] = mul[i-1]*i;
        }

        System.out.println(mul[n]);
    }



}
