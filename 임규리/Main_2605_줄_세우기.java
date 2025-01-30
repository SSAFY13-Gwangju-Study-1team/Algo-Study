import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main_2605_줄_세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());  // 학생 수

        // 학생들이 뽑은 번호 파싱
        int[] location = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> list = new LinkedList<>();    // 연결 리스트 사용
        int cur = 1;        // 1번 학생부터 시작
        list.add(cur++);    // 1번 학생은 미리 add 후 cur++

        // cur - 1 = 현재 위치 (0부터 시작하기에 -1)
        // (cur - 1) - location[i] = location[i]만큼 앞으로 이동한 인덱스
        // 연결리스트의 add()는 인덱스 지정이 가능 => add(인덱스, 넣을 숫자)
        for (int i = 1; i < num; i++) {
            list.add((cur - 1) - location[i], cur++);
        }

        for (int n : list) {
            sb.append(n).append(" ");
        }

        System.out.println(sb);
    }
}