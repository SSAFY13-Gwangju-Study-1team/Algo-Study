import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_19539_사과나무 {
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken()); // 사과 나무 개수
        trees = new int[n];
        st= new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            trees[i] = parseInt(st.nextToken());
        }
        Arrays.sort(trees);
        int total = 0;
        for(int i:trees) total+=i;
        if(total%3!=0) {
            System.out.println("YES");
            return;
        }
        checkTree(n, 0);
    }
    public static void checkTree(int n, int i) {
        if(i==n-1) {
            if(trees[i]%3==0){
                System.out.println("YES");
                return;
            }else{
                System.out.println("NO");
                return;
            }
        }

        if(trees[i]%3==1) {
            if(trees[i+1] >=2) {
                trees[i+1]-=2;
            }else if(trees[i+1] ==1){
                trees[i+1]=0;
                if(i+2<n && trees[i+2]==1){
                    trees[i+2]=0;
                }else{
                    System.out.println("NO");
                    return;
                }
            }else{
                System.out.println("NO");
                return;
            }
        }else if(trees[i]%3==2){
            if(trees[i+1] >=1) {
                trees[i+1]-=1;
            }else {
                System.out.println("NO");
                return;
            }
        }
        checkTree(n, i+1);
    }
}
