import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_1918_후위표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = br.readLine();
        ArrayDeque<String> stack = new ArrayDeque<>();
        String ans = "";
        String[] op = {"+","-","*","/","(",")"};

        for(char c : p.toCharArray()){
            if(Arrays.asList(op).contains(c+"")){
                if(c==')'){
                    while(!stack.isEmpty() && stack.peek().charAt(0)!='('){
                        ans+=stack.pop().charAt(0);
                    }
                    stack.pop(); // ( 를 빼는 부분
                }else{
                    while(!stack.isEmpty()&&compareOper(c, stack.peek().charAt(0))){
                        ans += stack.pop().charAt(0);
                    }
                    stack.push(c+"");
                }
            }else{
                ans+=c;
            }
        }
        while(!stack.isEmpty()){
            ans+=stack.pop();
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
