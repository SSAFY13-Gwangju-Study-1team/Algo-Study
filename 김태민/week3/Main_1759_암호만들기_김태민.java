package argoStudy;

import java.util.*;
import java.io.*;


public class Main {
    static int l, c;
    static String[] arr;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());


        arr = new String[c];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<c; i++){
            arr[i] = st.nextToken();
        }

        // 입력 받은 배열을 오름차순으로 정렬
        // 이러면 조합되는 문자열도 자연스럽게 오름차순으로 만들어짐

        Arrays.sort(arr);

        backtrack(0, "",0,0,0);
        System.out.println(sb);

    }

    /**
     *
     * @param start start: 중복을 피하기 위해 0번지가 끝났다면 1번지는 0번지를 접근 못하게 하는 용도로 사용
     * @param s     s: 문자열을 저장
     * @param cnt   cnt: 반복된 횟수
     * @param a_cnt a_cnt: 모음의 갯수
     * @param b_cnt b_cnt: 자음의 갯수
     */

    public static void backtrack(int start, String s, int cnt, int a_cnt, int b_cnt){
        if(cnt == l){  // 종료 조건
            if(a_cnt>=1&&b_cnt>=2){  // 모음이 1개이상, 자음이 2개 이상이면 종료
                sb.append(s).append("\n");
                return;
            }
        }

        for(int i = start; i<c; i++){
            // 모음일 경우: a_cnt+1
            if(arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")){
                backtrack(i+1,s+arr[i],cnt+1, a_cnt+1,b_cnt);
            } else {  // 자음일 경우: b_cnt+1
                backtrack(i+1,s+arr[i],cnt+1, a_cnt, b_cnt+1);
            }

        }
    }

}






