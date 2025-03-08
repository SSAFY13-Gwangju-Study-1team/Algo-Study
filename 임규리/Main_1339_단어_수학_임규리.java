import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 메모리 : 11,644 KB
 * 시간 : 68 ms
 * ---------------------------------------------------------
 * 풀이 1 => 실패
 * <아이디어>
 * - 가장 길이가 긴 것부터 순서대로 처리
 * - 예를 들어 GCF, ACDEB 가 있으면 ACDEB부터 차례대로
 *   A = 9, C = 8, D = 7, E = 6, B = 5, G = 4, F = 3
 * -> 실패
 * ----------------------------------------------------------
 * 풀이 2 => 지쌤의 도움을 받음
 * <지쌤의 아이디어>
 * - 알파벳별로 가중치를 계산해서 가중치가 높은 애부터 9, 8, 7, ...
 * <구현>
 * - Alphabet이라는 클래스 사용 : 알파벳 c, 가중치 price, 숫자 value
 * 1. words를 받아 길이가 긴 순서대로 정렬 => 굳이 안 해도 되는데 시간이 조금 더 빨라짐
 * 2. 알파벳이 등장하는 위치에 따라 가중치 계산
 * 3. 가중치가 높은 순서대로 정렬
 * 4. 차례대로 9, 8, 7, ... value 할당
 * 5. 계산
 */
public class Main_1339_단어_수학_임규리 {

    static class Alphabet implements Comparable<Alphabet> {
        char c;
        int price;
        int value;

        public Alphabet(char c, int price, int value) {
            this.c = c;
            this.price = price;
            this.value = value;
        }

        @Override
        public int compareTo(Alphabet o) {
            return o.price - this.price;
        }

        @Override
        public String toString() {
            return "Alphabet{" +
                    "c=" + c +
                    ", price=" + price +
                    ", value=" + value +
                    '}';
        }
    }

    static int N;   // 단어 개수
    static String[] words;  // 단어 배열
    static Alphabet[] alpha;// 알파벳 배열
    static int idx; // 9부터 --
    static int max; // 단어 합 최대값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        alpha = new Alphabet[26];
        idx = 9;
        max = 0;

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        alpha = new Alphabet[26];
        for (int i = 0; i < 26; i++) {
            alpha[i] = new Alphabet((char)('A' + i), 0, 0);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                alpha[c - 'A'].price += (int) Math.pow(10, (words[i].length() - j - 1));
            }
        }

        Arrays.sort(alpha);

        for (int i = 0; i < 26; i++) {
            alpha[i].value = idx--;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                for (int k = 0; k < 26; k++) {
                    if (c == alpha[k].c) {
                        max += (int) Math.pow(10, (words[i].length() - j - 1)) * alpha[k].value;
                        break;
                    }
                }
            }
        }

        System.out.println(max);
    }
}
