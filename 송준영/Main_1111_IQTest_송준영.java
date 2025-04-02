import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_1111_IQTest_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        
        // 수열의 길이가 1인 경우, 다음 수를 유일하게 결정할 수 없음
        if (N == 1) {
            System.out.println("A");
            return;
        }
        
        // 수열의 길이가 2인 경우, 무한히 많은 규칙이 가능함
        if (N == 2) {
            if (arr[0] == arr[1]) {
                // 같은 수가 반복되는 경우
                System.out.println(arr[0]);
            } else {
                // 다른 수가 있는 경우, 여러 규칙이 가능
                System.out.println("A");
            }
            return;
        }
        
        // 수열의 길이가 3 이상인 경우
        
        // a와 b를 계산
        int a, b;
        
        // 첫 번째 항이 두 번째 항과 같을 경우, a는 무엇이든 될 수 있음
        if (arr[0] == arr[1]) {
            a = 1; // a의 값은 중요하지 않음
            b = arr[1] - (arr[0] * a); // b = arr[1] - arr[0]
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - (arr[0] * a);
        }
        
        // 모든 수열에 대해 규칙이 일치하는지 확인
        boolean valid = true;
        for (int i = 1; i < N; i++) {
            if (arr[i] != arr[i-1] * a + b) {
                valid = false;
                break;
            }
        }
        
        if (valid) {
            // 규칙이 일치하면, 다음 수 계산
            int next = arr[N-1] * a + b;
            System.out.println(next);
        } else {
            // 규칙이 일치하지 않으면, 규칙을 찾을 수 없음
            System.out.println("B");
        }
    }
}