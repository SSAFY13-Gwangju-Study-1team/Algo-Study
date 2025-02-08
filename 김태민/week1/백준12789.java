package argo;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        Deque<Integer> deque = new ArrayDeque<>();
        Deque<Integer> result = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i<n; i++){
            deque.add(Integer.parseInt(st.nextToken()));
        }
        String happy = "Nice";
        int index = 1;

        while (!deque.isEmpty()){
            if (deque.peek() == index){
                deque.poll();
                index++;
            } else if(!result.isEmpty() && result.peekLast() == index){
                result.pollLast();
                index++;
            } else {
                result.addLast(deque.poll());
            }
        }

        while(!result.isEmpty()){
            if(result.pollLast() == index){
                index++;
            } else {
                happy = "Sad";
                break;
            }
        }


        System.out.println(happy);
    }
}
