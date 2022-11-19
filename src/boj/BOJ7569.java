package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BOJ 7569 토마토
 *
 */
public class BOJ7569 {
	private static final int[][] directions = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
	public static void main(String[] args) throws IOException {
		BOJSolver solver = new BOJSolver();
		int col = solver.readInt();
		int row = solver.readInt();
		int floor = solver.readInt();
		
		int restCount = 0;
		int[][][] map = new int[row][col][floor];
		
		Deque<int[]> deque = new ArrayDeque<>();
		Deque<int[]> deque2 = new ArrayDeque<>();
		for ( int k = 0 ; k < floor ; k ++ ) {
			for ( int i = 0 ; i < row ; i ++ ) {
				solver.nextLine();
				for ( int j = 0 ; j < col ; j ++ ) {
					map[i][j][k] = solver.readInt();
					if ( map[i][j][k] == 0 ) {
						restCount ++;
					}else if ( map[i][j][k] == 1 ) {
						deque.add(new int[]{i,j,k});
					}
				}
			}
		}
		int resultPassedDays = -1;
		while ( !deque.isEmpty() ) {
			while( !deque.isEmpty() ) {
				int[] item = deque.poll();
				
				for ( int[] direction : directions ) {
					int newRow = item[0]+direction[0];
					int newCol = item[1]+direction[1];
					int newFloor = item[2]+direction[2];
					if ( newRow >= 0 && newRow <= row-1 
							&& newCol >= 0 && newCol <= col-1 
							&& newFloor >= 0 && newFloor <= floor-1 
						&& map[newRow][newCol][newFloor] == 0 ) {
						map[newRow][newCol][newFloor] = 1;
						deque2.add(new int[] {newRow,newCol,newFloor});
						restCount--;
					}
				}
			}
			resultPassedDays++;
			
			while( !deque2.isEmpty() ) {
				deque.add(deque2.poll());
			}
		}
//		solver.print2DArray(map);
		
		if ( restCount > 0 ) {
			solver.write("-1");
		}else {
			solver.write(resultPassedDays);
		}
		solver.flushAndClose();
	}
	
	
	
	
	public static class BOJSolver {
		private StringTokenizer st;
		private BufferedReader br;
		private BufferedWriter bw;
		private BOJSolver() throws IOException {
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

		private int readInt() {
			return Integer.parseInt(st.nextToken());
		}
		/**
		 * Buffered Reader.nextLine()
		 */
		private void nextLine() throws IOException {
			st = new StringTokenizer(br.readLine());
		}
		
		private static void print2DArray(int[][] arr) {
			for ( int i = 0 ; i < arr.length ; i ++ ) {
				System.out.println(Arrays.toString(arr[i]));
			}
		}

	}


}
