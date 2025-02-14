package algo;
import java.util.*;
import java.io.*;

public class Main_12605 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] str = br.readLine().split(" "); // 공백을 기준으로 문자열을 자른다.
            Stack<String> stack = new Stack<>();
            for (String s : str) {
                stack.push(s); //문자열을 stack에 넣는다.
            }
            System.out.print("Case #" + test_case + ": ");
            while(!stack.isEmpty()){
                System.out.print(stack.pop() + " "); // 뒤에서부터 출력이 된다.
            }
            System.out.println();
        }

    }
}
