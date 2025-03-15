import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 112 ms
 * 부분집합으로 암호를 만들었습니다. 집합 선택 전에 정렬을 통해 알파벳 순서를 정해놓고 각 자리의 알파벳이 들어갈지 말지를
 * 선택하는 방법입니다.
 */
public class Main_1759_암호만들기2 {
    static int n, l;
    static char[] pw;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Character> vowel =  new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u'));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        l = parseInt(st.nextToken());
        pw = new char[l];
        st= new StringTokenizer(br.readLine());
        for(int i=0;i<l;i++){
            pw[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(pw); //정렬을 시켜버리기
        combination(0, 0, 0, 0, new char[n]);
        System.out.println(sb);
    }

    // 알파벳이 순서대로 들어가니깐 조합으로 자리만 결정해주면 됩니다
    // 단 정렬이 되어있어야 합니다. 재귀를 돌면서 각 자리이 알파벳이 모음인지 자음인지 숫자를 체크하면서 마지막에 검사하는 로직입니다
    private static void combination(int depth, int index, int vowelCnt, int notvowelCnt, char[] arr) {
        if(depth==n){
            if(vowelCnt>=1 &&  notvowelCnt>=2){
                for(char i:arr){
                    sb.append(i);
                }
                sb.append("\n");
            }
            return;
        }

        if(index==l) return;

        arr[depth] = pw[index];
        if(vowel.contains(arr[depth])){
            // 모음일때
            //해당 자리수를 고르거나
            combination(depth+1, index+1, vowelCnt+1, notvowelCnt, arr);
        }else{
            // 자음일때
            //해당 자리수를 고르거나
            combination(depth+1, index+1, vowelCnt, notvowelCnt+1,arr);
        }

        // 고르지 않거나
        combination(depth, index+1, vowelCnt, notvowelCnt, arr);
    }
}
