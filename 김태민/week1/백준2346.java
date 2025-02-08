package argo;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();
        Deque<Integer> numDeque = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            deque.add(Integer.parseInt(st.nextToken()));
            numDeque.add(i+1);
        }

        sb.append(numDeque.remove()+" ");
        int index = deque.remove();
        while (!deque.isEmpty()) {
            if (index > 0) {
                for (int i = 0; i < index-1; i++) {
                    deque.add(deque.remove());
                    numDeque.add(numDeque.remove());
                }
            } else {
                for (int i = 0; i < -index; i++) {
                    deque.addFirst(deque.removeLast());
                    numDeque.addFirst(numDeque.removeLast());
                }
            }
            sb.append(numDeque.remove() + " ");
            index = deque.poll();
        }
        System.out.println(sb);

    }
}
