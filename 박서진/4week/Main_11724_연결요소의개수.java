package PACKAGE_NAME;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_11724_연결요소의개수 {
    static int N,M;
    static ArrayList<Integer>[] A; //ArrayList<Integer>를 담는 리스트 객체
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        A = new ArrayList[N+1];
        isVisited = new boolean[N+1];
        int cnt=0;

        for(int i=1;i<N+1;i++){
            A[i] = new ArrayList<Integer>();

        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }
        for(int i=1;i<N+1;i++){
            if(!isVisited[i]){
                cnt++;
                DFS(i);
            }
        }
        System.out.println(cnt);
    }

    private static void DFS(int v) {
        if (isVisited[v]) return;
        isVisited[v] = true;

        for(int i:A[v]){
            if(isVisited[i]==false){
                DFS(i);
            }
        }
    }
}