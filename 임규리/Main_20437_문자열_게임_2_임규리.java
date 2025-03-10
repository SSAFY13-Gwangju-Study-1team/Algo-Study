package 스터디;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 메모리 : 46892 KB
 * 시간 : 316 ms
 * GPT 도움 받았습니다 ^_^...
 * - Map의 key를 알파벳으로, value를 등장 위치를 저장하는 리스트로
 * - 알파벳의 등장 위치가 K개 미만이면 무시
 * - 알파벳의 등장 위치가 K개 이상이면 최소길이와 최대길이 계산
 */
public class Main_20437_문자열_게임_2_임규리 {

    static int T;   // 문자열 게임 수
    static String W;   // 문자열
    static int K;   // 정수
    static Map<Character, List<Integer>> map;   // 알파벳 - 등장 위치 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            W = br.readLine();
            K = parseInt(br.readLine());
            map = new HashMap<>();

            for (int j = 0; j < W.length(); j++) {
                char c = W.charAt(j);
                List<Integer> temp = map.getOrDefault(c, new ArrayList<>());  // 기존 리스트 or 새 리스트
                temp.add(j);    // 현재 위치 추가
                map.put(c, temp); // 업데이트
            }

            int minLength = Integer.MAX_VALUE;
            int maxLength = Integer.MIN_VALUE;

            for (List<Integer> list : map.values()) {
                if (list.size() < K) continue;  // 해당 문자가 K개 미만이라면 무시

                for (int j = 0; j <= list.size() - K; j++) {    // e.g. list 사이즈가 5고, K가 3일 때 j는 0, 1, 2 가능 (2, 3, 4 => 길이 3)
                    int length = list.get(j + K - 1) - list.get(j) + 1;
                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }

            if (minLength == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minLength + " " + maxLength);
            }
        }
    }
}
