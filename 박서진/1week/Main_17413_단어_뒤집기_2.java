import java.io.*;
import java.util.*;
public class Main_17413_단어_뒤집기_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = br.readLine();
        char[] s_arr = sentence.toCharArray();
        Stack<Character> st = new Stack<>();
        boolean tag = false;
        for (char ch:s_arr){
            if(ch == '<'){
                while(!st.isEmpty()){
                    System.out.print(st.pop());
                }
                tag = true;
                System.out.print(ch);
            }else if(ch == '>'){
                tag = false;
                System.out.print(ch);
            }else if(tag){
                System.out.print(ch);
            }else{
                if (ch==' '){
                    while(!st.isEmpty()){
                        System.out.print(st.pop());
                    }
                    System.out.print(' ');
                }else{
                    st.push(ch);
                }
            }
        }
        while(!st.isEmpty()){
            System.out.print(st.pop());
        }
    }
}
