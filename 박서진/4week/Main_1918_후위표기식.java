import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

/**
 * 중위표기식 -> 후위 표기식 변환하는 알고리즘 -> 스택
 * 문자열과 char 사이의 변환에 어려움이 있었음
 * while 문을 이용하여 원하는 조건에 만족할 때 까지 스택을 조절해야 하는 점이 어려웠음
 */
public class Main_1918_후위표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = br.readLine();
        ArrayDeque<String> stack = new ArrayDeque<>();
        String ans = ""; // 정답 문자열
        String[] op = {"+","-","*","/","(",")"};

        for(char c : p.toCharArray()){
            if(Arrays.asList(op).contains(c+"")){ // 연산자라면
                if(c==')'){ // 열린 괄호(')')를 만났다면 '('를 만날때까지 스택에서 뺀 후 ans 저장하기
                    while(!stack.isEmpty() && stack.peek().charAt(0)!='('){
                        ans+=stack.pop().charAt(0);
                    }
                    stack.pop(); // ( 를 빼는 부분
                }else{
                    while(!stack.isEmpty()&&compareOper(c, stack.peek().charAt(0))){ // 연산자 간의 비교 후 스택에 저장 or pop
                        ans += stack.pop().charAt(0);
                    }
                    stack.push(c+"");
                }
            }else{ // 피연산자의 경우 바로 ans에 추가하기
                ans+=c;
            }
        }
        while(!stack.isEmpty()){
            ans+=stack.pop(); // 입력 받은 문자열 종료 후 스택에 남아있는 연산자 출력
        }
        System.out.println(ans);

    }
    private static boolean compareOper(char op1, char op2){
        if(op1=='+'){
            return op2 == '+' || op2 == '/' || op2 == '*' || op2 == '-';
        }else if(op1=='-'){
            return op2 == '+' || op2 == '/' || op2 == '*' || op2 == '-';
        }else if(op1=='*'){
            return op2 == '*' || op2 == '/';
        }else if(op1=='/'){
            return op2 == '*' || op2 == '/';
        }
        return false;
    }
}
