package argoStudy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> list = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        list.add(1);

        for(int i = 1; i<n; i++){
            list.add(list.size()-num[i],i+1);
        }

        for(int i : list){
            System.out.print(i+" ");
        }


    }
}
