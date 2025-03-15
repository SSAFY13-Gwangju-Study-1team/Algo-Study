import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 0과 1이 여러개 일수도 있다는 사실
 * 배열을 쭉 따라가면서 코드를 작성했는데 q를 이용해서 양수와 음수를 나누어 계산하는 풀이를 보았는데 그게 훨씬 간단해보였다
 */
public class Main_1744_수묶기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        int res = 0;
        int posCnt=0;
        int negCnt=0;
        int haveOne = 0;
        int haveZero = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0;i<n;i++){
            int tmp = parseInt(br.readLine());
            if(tmp==1) {
                res+=1;
                haveOne+=1;
                continue;
            }
            else if(tmp==0) {
                haveZero+=1;
                continue;
            }
            else if(tmp<0) {
                negCnt++;
            }
            else{
                posCnt++;
            }
            arr.add(tmp);
        }

        // 내림차순 정렬
        Collections.sort(arr, Collections.reverseOrder());
        // 짝지어서 더하기
        int index = 0;
        while (index<posCnt){
            if(index+1>=posCnt){ // pos가 홀수 일때 마지막 처리
                res += arr.get(index++);
            }else{
                res += arr.get(index)*arr.get(++index);
                index++;
            }
        }
        // 양수 끝
        if(haveOne>0) n -= haveOne;
        if(haveZero>0) n -= haveZero;

        // 음수 시작
        if(negCnt%2!=0){ // 음수가 홀수라면
            if(haveZero>0) { // 음수가 홀수인데 0이 있다면 곱해서 없애버림
                index++;
            }else{
                res += arr.get(index);
                index++;
            }
        }

        while(index<n-1){
            res += arr.get(index)*arr.get(++index);
            index++;
        }

        System.out.println(res);

    }
}
