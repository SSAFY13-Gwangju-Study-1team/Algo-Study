package algo_study;

import java.util.*;

public class Main_30021_순열선물하기 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 입력값 N을 읽음
        int[] result = new int[N]; // 결과 순서를 저장할 배열
        boolean[] used = new boolean[N + 1]; // 숫자가 사용되었는지 체크하는 배열

        if (!findNonPrimeSumOrder(N, result, used, 0, 0)) { // 순서를 찾을 수 없으면 "NO" 출력
            System.out.println("NO");
        } else {
            System.out.println("YES");
            for (int num : result) { // 찾은 순서를 출력
                System.out.print(num + " ");
            }
        }
    }
    
    private static boolean findNonPrimeSumOrder(int N, int[] result, boolean[] used, int sum, int index) {
        if (index == N) { // 모든 숫자를 배치했으면 성공
            return true;
        }
        
        for (int num = 1; num <= N; num++) { // 1부터 N까지 탐색
            if (!used[num] && !isPrime(sum + num)) { // 사용되지 않았고, 합이 소수가 아니면 추가
                result[index] = num;
                used[num] = true;
                
                if (findNonPrimeSumOrder(N, result, used, sum + num, index + 1)) {
                    return true;
                }
                
                used[num] = false; // 백트래킹
            }
        }
        return false; // 유효한 순서를 찾지 못함
    }
    
    private static boolean isPrime(int num) {
        if (num < 2) return false; // 2 미만의 숫자는 소수가 아님
        for (int i = 2; i * i <= num; i++) { // 2부터 루트 num까지 나누어 소수 판별
            if (num % i == 0) return false; // 나누어 떨어지면 소수가 아님
        }
        return true; // 소수인 경우 true 반환
    }
}