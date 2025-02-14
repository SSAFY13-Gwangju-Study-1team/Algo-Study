import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
/**
 * lst.size() - nums[i]를 생각해 내면 되는 쉬운 문제
 * lst.size()가 항상 마지막을 가리키고 내 뒤에 몇명이 오면 되는지만 계산해주면 되는 문제입니다
 *
 * * */
public class Main_2605_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = parseInt(br.readLine()); // 학생 수

        String[] arr = br.readLine().split(" ");
        int[] nums = new int[n]; // 뽑은 카드 수를 받을 배열
        for(int i=0;i<n;i++){
            nums[i] = parseInt(arr[i]);
        }

        LinkedList<Integer> lst = new LinkedList<>(); // 사람 inedx를 넣을 linkedList 객체 생성


        for(int i=0;i<n;i++){
            lst.add(lst.size() - nums[i], i+1); // 리스트에 사이즈의 크기에서 들어갈 위치를 뺴서 요소 인덱스 값을 넣기
        }

        for (int i : lst){
            sb.append(i).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
