import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

/**
 * 수업시간에 풀었던 연산자 끼워넣기랑 똑같은 문제입니다
 * 중복조합을 사용하지 않고 모든 경우를 완탐할 경우 시간 초과가 나오니 꼭  중복조합을 사용해서 풀도록 합니다
 */
public class Main_14888_연산자끼워넣기 {
    static int n;
    static int[] numbers;
    static int[] opers; // 순서대로 +, -, *, /
    static int maxVal = Integer.MIN_VALUE;
    static int minVal = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        numbers = new int[n];
        opers = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            numbers[i] = parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            opers[i] = parseInt(st.nextToken());
        }
        
        // 중복 순열을 통해서 연산자의 계산를 순열로 만들고 몇개인지 계산하여 oper과 비교한다
        // 만약 범위 안에 들어오면 +,-,*,/의 다음 연산자를 선택하고 계산한 값을 갱신한다
        perm(0, 0, 0,0 ,0, numbers[0]);
        System.out.println(maxVal);
        System.out.println(minVal);
    }
    public static void perm(int depth, int plus, int minus, int mul, int div, int res){
        //기저 조건 depth가 opersnum이 되면 순열 완성
        if(depth==n-1){
            maxVal = Math.max(maxVal, res);
            minVal = Math.min(minVal, res);
            return;
        }
        // + 연산
        if(plus<opers[0]){ // 쓸 수 있는 +연산자 수보다 작으면 다음에 + 넣을 수 있음
            perm(depth+1, plus+1, minus,mul,div,res+numbers[depth+1]);
        }
        // - 연산
        if(minus<opers[1]){ // 쓸 수 있는 -연산자 수보다 작으면 다음에 + 넣을 수 있음
            perm(depth+1, plus, minus+1,mul,div,res-numbers[depth+1]);
        }
        // * 연산
        if(mul<opers[2]){ // 쓸 수 있는 *연산자 수보다 작으면 다음에 + 넣을 수 있음
            perm(depth+1, plus, minus,mul+1,div,res*numbers[depth+1]);
        }
        // / 연산
        if(div<opers[3]){ // 쓸 수 있는 /연산자 수보다 작으면 다음에 + 넣을 수 있음
            perm(depth+1, plus, minus,mul,div+1,res/numbers[depth+1]);
        }
    }
}
