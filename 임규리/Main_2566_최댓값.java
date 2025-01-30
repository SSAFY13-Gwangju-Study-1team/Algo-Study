/**
 * 더 좋은 방법이 있을 것 같은데 생각해내지 못했습니다 ㅜ.ㅜ
 * 배열과 List 중 고민하다 List를 사용하였습니다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_2566_최댓값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String[] current = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                list.add(Integer.parseInt(current[j]));
            }
        }

        int max = Collections.max(list);
        int index = list.indexOf(max);
        int x = index / 9 + 1;
        int y = index % 9 + 1;

        System.out.println(max);
        System.out.println(x + " " + y);

        br.close();
    }
}
