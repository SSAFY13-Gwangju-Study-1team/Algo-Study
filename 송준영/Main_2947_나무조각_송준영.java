import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2947_나무조각_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] arr = new int[5];

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 버블 정렬 방식으로 인접한 두 숫자를 비교하며 정렬되었는지 확인
        while (true) {
            boolean isSorted = true;

            for (int i = 0; i < 4; i++) {
                if (arr[i] > arr[i + 1]) {
                    // swap
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;

                    // 결과 출력
                    for (int j = 0; j < 5; j++) {
                        sb.append(arr[j]).append(" ");
                    }
                    sb.append("\n");

                    isSorted = false;
                }
            }

            // 정렬 완료되었으면 종료
            if (isSorted) break;
        }

        // 최종 결과 출력
        System.out.print(sb);
    }
}
