package Week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 설계
 *  - 유니온 파인드 사용하면 될듯?
 *
 *  메모리: 45216kb
 *  실행시간: 372ms
 *
 */


public class Main_24391_귀찮은해강이_김태민 {

    static int[] parent;

    static void initParent(int n){
        for(int i = 1; i<=n; i++){
            parent[i] = i;
        }
    }


    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return false;
        // if(x<=y) parent[y] = x;
        // else parent[x] = y;
        // 그냥 보통 밑에 한줄만 적긴함
        parent[x] = y;

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        initParent(n);
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x,y);  // 서로 같은 집합을 형성하기 떄문에 union사용
        }

        int[] list = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        for(int i = 0; i<n-1; i++){
            if(find(list[i])!=find(list[i+1])){
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
