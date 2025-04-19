import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_5598_카이사르암호_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String s = br.readLine();  // 입력 받은 암호 문자열

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 알파벳 3칸 앞으로 이동 (A보다 작아지면 Z부터 다시)
            char decoded = (char) (ch - 3);
            if (decoded < 'A') {
                decoded += 26;
            }

            sb.append(decoded);
        }

        System.out.println(sb);
    }
}
