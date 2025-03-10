import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 잘못된 풀이..
 * 모든 단어를 맵에 올려서 열을 중심으로 빨리 나오는(자리수가 큰) 알파벳에 일단 큰 수를 제공했다
 * -----------------------------------------------------------------------------------
 * gpt가 말하길...
 * 현재 코드에서는 단순히 세로로 읽으면서(왼쪽부터) 첫 등장하는 알파벳에 9부터 할당하는 방식이야.
 * 하지만 가장 중요한 자리수를 가진 알파벳이 제대로 높은 숫자를 받지 못하는 경우가 발생할 수 있어.
 * 자리수 가중치(weight)를 누적하는 과정이 빠져 있어서 최적의 숫자 배정이 되지 않는 문제가 있어! 🔥
 * -----------------------------------------------------------------------------------
 * 따라서 두번째 풀이를 따라해 보았다
 */
public class Main_1339_단어수학 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= parseInt(br.readLine());
        char[][] words = new char[n][8];
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(int i=0;i<n;i++){
            char[] input = br.readLine().toCharArray();
            for(int j = 8-input.length;j<8;j++){
                words[i][j] = input[j+input.length-8];
            }
        }

        // 세로 탐색
        int presentNum=9;
        int res = 0;
        for(int c=0;c<8;c++){
            for(int r=0;r<n;r++){
                if(words[r][c]<'A' || words[r][c]>'J') continue; // null이면 pass
                if(!hmap.containsKey(words[r][c])){
                    hmap.put(words[r][c], presentNum);
                    presentNum--;
                }
                int changeToNum = hmap.get(words[r][c]);
                res += changeToNum*Math.pow(10, 7-c);
            }
        }
        System.out.println(res);


    }
}
