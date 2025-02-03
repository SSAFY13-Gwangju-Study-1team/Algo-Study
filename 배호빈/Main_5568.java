import java.util.*;
import java.io.*;
public class Main_5568 {
    //백트래킹 함수에서 편하게 사용하기 위해 static으로 만들었습니다.
    private static String[] arr;
    private static boolean[] visited;
    private static int N;
    private static int K;
    private static Set<String>set = new HashSet<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

         arr = new String[N];
         visited = new boolean[N];
        for(int i = 0; i < N; i++){
            arr[i] = br.readLine();
        }
        back(0);
        System.out.println(set.size());
    }
    private static void back( int depth){
        if(depth == K){
            //중복된 숫자는있으면 안되니 set을 사용해줍니다.
            set.add(sb.toString());
            return;
        }
        for(int i = 0; i < arr.length; i++){
            if (!visited[i]) {
                //visited[i]가 false라면 아직 arr[i]의 숫자를 사용하지 않은 상태입니다.
                visited[i] = true; // 중복 선택을 방지하기 위해서 true로 변경
                sb.append(arr[i]); // arr[i]를 추가하여 숫자 조합을 만듭니다.
                back(depth + 1); // depth가 k가 될 때까지 재귀적 탐색을 합니다.
                sb.setLength(sb.length() - arr[i].length()); // append(arr[i])로 추가했던 숫자를 지워서 원래 상태로 만듭니다.
                visited[i] = false; // 다음 조합을 만들기 위해 다시 false로 만들어줍니다.
            }
        }
    }
}
