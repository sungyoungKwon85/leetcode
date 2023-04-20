package programmers;

import org.junit.Assert;
import org.junit.Test;

public class 행렬테두리회전하기 {

  /**
   * rows x columns 크기인 행렬이 있습니다. 행렬에는 1부터 rows x columns까지의 숫자가 한 줄씩 순서대로 적혀있습니다.
   * 이 행렬에서 직사각형 모양의 범위를 여러 번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로 회전시키려 합니다.
   * 각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현하며, 그 의미는 다음과 같습니다.
   *
   * x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.
   *
   * 이 행렬에 (2, 2, 5, 4) 회전을 적용하면, 아래 그림과 같이 2행 2열부터 5행 4열까지 영역의 테두리가 시계방향으로 회전합니다.
   * 이때, 중앙의 15와 21이 있는 영역은 회전하지 않는 것을 주의하세요.
   *
   * 행렬의 세로 길이(행 개수) rows, 가로 길이(열 개수) columns, 그리고 회전들의 목록 queries가 주어질 때,
   * 각 회전들을 배열에 적용한 뒤, 그 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.
   *
   * 입출력 예시
   * rows	columns	queries	result
   * 6	6	[[2,2,5,4],[3,3,6,6],[5,1,6,3]]	[8, 10, 25]
   * 3	3	[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]	[1, 1, 5, 3]
   * 100	97	[[1,1,100,97]]	[1]
   */

  @Test
  public void test1(){
    int rows = 6;
    int columns = 6;
    int[][] queries = {
      {2,2,5,4}
      , {3,3,6,6}
      , {5,1,6,3}
    };
    int[] expected = {8, 10, 25};
    Assert.assertArrayEquals(solution(rows, columns, queries), expected);
  }

  @Test
  public void test2(){
    int rows = 100;
    int columns = 97;
    int[][] queries = {
      {1,1,100,97}
    };
    int[] expected = {1};
    Assert.assertArrayEquals(solution(rows, columns, queries), expected);
  }

  @Test
  public void test3(){
    int rows = 3;
    int columns = 3;
    int[][] queries = {
      {1,1,2,2}
      , {1,2,2,3}
      , {2,1,3,2}
      , {2,2,3,3}
    };
    int[] expected = {1, 1, 5, 3};
    Assert.assertArrayEquals(solution(rows, columns, queries), expected);
  }

  public int[] solution(int rows, int columns, int[][] queries) {
    int[] result = new int[queries.length];
    int[][] base = makeBase(rows, columns);
    for (int i = 0; i < queries.length; i++) {
      int[][] maps = getMaps(queries[i]);
      int temp = base[maps[0][0]][maps[0][1]];
      int min = temp;
      for (int j = 1; j < maps.length; j++) {
        int row = maps[j][0];
        int column = maps[j][1];
        int cell = base[row][column];
        if (cell < min) min = cell;

        base[row][column] = temp;
        temp = cell;
      }
      base[maps[0][0]][maps[0][1]] = temp;
      result[i] = min;
    }

    return result;
  }

  private int[][] getMaps(int[] query) {
    int startRow = query[0];
    int endRow = query[2];
    int startColumn = query[1];
    int endColumn = query[3];
    int lengthOfCells = (endRow - startRow + 1)*2 + (endColumn - startColumn + 1)*2 - 4;
    int[][] maps = new int[lengthOfCells][2];

    int rowIndex = 0;
    for (int i = startColumn; i <= endColumn; i++) {
      maps[rowIndex][0] = startRow - 1;
      maps[rowIndex][1] = i - 1;
      rowIndex++;
    }
    for (int i = startRow + 1; i <= endRow; i++) {
      maps[rowIndex][0] = i - 1;
      maps[rowIndex][1] = endColumn - 1;
      rowIndex++;
    }
    for (int i = endColumn - 1; i >= startColumn; i--) {
      maps[rowIndex][0] = endRow - 1;
      maps[rowIndex][1] = i - 1;
      rowIndex++;
    }
    for (int i = endRow - 1; i > startRow; i--) {
      maps[rowIndex][0] = i - 1;
      maps[rowIndex][1] = startColumn - 1;
      rowIndex++;
    }
    
    return maps;
  }

  private int[][] makeBase(int rows, int columns) {
    int start = 1;
    int[][] base = new int[rows][columns];
    for (int i = 0; i < base.length; i++) {
      for (int j = 0; j < base[i].length; j++) {
        base[i][j] = start++;
      }
    }
    return base;
  }
}
