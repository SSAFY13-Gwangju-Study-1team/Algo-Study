import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;
public class Main_1969_DNA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());
        char[][]  dna = new char[n][m];
        int hammingD = 0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            dna[i] = st.nextToken().toCharArray();
        }

        for(int i=0;i<m;i++){
            int[] indiCnt = new int[4]; //TAGC 순서
            for(int j=0;j<n;j++){
                if(dna[j][i] == 'T'){
                    indiCnt[0]+=1;
                }else if(dna[j][i] == 'G'){
                    indiCnt[1]+=1;
                }else if(dna[j][i] == 'C'){
                    indiCnt[2]+=1;
                }else if(dna[j][i] == 'A'){
                    indiCnt[3]+=1;
                }
            }
            // 어떤 문자가 가장 많은지 확인
            // 나머지 개수들은 해밍디스턴스에 추가
            int maxChar = 0;
            int maxIndex = 0;
            for(int k=0;k<4;k++){
                if(maxChar<=indiCnt[k]){
                    maxChar = indiCnt[k];// 가장 많은 char의 개수가 나옴
                    maxIndex = k; // 그때의 인덱스 저장
                }
            }
            // 전체에서 max를 뺀 나머지가 들어감
            hammingD += n-maxChar;

            if(maxIndex == 0){
                sb.append('T');
            }else if(maxIndex == 1){
                sb.append('G');
            }else if(maxIndex == 2){
                sb.append('C');
            }else if(maxIndex == 3){
                sb.append('A');
            }
        }
        sb.append("\n");
        sb.append(hammingD);
        System.out.println(sb);
    }

}
