package algo_study.week07;
import java.io.*;
import java.util.*;
public class Main_1969_DNA_정해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // DNA 개수
        int M = Integer.parseInt(st.nextToken()); // 문자열 길이

        String[] dna = new String[N]; // DNA 배열
        for (int i = 0; i < N; i++) {
            dna[i] = br.readLine();
        }

        StringBuilder resultDNA = new StringBuilder();
        int hammingSum = 0;

        for (int col = 0; col < M; col++) {
            int[] count = new int[4]; // A, C, G, T 카운트

            // 각각 dna 카운
            for (int row = 0; row < N; row++) {
                char c = dna[row].charAt(col);
                if (c == 'A') count[0]++;
                else if (c == 'C') count[1]++;
                else if (c == 'G') count[2]++;
                else if (c == 'T') count[3]++;
            }

            // 제일 많이 나오는 문자 확인해주기 -> 사전순으로 정렬, maxIdx 갱신 
            int maxIdx = 0;
            for (int i = 1; i < 4; i++) {
                if (count[i] > count[maxIdx]) {
                    maxIdx = i;
                }
            }
            
            // A,C,G,T 중 maxIdx로 선정된 값을 result에 붙여주기 
            char selected = "ACGT".charAt(maxIdx);
            resultDNA.append(selected);

            // Hamming Distance 계산하기 
            for (int row = 0; row < N; row++) {
                if (dna[row].charAt(col) != selected) { // 선택된 값 다르면 
                    hammingSum++; // 더해주기 
                }
            }
        }

        // 결과 출력
        System.out.println(resultDNA.toString());
        System.out.println(hammingSum);
	        				
	}
}