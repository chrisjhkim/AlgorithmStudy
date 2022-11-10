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
 * BOJ 1260 DFS 와 BFS * 
 */
public class BOJ1260 {
	public static void main(String[] args) throws IOException {
		BOJSolver solver = new BOJSolver();

		int dotCount = solver.readInt();
		int lineCount = solver.readInt();
		int startDot = solver.readInt();
		
		
		int[][] map = new int[dotCount+1][dotCount+1];
		
		for ( int i = 0 ; i < lineCount ; i ++ ) {
			solver.nextLine();
			int a= solver.readInt();
			int b= solver.readInt();
			map[a][b] = 1;
			map[b][a] = 1;
		}
//		solver.print2DArray(map);
		boolean[] used = new boolean[dotCount+1];
		dfs(startDot, map, used, solver);
		solver.writeNewLine();

		used = new boolean[dotCount+1];
		bfs(startDot, map, used, solver);
		
		solver.flushAndClose();
	}


	private static void bfs(int pos, int[][] map, boolean[] used, BOJSolver solver) throws IOException {
		solver.write(pos);
		solver.write(" ");
		Deque<Integer> deque1 = new ArrayDeque<>();
		Deque<Integer> deque2 = new ArrayDeque<>();
		
		deque1.add(pos);
		used[pos] = true;
		while ( !deque1.isEmpty() ) {
			while ( !deque1.isEmpty() ) {
				int item = deque1.poll();
				for ( int i = 1; i < map.length; i ++ ) {
					if ( map[item][i] == 1 && !used[i]) {
						deque2.add(i);
						solver.write(i);
						solver.write(" ");
						used[i] = true;
					}
				}
			}
			
			while ( !deque2.isEmpty()) {
				deque1.add(deque2.poll());
			}
			
		}
		
	}


	private static void dfs(int pos, int[][] map, boolean[] used, BOJSolver solver) throws IOException {
		solver.write(pos);
		solver.write(" ");
		used[pos] = true;
		for ( int i = 1 ; i < map.length ; i ++ ) {
			if ( map[pos][i] == 1 && !used[i] ) {
				dfs(i, map, used, solver);
			}
		}
		
	}

	private static class BOJSolver{
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
/*
 * BOJ 1260 DFS 와 BFS  
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	128 MB	205524	75824	44979	35.893%
문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 
간선의 개수 M(1 ≤ M ≤ 10,000), 
탐색을 시작할 정점의 번호 V가 주어진다. 
다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

예제 입력 1 
4 5 1
1 2
1 3
1 4
2 4
3 4
예제 출력 1 
1 2 4 3
1 2 3 4
예제 입력 2 
5 5 3
5 4
5 2
1 2
3 4
3 1
예제 출력 2 
3 1 2 5 4
3 1 4 2 5
예제 입력 3 
1000 1 1000
999 1000
예제 출력 3 
1000 999
1000 999


*
*/

