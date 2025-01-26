import java.util.*;

public class Main_2504 { // 틀렸습니다!! ㅠ

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int ans = 0;
		int num = 1;
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			
			if(c=='(') {
				stack.push(c);
				num *= 2;
			}
			
			else if(c=='[') {
				stack.push(c);
				num *= 3;
			}
			
			else if(c==')') {
				if(stack.isEmpty() || stack.peek()!='(') { //올바른 괄호x 
					ans = 0;
					return;
				}
				else {
					if(s.charAt(i-1)=='(') { // 괄호쌍이 붙어있을 경우 
						ans += num;
					}
					stack.pop();
					num /= 2; //곱하기가 아닌 더하기 연산을 했으므로 나눠주기 <=== 오답으로 이 로직을 검색해서 추가함
					
				}
				
			}
			
			else if(c == ']') {//')' 경우와 같음 
				if(stack.isEmpty() || stack.peek() != '[') {
					ans = 0;
					return;
				}
				else if(s.charAt(i-1)=='[') {
					ans += num;
				}
				stack.pop();
				num /= 3;
			}
		}
		
		if(!stack.isEmpty()) {
			System.out.println(0);
		}
		else {
			System.out.println(ans);
		}
	}
}
