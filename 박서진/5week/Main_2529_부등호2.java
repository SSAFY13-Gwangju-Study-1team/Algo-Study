import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리: 27284KB	시간: 448ms
 * 주의! 첫째자리에 0이들어갈 수도 있음
 * 순열 백트래킹
 * 백트래킹 함수에서 바로 min max 값 계산하기
 *
 */
public class Main_2529_부등호2 {
    static int k;
    static char[] A;
    static int[] nums;
    static boolean[] isVisited;
    static long min_hap = Long.MAX_VALUE; // 숫자가 매우 커질수도 있으니(10자리수) long으로 설정
    static long max_hap = Long.MIN_VALUE;

    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k =parseInt(br.readLine());
        A = new char[k]; // 길이가 k의 2배 +1
        isVisited = new boolean[10]; // 0~9까지의 방문 처리
        nums = new int[k+1];
        
        // A에 부등호 앞 뒤로 자리를 만들어 두어 후에 숫자를 삽입
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            A[i] = st.nextToken().charAt(0);
        }

        // 백트래킹으로 순열 구하기
        backtrack(0);
        // max 합 출력
        System.out.println(max_hap);
        // min 합 출력
        if(Long.toString(min_hap).length()!=k+1){
            System.out.println("0"+min_hap);
        }else {
            System.out.println(min_hap);
        }

    }

    // 순열을 만들기 위한 함수 backtrack
    public static void backtrack(int cnt){
        // 종료 조건
        if(cnt == k+1){ // k 자리수 보다 커지면 return 
            boolean flag = check(); // nums들의 순서와 부등호의 순서가 일치하는지를 확인
            if(flag) {
                String numsToString=""; // 숫자를 스트링으로 바꿔서 다시 long으로 바꿔준다
                for(int i=0;i<nums.length;i++)
                    numsToString+=nums[i];
                min_hap = Math.min(min_hap, Long.parseLong(numsToString));
                max_hap = Math.max(max_hap, Long.parseLong(numsToString));
            }
            return;
        }

        for(int i=0;i<=9;i++){ // 0~9까지 숫자를 뽑아서 순열을 만드는 작업
            if(isVisited[i]) continue;
            isVisited[i] = true;
            nums[cnt] = i;
            backtrack(cnt+1);
            isVisited[i] = false;
        }
    }

    public static boolean check(){
        // 슬라이딩 윈도우가 3칸으로 만들어지고 2칸씩 움직인다
        int i =0;
        boolean flag =true;
        while(i<A.length){
            int n1 = nums[i];
            int n2 = nums[i+1];
            char oper = A[i];
            i++;
            if(oper == '<'){
                if(n1<n2) continue;
                else {
                    flag=false;
                    break;
                }
            }else{
                if(n1>n2) continue;
                else {
                    flag=false;
                    break;
                }
            }
        }

        return flag;
    }
}
