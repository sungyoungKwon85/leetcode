package programmers;

import org.junit.Assert;
import org.junit.Test;

public class 미로탈출명령어 {

  @Test
  public void test1(){
    Assert.assertEquals("dllrl", solution(3,4,2,3,3,1,5));
  }
  @Test
  public void test2(){
    Assert.assertEquals("dr", solution(2,2,1,1,2,2,2));
  }
  @Test
  public void test3(){
    Assert.assertEquals("impossible", solution(3,3,1,2,3,3,4));
  }
  @Test
  public void test4(){
    Assert.assertEquals("ddddllruuu", solution(7,7,3,5,4,4,10));
  }
  @Test
  public void test5(){
    Assert.assertEquals("dddlrruuuu", solution(7,7,4,4,3,5,10));
  }
  @Test
  public void test6(){
    Assert.assertEquals("dllllrrr", solution(7,7,6,7,7,6,8));
  }

  String answer = "";
  int[] xD = {1, 0, 0, -1};
  int[] yD = {0, -1, 1, 0};
  public String solution(int row, int column, int x, int y, int x2, int y2, int k) {
    if ((Math.abs(x - x2) + Math.abs(y - y2)) % 2 != k % 2) {
      return "impossible";
    }

    int distance = getDistance(x, y, x2, y2);
    move(0, row, column, x, y, x2, y2, k, distance);

    return answer;
  }

  private void move(int dIndex, int row, int column, int x, int y, final int x2, final int y2, int k, int distance) {
    if (k == 0 && distance == 0) {
      return;
    }

    switch (dIndex) {
      case 0: {
        int tempDistance = getDistance(x, x + xD[dIndex], x2, y2);
        if (x == row || k - 1 < tempDistance) {
          move(dIndex + 1, row, column, x, y, x2, y2, k, getDistance(x, y, x2, y2));
          return;
        } else {
          x += xD[dIndex];
          answer += "d";
        }
      }
        break;
      case 1: {
        int tempDistance = getDistance(x, y + yD[dIndex], x2, y2);
        if (y <= 1 || k - 1 < tempDistance) {
          move(dIndex + 1, row, column, x, y, x2, y2, k, getDistance(x, y, x2, y2));
          return;
        } else {
          y += yD[dIndex];
          answer += "l";
        }
      }
        break;
      case 2: {
        int tempDistance = getDistance(x, y + yD[dIndex], x2, y2);
        if (y == column || k - 1 < tempDistance) {
          move(dIndex + 1, row, column, x, y, x2, y2, k, getDistance(x, y, x2, y2));
          return;
        } else {
          y += yD[dIndex];
          answer += "r";
        }
      }
        break;
      case 3: {
        if (x == 1) {
          return;
        } else {
          x += xD[dIndex];
          answer += "u";
        }
      }
        break;
    }
    move(0, row, column, x, y, x2, y2, k - 1, getDistance(x, y, x2, y2));
  }

  private static int getDistance(int x, int y, int x2, int y2) {
    return Math.abs(x2 - x) + Math.abs(y2 - y);
  }

  public String solution3(int row, int column, int x, int y, int x2, int y2, int k) {
    if ((Math.abs(x - x2) + Math.abs(y - y2)) % 2 != k % 2) {
      return "impossible";
    }

    StringBuilder sb = new StringBuilder();

    int upCount = x - x2;
    int horizontalCount = Math.abs(y2 - y);
    int downCount = (k - upCount - horizontalCount) / 2;
    downCount = (row - x) > downCount ? downCount : (row - x);

    for (int i = 0; i < downCount; i++) {
      sb.append("d");
    }

    k -= downCount;
    upCount += downCount;
    horizontalCount = k - upCount;

    int leftBoundaryCount = y - 1;
    int leftCount = y - y2;
    leftCount = (horizontalCount + leftCount) / 2;
    if (leftCount > leftBoundaryCount) {
      leftCount = leftBoundaryCount;
      while (horizontalCount > 0) {
        if (leftCount > 0) { // 이걸 조건건 이유는 바운더리 넘을까봐임.
          sb.append("l");
          leftCount--;
          k--;
        } else {
          sb.append("r");
          leftCount++;
          k--;
        }
        horizontalCount--;
      }
    } else {
      for (int i = 0; i < leftCount; i++) {
        sb.append("l");
        k--;
      }
      for (int i = 0; i < horizontalCount - leftCount; i++) {
        sb.append("r");
        k--;
      }
    }

    for (int i = 0; i < k; i++) {
      sb.append("u");
    }

    return sb.toString();
  }


  // fail T.T
  public String solution2(int n, int m, int x, int y, int r, int c, int k) {
    if ((x + y + r + c) % 2 != k % 2 || Math.abs(r - x) + Math.abs(c - y) > k) {
      return "impossible";
    }

    StringBuilder sb = new StringBuilder();
    int dNeed = n - x; // 실수. k에 따라 맨마지막까지 갈수도 있고 안갈수도 있는데 무조건 간다고 해버림 ㅜ
    k = k - dNeed;
    int lNeed = y - c;
    int upNeed = n - r;
    k = k - upNeed;
    int horiCap = k;

    for (int i = 0; i < dNeed; i++) {
      sb.append("d");
    }

    int lBoundary = y - 1;
    int lCap = (horiCap + lNeed) / 2;
    if (lCap > lBoundary) {
      lCap = lBoundary;
      while (horiCap > 0) {
        if (lCap > 0) { // 이걸 조건건 이유는 바운더리 넘을까봐임.
          sb.append("l");
          lCap--;
        } else {
          sb.append("r");
          lCap++;
        }
        horiCap--;
      }
    } else {
      for (int i = 0; i < lCap; i++) {
        sb.append("l");
      }
      for (int i = 0; i < horiCap - lCap; i++) {
        sb.append("r");
      }
    }

    for (int i = 0; i < upNeed; i++) {
      sb.append("u");
    }

    return sb.toString();
  }
}
/**
 * n x m 격자 미로
 * (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
 * 이동하는 거리가 총 k여야 합니다.
 * 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
 * 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.
 *
 * 왼쪽으로 한 칸, 위로 한 칸, 왼쪽으로 한 칸 움직였다면, 문자열 "lul"
 */
