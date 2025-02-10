import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1759_암호만들기 {
    static String[] output;
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int l = parseInt(st.nextToken());
        int c = parseInt(st.nextToken());

        output = new String[l];

        // c 배열 입력 받기
        String[] c_arr = br.readLine().split(" ");
        // sort로 정렬 -> 순서는 신경쓰지 않아도 됨
        Arrays.sort(c_arr);

        // c 배열에서 l개의 조합 자음 2개 이상 모음 1개 이상 함수 제작
        combination(c_arr, 0, 0, c, l);

        System.out.println(sb);



    }
    static void combination(String[] c_arr, int start, int depth, int n, int r){
        int vowelCnt=0;
        int conCnt=0;
        // 조합이 완성된 경우
        if (depth==r){
            // 모음과 자음 개수 세기
            for(int i=0;i<r;i++){
                String ch = output[i];
                if (isVowel(ch)){
                    vowelCnt++;
                }else{
                    conCnt++;
                }
            }
            if(vowelCnt>=1 && conCnt>=2){
                for(int i=0;i<r;i++){
                    sb.append(output[i]);
                }
                sb.append(System.lineSeparator());
            }
            return;
        }
        for(int i=start;i<n;i++){
            output[depth] = c_arr[i]; // 현재 depth를 인덱스로 사용
            combination(c_arr, i+1, depth+1, n, r); // i + 1, depth + 1를 전달
        }
    }

    static boolean isVowel(String ch){
        return "aeiou".contains(ch);
    }
}
