package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ11382 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int count = Integer.parseInt(br.readLine());
		int[] input = new int[count];
		for ( int i = 0 ; i < count ; i ++ ) {
			input[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(input);
		System.out.println(Arrays.toString(input));
		int[]prefixSum = new int[count+1];
		prefixSum[0] = 0;
		for ( int i = 1 ; i <= count ; i ++ ) {
			prefixSum[i] = prefixSum[i-1] + input[i-1];
		}
		System.out.println(Arrays.toString(prefixSum));
		int result = 0;
		for ( int i = 2 ; i < prefixSum.length ; i ++ ) {
			result += prefixSum[i];
		}
		System.out.println("result="+result);
//		bw.write(String.valueOf(Integer.parseInt(br.readLine())+Integer.parseInt(br.readLine())));
		bw.flush();
		bw.close();
		br.close();
	}

}
