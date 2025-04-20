import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Main_2851_슈퍼_마리오_임규리 {

    static class Node {
        int sum;    // 누적합
        int diff;   // 100과의 차이

        public Node (int sum, int diff) {
            this.sum = sum;
            this.diff = Math.abs(diff); // 100과의 차이의 절대값
        }
    }

    static int[] arr;   // 수열
    static Node[] prefix;    // 누적합 배열
    static Node result;  // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10];
        prefix = new Node[11];

        for (int i = 0; i < 10; i++) {
            arr[i] = parseInt(br.readLine());
        }

        prefix[0] = new Node(0, 100);
        for (int i = 1; i <= 10; i++) {
            int sum = prefix[i - 1].sum + arr[i - 1];
            int diff = 100 - sum;
            prefix[i] = new Node(sum, diff);
        }

        result = prefix[0];
        for (int i = 1; i <= 10; i++) {
            if (prefix[i].diff <= result.diff) {
                result = prefix[i];
            }
        }

        System.out.println(result.sum);
    }
}
