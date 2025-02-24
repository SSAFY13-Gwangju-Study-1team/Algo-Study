import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 후위표기식 계산 문제
 * 이런문제는 문자를 char로 변환하고 int로 또 변환하는게 너무 빡세다
 * 사용하는 방법을 익히는 게 중요하겠다
 * 특히 피연산자의 값이 0-9까지의 숫자가 아니라는 것을 캐치해야 하는데 그것을 못 알아채서 gpt한테 힌트로 얻었다...
 * 문제를 꼼꼼히 읽어야 겠다고 다짐했다 항상 안다고 떠넘기지 말자
 */
public class Main_1935_후위표기식2 {
    static ArrayDeque<Double> stack = new ArrayDeque<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine()); // 피연산자의 개수
        String[] op = {"+", "-", "*", "/"};
        char[] line = br.readLine().toCharArray();
        String[] stringLine = new String[line.length];

        HashMap<Character,Integer> hmap = new HashMap<>();
        char x = 'A';
        for(int i=0;i<n;i++){
            int num = parseInt(br.readLine());
            hmap.put(x,num);
            x++;
        }

        for(int i=0;i<line.length;i++){
            stringLine[i] = line[i]+"";
            if('A'<=line[i]&& line[i]<='Z'){
                stringLine[i] = Integer.toString(hmap.get(line[i]));
            }
        }

        for(String c:stringLine){
            if(Arrays.asList(op).contains(c)){
                double num2 = stack.pop();
                double num1 = stack.pop();
                operate(c.charAt(0), num2, num1);
            }else{
                stack.push(Double.valueOf(c));
            }
        }
//        System.out.println(stack.pop());
        System.out.println(String.format("%.2f",stack.pop()));

    }
    public static void operate(char oper, double num2, double num1){
        switch (oper){
            case '+':
                stack.push(num1 + num2);
                break;
            case '-':
                stack.push(num1 - num2);
                break;
            case '*':
                stack.push(num1 * num2);
                break;
            case '/':
                stack.push(num1 / num2);
                break;
        }
    }
}
