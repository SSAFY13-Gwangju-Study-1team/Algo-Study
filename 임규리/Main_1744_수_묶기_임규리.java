import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Main_1744_수_묶기_임규리 {

    static int N;       // 수열 길이
    static List<Integer> listMinus; // 0 이하 수열
    static List<Integer> listPlus;  // 1 이상 수열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        listMinus = new ArrayList<>();
        listPlus = new ArrayList<>();
        int count1 = 0; // 1의 개수 카운팅

        for (int i = 0; i < N; i++) {
            int num = parseInt(br.readLine());
            if (num <= 0) { // 0 이하면
                listMinus.add(num);
            } else {        // 1 이상이면
                listPlus.add(num);
            }

            if (num == 1) count1++;
        }

        Collections.sort(listMinus);    // listMinus는 가장 작은 수 * 그 다음 작은 수를 곱했을 때 결과가 크게 나오니 오름차순
        Collections.sort(listPlus, Collections.reverseOrder()); // listPlust는 가장 큰 수 * 그 다음 큰 수를 곱했을 때 결과가 크게 나오니 내림차순

        // 탐색할 길이 찾기 => 짝수개만 보도록
        int minusLength = listMinus.size() % 2 == 0 ? listMinus.size() : listMinus.size() - 1;
        for (int i = 0; i < minusLength - 1; i++) {
            listMinus.set(i + 1, listMinus.get(i) * listMinus.get(i + 1));  // 작은 수 * 그 다음 작은 수
            listMinus.set(i, 0);    // 리스트의 크기가 변하지 않도록 0을 넣어줌
            i++;    // 두 개를 처리했으니 i++
        }

        // 탐색할 길이 찾기 => 1을 제외한 나머지 중 짝수개만 보도록
        // 1을 제외하는 이유 : 1을 곱해봤자 값이 똑같음 => 1을 곱하지 않고 그냥 더하는 것이 최선
        int plusLength = (listPlus.size() - count1) % 2 == 0 ? listPlus.size() - count1 : listPlus.size() - count1 - 1;
        for (int i = 0; i < plusLength - 1; i++) {
            listPlus.set(i + 1, listPlus.get(i) * listPlus.get(i + 1)); // 큰 수 * 그 다음 큰 수
            listPlus.set(i, 0); // 리스트의 크기가 변하지 않도록 0을 넣어줌
            i++;    // 두 개를 처리했으니 i++
        }

        int sum = 0;
        for (int n : listMinus) {
            sum += n;
        }

        for (int n : listPlus) {
            sum += n;
        }

        System.out.println(sum);
    }
}
