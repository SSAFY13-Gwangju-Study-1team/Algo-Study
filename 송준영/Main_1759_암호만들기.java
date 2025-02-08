import java.io.*;
import java.util.*;

public class Main_1759_암호만들기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int L, C;
    static String[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new String[C];
        visited = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr);
        makePw(0, L, -1, new StringBuilder());
        
        System.out.println(sb);
    }

    public static void makePw(int depth, int L, int step, StringBuilder tempSb) {
        if (depth == L) {
            char[] vowels = {'a', 'o', 'e', 'i', 'u'};
            int vowel = 0;
            boolean isVowel;
            
            // System.out.println("끝에 왔음! " + tempSb.toString());

            for (int i = 0; i < L; i++) {
                isVowel = false;
                for (char c : vowels) {
                    if (tempSb.charAt(i) == c) {
                        isVowel = true;
                        break;
                    }
                }
                if (isVowel) {
                    vowel++;
                }
            }

            if (vowel > 0 && (L - vowel) > 1) {
                // System.out.println(vowel + " " + (L - vowel));
                sb.append(tempSb).append("\n");
            }
            return;
        }

        for (int i = 0; i < C; i++) {
            if (!visited[i] && step < i) {
                visited[i] = true;
                tempSb.append(arr[i]);
                makePw(depth + 1, L, i, tempSb);
                tempSb.setLength(tempSb.length()-1);
                visited[i] = false;
            }
        }
    }
}
