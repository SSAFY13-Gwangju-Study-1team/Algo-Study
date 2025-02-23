import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 시간 424ms
 * 슬라이딩 윈도우 문제
 * 문자열 acgt를 관리하는 방법이 hmap이 떠올라서 사용해서 풀었습니다
 */
public class Main_12891_DNA비밀번호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = parseInt(st.nextToken());
        int p = parseInt(st.nextToken());
        char[] password = br.readLine().toCharArray();
        int res =0;
        int[] alphaCnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        HashMap<Character, Integer> hmap = new HashMap<>();
        hmap.put('A',0); //  처음에 acgt의 hmap을 0으로 초기화 해줍니다
        hmap.put('C',0);
        hmap.put('G',0);
        hmap.put('T',0);
        // 첫번째 자리를 초기화 한다
        for(int i=0;i<p;i++){
            hmap.put(password[i], hmap.get(password[i])+1);
        }
//        System.out.println(hmap);
        // 첫번째 요소들의 값을 확인한다
        if(check(hmap,alphaCnt)){
            res++;
        }

        // 다음 슬라이딩 윈도우에서 추가하고 제거해야할 원소들을 뽑는다
        for(int i=0;i<s-p;i++){
            char remove = password[i]; // 빼야할 원소
            char plus = password[i+p]; // 추가할 원소
            hmap.put(remove, hmap.get(remove)-1);
            hmap.put(plus, hmap.get(plus)+1);
            if(check(hmap, alphaCnt)) res++;
        }
        System.out.println(res);
        

    }

    // 각각의 원소가 지정된 문자열보다 작으면 바로 false로 리턴하여 걸러줍니다.
    private static boolean check(HashMap<Character, Integer> hmap, int[] alphaCnt) {
        if(alphaCnt[0] > hmap.get('A')) return false;
        if(alphaCnt[1] > hmap.get('C')) return false;
        if(alphaCnt[2] > hmap.get('G')) return false;
        if(alphaCnt[3] > hmap.get('T')) return false;
        return true;
    }
}
