package nexon2023;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class Three {

  @Test
  public void test1() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(1, 1, 1),
        Arrays.asList(1, 1, 1),
        Arrays.asList(1, 1, 1)
    );
    int maxSum = 4;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 2);
  }

  @Test
  public void test11() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(1, 1, 1),
        Arrays.asList(1, 1, 1),
        Arrays.asList(1, 1, 1)
    );
    int maxSum = 2;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 1);
  }

  @Test
  public void test2() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(2, 2, 2),
        Arrays.asList(3, 3, 3),
        Arrays.asList(4, 4, 4)
    );
    int maxSum = 14;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 2);
  }

  @Test
  public void test22() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(2, 2, 2),
        Arrays.asList(3, 3, 3),
        Arrays.asList(4, 4, 4)
    );
    int maxSum = 13;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 1);
  }

  @Test
  public void test222() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(2, 2, 2),
        Arrays.asList(3, 3, 3),
        Arrays.asList(4, 4, 4)
    );
    int maxSum = 27;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 3);
  }

  @Test
  public void test3() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(1, 1, 1, 1),
        Arrays.asList(2, 2, 2, 2),
        Arrays.asList(3, 3, 3, 3),
        Arrays.asList(4, 4, 4, 4)
    );
    int maxSum = 39;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 3);
  }

  @Test
  public void test4() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(4, 5),
        Arrays.asList(6, 7)
    );
    int maxSum = 2;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 0);
  }


  @Test
  public void test6() {
    List<List<Integer>> grid = Arrays.asList(
        Arrays.asList(1, 2, 3, 4),
        Arrays.asList(4, 3, 2, 1),
        Arrays.asList(1, 1, 4, 4),
        Arrays.asList(4, 4, 1, 1)
    );
    int maxSum = 4;
    Assert.assertEquals(largestSubgrid(grid, maxSum), 1);
  }






  public int largestSubgrid2(List<List<Integer>> grid, int maxSum) {
    // Write your code here
    int size = grid.size();
    int[] maxArr = new int[size];
    for (int n = 1; n <= size; n++) {
      int max = 0;
      for (int x = 0; x <= size -n ; x++) {
        for (int y = 0; y <= size -n; y++) {
          int sum = getSum(grid, n, x, y);
          if (max < sum) {
            max = sum;
          }
        }
      }
      maxArr[n-1] = max;
    }

    if (maxSum < maxArr[0]) return 0;
    if (maxSum == maxArr[size-1]) return size;

    for (int i = 0; i < size - 1; i++) {
      int min = maxArr[i];
      int max = maxArr[i+1];
      if (maxSum >= min && maxSum < max) {
        return i + 1;
      } else if (maxSum == max) {
        return i + 2;
      }
    }

    return 0;
  }

  private int getSum(List<List<Integer>> grid, int n, int x, int y) {
    int sum = 0;
    for (int i = x; i < x+n; i++) {
      for (int j = y; j < y+n; j++) {
        sum += grid.get(i).get(j);
      }
    }
    return sum;
  }









  /**
   * 2차원의 누적합계..
   *
   */


  public int largestSubgrid(List<List<Integer>> grid, int maxSum) {
    int n = grid.size();
    int[][] preSum = new int[n+1][n+1];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        preSum[i+1][j+1] = grid.get(i).get(j) + preSum[i+1][j] + preSum[i][j+1] - preSum[i][j];
      }
    }

    int size = n;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i + size > n || j + size > n) {
          continue;
        }

        while(size > 0 && getArea(i, j, size, preSum) > maxSum) {
          size--;
        }
      }
    }

    return size;
  }

  private int getArea(int i, int j, int size, int[][] preSum) {
    return preSum[i+size][j+size] - preSum[i+size][j] - preSum[i][j+size] + preSum[i][j];
  }
}
