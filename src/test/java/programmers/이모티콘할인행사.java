package programmers;

import org.junit.Assert;
import org.junit.Test;

public class 이모티콘할인행사 {

  @Test
  public void test1() {
    int[][] users = {
        {40, 10000},
        {25, 10000}
    };
    int[] emoticons = {7000, 9000};
    int[] result = {1, 5400};
    Assert.assertArrayEquals(result, solution(users, emoticons));
  }
  @Test
  public void test2() {
    int[][] users = {
        {40, 2900},
        {23, 10000},
        {11, 5200},
        {5, 5900},
        {40, 3100},
        {27, 9200},
        {32, 6900}
    };
    int[] emoticons = {1300, 1500, 1600, 4900}; // max 7, (40, 40, 20, 40) is best
    int[] result = {4, 13860};
    Assert.assertArrayEquals(result, solution(users, emoticons));
  }

  int[] percentages = {10, 20, 30, 40}; // 할인율
  int[] answer = new int[] {0, 0};
  public int[] solution(int[][] users, int[] emoticons) {
    // 각 이모티콘 할인율을 어떻게 해야 플러스 가입자가 가장 많이 늘어나고. 동일하다면 판매액이 가장 많은지
    // 브루트포스 알고리즘
    //  백트래킹(재귀)을 이용한 탐색
    //  DFS&BFS 탐색

    int level = 0;
    dfs(users, emoticons, new int[emoticons.length], level);

    return answer;
  }

  private void dfs(int[][] users, int[] emoticons, int[] combination, int level) {
    // 종료 구간
    if (emoticons.length == level) {
      int plusCount = 0;
      int salePrice = 0;
      for (int i = 0; i < users.length; i++) {
        int allowPercentage = users[i][0];
        int balance = users[i][1];
        int totalPrice = 0;
        for (int j = 0; j < combination.length; j++) {
          if (allowPercentage <= combination[j]) { // 조건에 맞는 것만 구매
            int discountedPrice = emoticons[j]/100 * (100 - combination[j]); // double int 연산에서 틀리는 경우가 발생한다. 문제에 100수로 한걸 보고 100으로 먼저 나눠줌
            totalPrice += discountedPrice;
          }
        }
        if (balance > totalPrice) { // 여유 금액 이하
          salePrice += totalPrice;
        } else { // 여유 금액 넘음
          plusCount++;
        }
      }
      if ((answer[0] == 0 && answer[1] == 0)
          || (answer[0] < plusCount)
          || (answer[0] <= plusCount && answer[1] <= salePrice)) {
        answer = new int[]{plusCount, salePrice};
      }
    } else {
      // dfs 구간
      for (int i = 0; i < percentages.length; i++) {
        combination[level] = percentages[i];
        dfs(users, emoticons, combination, level+1);
      }
    }
  }
}
/**
 * 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
 * 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.
 *  1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
 *  2. 이모티콘 판매액을 최대한 늘리는 것.
 * 1번 목표가 우선이며, 2번 목표가 그 다음입니다.
 *
 * n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매
 * 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
 *
 * 카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.
 *  각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
 *  각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 *
 * 사용자 n명
 * 구매 기준을 담은 2차원 정수 배열 users
 *  users의 원소는 [비율, 가격]의 형태입니다.
 * 이모티콘 m개의 정가를 담은 1차원 정수 배열 emoticons
 *
 * 이때, 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return
 */

/**
 * DFS (Depth-first Search) 깊이 우선 탐색
 *
 * 처음 노드 선택
 * 오름차순으로 선택
 * 여러개 있으면 작은거 선택
 * 더 이상 없으면 부모로 돌아오기
 *
 * 재귀로 구현하든
 * Stack으로 구현하든
 * https://codingnojam.tistory.com/44
 */
