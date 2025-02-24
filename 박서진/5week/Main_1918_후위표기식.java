import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1918_후위표기식 {
    static ArrayDeque<Character> stack = new ArrayDeque<>();
    static String res="";
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Character[] op = {'+','-','*','/','(',')'};
        char[] line = br.readLine().toCharArray();
        for(char c:line){
            if(Arrays.asList(op).contains(c)){
                if(c==')'){
                    while (!stack.isEmpty() && stack.peek()!='('){
                        res+=stack.pop();
                    }
                    stack.pop(); // 마지막 괄호 빼기
                }else {
                    while(!stack.isEmpty()&&addStack(c, stack.peek())){
                        res += stack.pop();
                    }
                    stack.push(c);
                }

            }else{
                res+=c;
            }
        }
        while (!stack.isEmpty()){
            res+=stack.pop();
        }
        System.out.println(res);
    }
    public static boolean addStack(char oper1, char oper2){
        switch (oper1){
            case '+':
                if(oper2 == '+' ||oper2 == '-' || oper2 == '*' ||oper2 == '/')
                    return true;
            case '-':
                if(oper2 == '+' ||oper2 == '-' || oper2 == '*' ||oper2 == '/')
                    return true;
            case '*':
                if(oper2 == '*' ||oper2 == '/')
                    return true;
            case '/':
                if(oper2 == '*' ||oper2 == '/')
                    return true;
        }
        return false;
    }
}
