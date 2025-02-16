import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2841_외계인의기타연주 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int p = parseInt(st.nextToken());
        ArrayDeque<Integer>[] q = new ArrayDeque[n+1];
        for(int i=1;i<=n;i++){
            q[i] = new ArrayDeque<>();
        }

        int cnt =0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int m = parseInt(st.nextToken());
            int k = parseInt(st.nextToken());
            if(q[m].isEmpty()){
                q[m].push(k);
                cnt++;
            }else{
                if(q[m].peek()<k){
                    q[m].push(k);
                    cnt++;
                } else{ //q[m].peek()>=k의 경우
                    if(q[m].peek()==k)
                        continue;
                    while(!q[m].isEmpty() && q[m].peek()>k){
                        if(q[m].peek()==k)
                            break;
                        q[m].pop();
                        cnt++;
                    }
                    if(q[m].isEmpty() || q[m].peek()!=k) {
                        q[m].push(k);
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
