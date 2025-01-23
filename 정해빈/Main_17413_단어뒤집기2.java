import java.util.*;
import java.io.*;

public class Main_17413 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		boolean tag = false; // tag 안의 문자인지 확인 하는 flag
		
		for(int i=0; i<s.length();i++) {
			char c = s.charAt(i);
			
			if(c=='<') { // 이전에 있는 문자열들을 다빼서 거꾸로 붙이기 
				tag = true; // 현재 태그 안의 문자 
				while(!stack.isEmpty()) {
					sb.append(stack.peek());
					stack.pop();
				}
			}
			else if(c=='>') {
				tag = false; // 현재 태그 안의 문자 다 받기 
				sb.append(c);
			}
			
			else if(c==' ') {
				if(tag) { // 태그 안의 공백이라면 그대로 붙이기 
					sb.append(c);
				}
				else { // 태그 밖의 공백이라면 다음 단어임을 입력이라 생각 하고 스택비움 
					while(!stack.isEmpty()) {
						sb.append(stack.peek());
						stack.pop();
					}
				}
			}
			else {
				stack.push(c);
			}
		}
		
		System.out.println(sb);
		sc.close();
	}

}
