package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;


/**
 * BOJ 16236 아기 상어
 */
public class BOJ16236 {
	private static final int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
	private static int[][] map;
	private static Shark shark;
	public static void main(String[] args) throws IOException {
		BOJSolver solver = new BOJSolver();
		int mapSize = solver.readInt();
		map = new int[mapSize][mapSize];
		for ( int i = 0 ;i < mapSize ; i ++ ) {
			solver.nextLine();
			for ( int j = 0 ;j < mapSize ; j ++ ) {
				map[i][j] = solver.readInt();
				
				if ( map[i][j] == 9 ) {
					shark = new Shark(i, j);
				}
			}
		}
		
		int resultTimePassed = 0;
		Optional<NearFishInfo> searchResult = shark.findNearFish(map);
		while( searchResult.isPresent()) {
			NearFishInfo nearFishInfo = searchResult.get();
//			System.out.println("near : " + nearFishInfo);
			map[shark.row][shark.col] = 0;
			shark.moveTo(nearFishInfo);
			map[nearFishInfo.row][nearFishInfo.col] = 9;
			resultTimePassed += nearFishInfo.distance;
			

//			printStatus(map, shark, resultTimePassed);
			
			searchResult = shark.findNearFish(map);
			
		}
		
		solver.write(resultTimePassed);
		solver.flushAndClose();
	}
	
	private static void printStatus(int[][] arr, Shark shark2, int resultTimePassed) {
		System.out.println("size = " + shark2.size + " timePassed = " +resultTimePassed);
		for ( int i = 0 ; i < arr.length ; i ++ ) {
			for ( int j = 0 ;j < arr[i].length; j ++ ) {
				if ( arr[i][j] == 9 ) {
					
					System.out.print("■ ");
				}else {
					
					System.out.print(arr[i][j]+ " ");
				}
			}
			System.out.println();
		}

	}

	private static class Shark{
		private int row;
		private int col;
		private int size;
		private int sizeExp;
		public Shark(int row, int col) {
			super();
			this.row = row;
			this.col = col;
			this.size = 2;
			this.sizeExp = 0;
		}
		
		
		public Optional<NearFishInfo> findNearFish(int[][] map) {
			 
			Deque<int[]> deque = new ArrayDeque<>();
			Deque<int[]> deque2 = new ArrayDeque<>();
			List<NearFishInfo> resultCandList = new ArrayList<>();
			int distance = 0;
			deque.add(new int[] {this.row,this.col});
			boolean[][] checked = new boolean[map.length][map[0].length];
			while ( !deque.isEmpty() ) {
				while( !deque.isEmpty() ) {
					int[] item = deque.poll();
					
					for ( int[] direction : directions ) {
						int movedRow = item[0]+direction[0];
						int movedCol = item[1]+direction[1];
						if ( movedRow >= 0 && movedRow <= map.length-1 
								&& movedCol >= 0 && movedCol <= map[0].length-1
								&& map[movedRow][movedCol] != 9
								&& map[movedRow][movedCol] <= this.size
								&& !checked[movedRow][movedCol]) {
							checked[movedRow][movedCol] = true;
//							map[movedRow][movedCol] = 0;
							if ( map[movedRow][movedCol] < this.size &&  map[movedRow][movedCol] !=0 ) {
								resultCandList.add(new NearFishInfo(movedRow, movedCol, distance+1));
							}else {
								deque2.add(new int[] {movedRow,movedCol});
							}
						}
					}
				}
				distance++;
				if ( !resultCandList.isEmpty() ) {
					return resultCandList.stream().sorted((o1,o2)->o1.row==o2.row?o1.col-o2.col:o1.row-o2.row).findFirst();
				}
				
				while( !deque2.isEmpty() ) {
					deque.add(deque2.poll());
				}
			}
			return Optional.empty();
		}


		public void moveTo(NearFishInfo nearFishInfo) {
			this.row = nearFishInfo.row;
			this.col = nearFishInfo.col;
			this.expUp();
			
			
		}

		public void expUp() {
			this.sizeExp ++;
			if ( this.size == this.sizeExp) {
				this.size ++;
				this.sizeExp = 0;			// 이거 맞나? 
			}
		}
		
	}
	
	
	private static class NearFishInfo{
		public NearFishInfo(int movedRow, int movedCol, int distance2) {
			this.row = movedRow;
			this.col = movedCol;
			this.distance = distance2;
		}
		int row;
		int col;
		int size;
		int distance;
		@Override
		public String toString() {
			return "NearFishInfo [row=" + row + ", col=" + col + ", size=" + size + ", distance=" + distance + "]";
		}
		
	}
	

	public static class BOJSolver {
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

}


/*




2 초	512 MB	50621	23043	13707	42.037%
문제
N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 
공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 
가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 
나머지 칸은 모두 지나갈 수 있다. 
아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 
따라서, 크기가 같은 물고기는 먹을 수 없지만, 
그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 
즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 
이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

   아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 
    예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 
아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.

둘째 줄부터 N개의 줄에 공간의 상태가 주어진다. 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.

0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
아기 상어는 공간에 한 마리 있다.

출력
첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.

예제 입력 1 
3
0 0 0
0 0 0
0 9 0
예제 출력 1 
0
예제 입력 2 
3
0 0 1
0 0 0
0 9 0
예제 출력 2 
3
예제 입력 3 
4
4 3 2 1
0 0 0 0
0 0 9 0
1 2 3 4
예제 출력 3 
14
예제 입력 4 
6
5 4 3 2 3 4
4 3 2 3 4 5
3 2 9 5 6 6
2 1 2 3 4 5
3 2 1 6 5 4
6 6 6 6 6 6
예제 출력 4 
60
예제 입력 5 
6
6 0 6 0 6 1
0 0 0 0 0 2
2 3 4 5 6 6
0 0 0 0 0 2
0 2 0 0 0 0
3 9 3 0 0 1
예제 출력 5 
48
예제 입력 6 
6
1 1 1 1 1 1
2 2 6 2 2 3
2 2 5 2 2 3
2 2 2 4 6 3
0 0 0 0 0 6
0 0 0 0 0 9
예제 출력 6 
39
*/