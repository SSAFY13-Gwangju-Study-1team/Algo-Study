package PACKAGE_NAME;

import java.util.Scanner;

public class Main_10870_피보나치수5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long res = fib(n);
        System.out.println(res);
    }

    static long fib(int n){
        long res;
        if(n<2){
            return n;
        }else{
            res = fib(n-1)+fib(n-2);
        }
        return res;
    }
}
