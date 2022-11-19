package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1074 Z
 */
public class BOJ1074 {

	private static int count = 0;
	static BOJSolverTester solver;
	private static int targetRow;
	private static int targetCol;
	private static int result;
	public static void main(String[] args) throws IOException {
		if ( args != null && args.length!= 0 && (args.length!= 1 || !args[0].isBlank())) {
			solver =new BOJSolverTester(args);
		}else {
			solver =new BOJSolverTester();
		} 
		int size = solver.readInt();
		targetRow = solver.readInt();
		targetCol = solver.readInt();
		
		draw(0,0,size);
		solver.write(result);
		solver.flushAndClose();
	}		



	private static void draw(int row, int col, int size) {
		if ( size == 1 ) {
			if (( row == targetRow || row+1 == targetRow )
				&& ( col == targetCol || col+1 == targetCol )){
				countOrResult(row,col);
				countOrResult(row,col+1);
				countOrResult(row+1,col);
				countOrResult(row+1,col+1);
			}else {
				count+=4;
			}
		}else {
			int sSize = (int) Math.pow(2, size-1);
			if ( row <= targetRow && targetRow < row+sSize && col <= targetCol && targetCol < col+sSize ) {
				draw(row, col, size-1); 			// 0 0  0 0
			}else {
				count += sSize*sSize;
			}
												//   1    2
			if ( row <= targetRow && targetRow < row+sSize && col <= targetCol+sSize && targetCol < col+sSize+sSize ) {
				draw(row, col+sSize, size-1); 		// 0 2  0 4 
			}else {
				count += sSize*sSize;
			}
			if ( row+sSize <= targetRow && targetRow < row+sSize+sSize && col <= targetCol && targetCol < col+sSize ) {
				draw(row+sSize, col, size-1); 		// 2 0  4 0
			}else {
				count += sSize*sSize;
			}
			if ( row+sSize <= targetRow && targetRow < row+sSize+sSize && col <= targetCol+sSize && targetCol < col+sSize+sSize ) {
				draw(row+sSize, col+sSize, size-1); // 2 2  4 4
			}else {
				count += sSize*sSize;
			}
		}
		
	}



	private static void countOrResult(int row, int col) {
		if ( targetRow == row && targetCol == col ) {
			result = count;
//			System.out.println("@@@ " + row + " " + col + " " + count);
		}else {
//			System.out.println(row + " " + col + " " + count++);
			count++;
		}
		
	}



	public static class BOJSolverTester {
		private String[] testSource;
		private int lineIdx = 0;
		private StringBuilder sb;
		
		private StringTokenizer st;
		private BufferedReader br;
		private BufferedWriter bw;
		public BOJSolverTester() throws IOException {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}
		

		public BOJSolverTester(String[] args) {
			if ( args.length == 1 ) {
				args = args[0].split("\r\n");
			}
			testSource = args;
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			st = new StringTokenizer(args[0]);
			lineIdx++;
			
			sb = new StringBuilder();
		}


		public String readLine() throws IOException {
			if ( testSource != null ) {
				return testSource[lineIdx++];
			}else {
				return br.readLine();
			}
		}

		public void writeNewLine() throws IOException {
			bw.newLine();
			
			if ( testSource!= null ) {
				sb.append("\r\n");
			}
			
		}

		public void flushAndClose() throws IOException {
			bw.flush();
			bw.close();
			if ( br != null ) {
				br.close();
			}
			
		}
		
		public void write(int input) throws IOException {
			this.write(String.valueOf(input));
		}
		public void write(String input) throws IOException {
			bw.write(input);
			if ( testSource!= null ) {
				sb.append(input);
			}
		}

		public int readInt() {
			return Integer.parseInt(st.nextToken());
		}
		/**
		 * Buffered Reader.nextLine()
		 */
		public void nextLine() throws IOException {
			if ( testSource == null ) {
				st = new StringTokenizer(br.readLine());
			}else {
				st = new StringTokenizer(testSource[lineIdx++]);
			}
		}
		
		public static void print2DArray(int[][] arr) {
			for ( int i = 0 ; i < arr.length ; i ++ ) {
				System.out.println(Arrays.toString(arr[i]));
			}
		}

	}

	public static Object getResult() {
		return solver.sb.toString();
	}

}

