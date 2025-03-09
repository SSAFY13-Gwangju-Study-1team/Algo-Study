import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 *
 *  * 원래의 잘못된 풀이..
 *  * 모든 단어를 맵에 올려서 열을 중심으로 빨리 나오는(자리수가 큰) 알파벳에 일단 큰 수를 제공했다
 *  * -----------------------------------------------------------------------------------
 *  * gpt가 말하길...
 *  * 현재 코드에서는 단순히 세로로 읽으면서(왼쪽부터) 첫 등장하는 알파벳에 9부터 할당하는 방식이야.
 *  * 하지만 가장 중요한 자리수를 가진 알파벳이 제대로 높은 숫자를 받지 못하는 경우가 발생할 수 있어.
 *  * 자리수 가중치(weight)를 누적하는 과정이 빠져 있어서 최적의 숫자 배정이 되지 않는 문제가 있어! 🔥
 *  * -----------------------------------------------------------------------------------
 *  * 따라서 두번째 풀이를 따라해 보았다
 *
 * gpt의 도움으로 완성한 코드
 * hamp의 value의 정렬을 처음해봤는데 어려웠다
 * 가중치를 map에 기록하는데 자리수별만큼 준다 -> 1000, 100...
 * 
 */
public class Main_1339_단어수학2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= parseInt(br.readLine());
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(int i=0;i<n;i++){
            char[] input = br.readLine().toCharArray();
            for(int j=0;j<input.length;j++){
                if(!hmap.containsKey(input[j])) {
                    hmap.put(input[j], (int) Math.pow(10, input.length-j-1));
                }else{
                    hmap.put(input[j], hmap.get(input[j])+(int) Math.pow(10, input.length-j-1));
                }
            }
        }
        
        // map을 list keySet으로 만들어서 value로 내림차순 했다
        List<Character> keySet = new ArrayList<>(hmap.keySet());
        keySet.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return hmap.get(o2)-hmap.get(o1);
            }
        });

        // 가중치가 큰 수부터 큰 수를 할당한다
        int presentNum=9;
        int res = 0;
        for(char key : keySet){
            res += presentNum*hmap.get(key);
            presentNum--;
        }
        System.out.println(res);


    }
}
