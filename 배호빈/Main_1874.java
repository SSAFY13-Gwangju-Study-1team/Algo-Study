
import java.util.*;
import java.io.*;
public class Main_1874 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[N]; // 만들어야하는 수열을 담을 배열
        Stack<Integer>stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int index = 0; // 순열 맨 앞부터 비교를 시작하기 위한 변수
        for (int i = 1; i <= N; i++) { // 1부터 N까지 반복하면서 스택에 넣음

            stack.push(i); // 먼저 stack 값을 넣어주고 +를 출력한다.
            sb.append("+").append("\n");

            if (stack.peek() == arr[index]) { // stack 맨위가 순열의 인덱스와 비교해서 같을 때
                stack.pop(); // stack 맨위를 제거하고 - 출력
                sb.append("-").append("\n");
                index++; // 순열의 인덱스 값을 증가
                while(!stack.isEmpty()){ // stack 맨위를 제거 후 순열의 바로 다음 인덱스와 비교를 해줘야한다.
                    if(stack.peek() != arr[index]){ // stack 맨위와 순열의 인덱스가 같지 않으면 루프를 벗어나고 아니면 계속 제거해준다.
                        break;
                    }else{
                        stack.pop();
                        sb.append("-").append("\n");
                        index++;
                    }
                }
            }

        }
        if(stack.isEmpty()){ // stack에 아무것도 없어야 만들 수 있는 수열이다.
            System.out.println(sb);
        }else{
            System.out.println("NO"); // stack에 값이 남아있다면 만들 수 없는 수열이다.
        }


    }
}
