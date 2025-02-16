package PACKAGE_NAME;

import java.math.BigInteger;
import java.util.Scanner;

public class Main_11729_하노이탑이동순서 {
    static BigInteger big2 = new BigInteger("2");
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger res = big2.pow(n).subtract(new BigInteger("1"));
        System.out.println(res);
        hanoi(n, 1, 2, 3);
        System.out.println(sb);

    }

    /**
     *
     * @param n 원판의 개수
     * @param start 시작점
     * @param mid 중간 지점
     * @param end 끝지점 (목표 지점)
     */
    static void hanoi(int n, int start, int mid, int end){
        if(n>0){
            hanoi(n-1, start, end, mid);
            sb.append(start + " " + end+"\n");
            hanoi(n-1, mid, start, end);
        }
    }
}
