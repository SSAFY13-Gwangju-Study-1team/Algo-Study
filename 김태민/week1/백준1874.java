package argo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Deque<String> result = new LinkedList<>(); // 연산을 넣는 덱
        Deque<Integer> arr = new LinkedList<>();  // 수열을 입력 받는 덱
        Deque<Integer> nums = new LinkedList<>(); // 스택을 담는 덱
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            int s1 = Integer.parseInt(scanner.nextLine());
            arr.addLast(s1);
        }

        int cnt = 1; // 스택에 넣을 숫자
        boolean isPossible = true;


        while (!arr.isEmpty()) {
            int key = arr.peekFirst(); // 수열에서 앞부분 숫자 확인

            // 스택이 비어있거나 스택의 마지막 숫자와 key가 같을 경우
            if (!nums.isEmpty() && nums.peekLast() == key) {

                // 스택에서 가장 윗부분 지우기
                nums.removeLast();
                // 수열의 값 제거
                arr.removeFirst();
                // pop이여서 결과 덱에 - 값 추가하기
                result.addLast("-");
            } else if (cnt <= key) {
                // 원하는 수인 key의 값과 같아 질때까지
                // 스택에 값을 추가
                nums.addLast(cnt++);
                result.addLast("+");
            } else {

                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            for (String op : result) {
                System.out.println(op);
            }
        } else {
            System.out.println("NO");
        }
    }
}
