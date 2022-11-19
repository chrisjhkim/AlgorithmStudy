package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJSolver {
	private StringTokenizer st;
	private BufferedReader br;
	private BufferedWriter bw;
	public BOJSolver() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	public String readLine() throws IOException {
		return br.readLine();
	}

	public void writeNewLine() throws IOException {
		bw.newLine();
		
	}

	public void flushAndClose() throws IOException {
		bw.flush();
		bw.close();
		br.close();
	}
	
	public void write(int input) throws IOException {
		this.write(String.valueOf(input));
	}
	public void write(String input) throws IOException {
		bw.write(input);
	}

	public int readInt() {
		return Integer.parseInt(st.nextToken());
	}
	/**
	 * Buffered Reader.nextLine()
	 */
	public void nextLine() throws IOException {
		st = new StringTokenizer(br.readLine());
	}
	
	public static void print2DArray(int[][] arr) {
		for ( int i = 0 ; i < arr.length ; i ++ ) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}

}
