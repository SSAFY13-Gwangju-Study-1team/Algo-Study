/**
 * 첫 시도 => 실패
 * - 모음과 자음 리스트를 각각 분리해서 만든 후 모음의 개수를 1 ~ L-2개 뽑고, 나머지 개수만큼 자음 뽑기
 *   => accs와 같이 자음이 중복으로 나타나는 문제 발생
 * 재시도
 * - 모음과 자음을 하나의 배열에 넣고 전체 조합 후 조건에 맞는지 확인
 * 
 * 서버 문제로 결과 확인 X...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_1759_암호_만들기 {
    static char[] alphabet;
    static int L;
    static int C;
    static Set<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result = new HashSet<>();

        int[] temp = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        L = temp[0];
        C = temp[1];

        alphabet = br.readLine().replaceAll(" ", "")
                .toCharArray();
        Arrays.sort(alphabet);

        combination(0, new StringBuilder(), 0, 0);

        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        for (String s : sorted) {
            System.out.println(s);
        }
    }

    private static void combination(int start, StringBuilder cur, int vowelCount, int consonantCount) {
        if (cur.length() == L) {
            if (vowelCount >= 1 && consonantCount >= 2) {
                result.add(cur.toString());
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char c = alphabet[i];
            cur.append(c);

            if (isVowel(c)) {
                combination(i + 1, cur, vowelCount + 1, consonantCount);
            } else {
                combination(i + 1, cur, vowelCount, consonantCount + 1);
            }

            cur.deleteCharAt(cur.length() - 1);
        }
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
