/* 메모리 14208kb, 시간 104ms
 * 문제 풀이 아이디어: 양수인 수와 0과 음수인 수 두 가지 배열로 나눈다. -> 음수 배열 나누는 것은 해빈누나의 팁
 * 양수인 배열은 내림차순, 음수인 수는 오름차순으로 정렬한다.
 * 앞에서부터 2개씩 묶고 나머지는 더한다.
 * 체감 난이도: 6/10
 */

import java.io.*;
import java.util.*;

public class Main_1744_수_묶기_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }
    
    /* ----- 입력 ----- */
    static int N;
    static int[] nums;
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 수열의 크기

        nums = new int[N];  // 수열 배열
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        int ans = Integer.MIN_VALUE;    // 답을 저장하는 변수
        
        Arrays.sort(nums);
        for(int i=0; i<N/2; i++) {  // 역순 정렬
            int temp = nums[i];
            nums[i] =  nums[N-1-i];
            nums[N-1-i] = temp;
        }

        boolean checkMinus = false; // 수열에 음수가 있는지 확인하는 변수
        int minusIndex = 0;         // 음수가 있다면 음수가 시작하는 인덱스 저장
        
        boolean checkZero = false;  // 수열에 0이 있는지 확인하는 변수
        int zeroIndex = 0;          // 0이 있다면 0의 인덱스 저장

        for(int i=0; i<N; i++) {
            if(nums[i] == 0) {      // 0 찾기
                checkZero = true;
                zeroIndex = i;
            }
            
            if(nums[i] < 0) {       // 음수 찾기
                minusIndex = i;
                checkMinus = true;
                break;
            }
        }

        if(checkMinus) {    // 음수가 있는 경우
            int[] posNums;
            int[] negNums;

            if(checkZero) { // 0도 있을 때
                posNums = Arrays.copyOfRange(nums, 0, zeroIndex);     // 양수 배열
                negNums = Arrays.copyOfRange(nums, zeroIndex, N);    // 음수 배열(0 포함)
            } else {    // 0이 없을 때
                posNums = Arrays.copyOfRange(nums, 0, minusIndex);     // 양수 배열
                negNums = Arrays.copyOfRange(nums, minusIndex, N);     // 음수 배열
            }
            Arrays.sort(negNums);   // 음수는 정방향으로 재정렬

            sb.append(calculate(posNums) + calculate(negNums));
        } else {
            sb.append(calculate(nums));
        }
    }
    
    // 2개 씩 묶을 때 가장 큰 값을 찾는 함수
    static int calculate(int[] arr) {
        int max = Integer.MIN_VALUE;
        
        for(int i=0; i <= arr.length/2; i++) {  // i = 묶는 수
            int sum = 0;
            for(int j=0; j<i*2; j+=2) {
                sum += arr[j] * arr[j+1];
            }

            for(int j=2*i; j<arr.length; j++) {
                sum += arr[j];
            }

            max = Math.max(max, sum);
        }
        return max;
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}