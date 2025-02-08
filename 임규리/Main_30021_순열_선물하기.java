/**
 * ChatGPT 선생님과 함께 풀이...
 *
 * 에라토스테네스의 체 구현할 때 문제 발생
 * - 기존 : for (int i = 2; i <= Math.sqrt(maxSum); i++)
 * => 문제 발생 : 65%에서 실패, 부동소수점 오류로 추측? 아직까지 정확하지 않음
 * - 수정 : for (int i = 2; i * i <= maxSum; i++)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_30021_순열_선물하기 {
    static int N;
    static boolean[] isPrime;
    static boolean[] used;
    static List<Integer> result;
    static boolean found;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println("YES");
            System.out.println(N);
            return;
        }

        int maxSum = (N * (N + 1)) / 2;
        isPrime = new boolean[maxSum + 1];
        // 에라토스테네스의 체
        checkPrime(maxSum);

        used = new boolean[N + 1];
        result = new ArrayList<>();

        // 순열
        permutation(0, 0);

        if (found) {
            System.out.println("YES");

            StringBuilder sb = new StringBuilder();
            for (int n : result) {
                sb.append(n);
                sb.append(" ");
            }

            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }

    private static void checkPrime(int maxSum) {
        Arrays.fill(isPrime, true);

        isPrime[0] = false; // 0은 소수X
        isPrime[1] = false; // 1은 소수X

        // true -> 소수, false -> 소수X
        for (int i = 2; i * i <= maxSum; i++) {  // 2부터 maxSum의 제곱근까지 모든 수를 확인
            if (isPrime[i]) {   // 해당 수가 소수라면, 해당 수를 제외한 배수들을 모두 false 처리 (소수X)
                for (int j = i * i; j <= maxSum; j += i) {  // 이미 처리된 작은 배수들을 제외하고 효율적으로 소수 배수를 처리하기 위해 i*i부터 시작
                    isPrime[j] = false;
                }
            }
        }
    }

    private static void permutation(int depth, int sum) {
        if (depth == N) {
            found = true;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                int newSum = sum + i;

                if (!isPrime[newSum]) {  // 새로운 합이 소수가 아니라면
                    used[i] = true;
                    result.add(i);

                    permutation(depth + 1, newSum);

                    if (found) {    // 재귀하며 찾았다면 반복문 stop
                        return;
                    }

                    used[i] = false;
                    result.remove(result.size() - 1);
                }
            }
        }
    }
}
