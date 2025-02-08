package argo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 명령어 개수 입력

        int[] arr = new int[n]; // 스택 배열
        int cnt = 0; // 스택 크기

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            int command = Integer.parseInt(st.nextToken()); 

            if (command == 1) { 
                int value = Integer.parseInt(st.nextToken());
                arr[cnt++] = value;
            } else if (command == 2) { 
                if (cnt == 0) {
                    System.out.println(-1);
                } else {
                    System.out.println(arr[--cnt]);
                }
            } else if (command == 3) { 
                System.out.println(cnt);
            } else if (command == 4) { 
                System.out.println(cnt == 0 ? 1 : 0);
            } else if (command == 5) { 
                if (cnt == 0) {
                    System.out.println(-1);
                } else {
                    System.out.println(arr[cnt - 1]);
                }
            }
        }

        br.close(); 
    }
}
