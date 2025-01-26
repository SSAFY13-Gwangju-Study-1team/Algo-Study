import java.io.*;
import java.util.*;

public class Main_17608_막대기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] polls = new int[n];
        for(int i=0;i<n;i++){
            polls[i] = sc.nextInt();
        }
        int maxi = polls[n-1];
        int res = 1;
        for(int i =n-1; i>-1 ;i--){
            if (polls[i]>maxi){
                maxi=polls[i];
                res++;
            }
        }
        System.out.println(res);
    }
}
