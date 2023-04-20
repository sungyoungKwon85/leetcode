package programmers;

import java.util.Stack;
import org.junit.Assert;
import org.junit.Test;

public class 택배배달과수거하기 {
  @Test
  public void test11(){
    int cap = 1;
    int n = 1;
    int[] deliveries = {1};
    int[] pickups = {1};
    int result = 2;
    Assert.assertEquals(result, solution(cap, n, deliveries, pickups));
  }
  @Test
  public void test12(){
    int cap = 1;
    int n = 1;
    int[] deliveries = {2};
    int[] pickups = {2};
    int result = 4;
    Assert.assertEquals(result, solution(cap, n, deliveries, pickups));
  }
  @Test
  public void test1(){
    int cap = 4;
    int n = 5;
    int[] deliveries = {1, 0, 3, 1, 2};
    int[] pickups = {0, 3, 0, 4, 0};
    int result = 16;
    Assert.assertEquals(result, solution(cap, n, deliveries, pickups));
  }
  @Test
  public void test2(){
    int cap = 2;
    int n = 7;
    int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
    int[] pickups = {0, 2, 0, 1, 0, 2, 0};
    int result = 30;
    Assert.assertEquals(result, solution(cap, n, deliveries, pickups));
  }
  @Test
  public void test3(){
    int cap = 2;
    int n = 6;
    int[] deliveries = {3, 0, 1, 2, 3, 3};
    int[] pickups = {3, 1, 0, 3, 2, 3};
    int result = 50;
    Assert.assertEquals(result, solution(cap, n, deliveries, pickups));
  }

  public long solution(final int cap, int n, int[] deliveries, int[] pickups) {
    long answer = 0L;
    Stack<Integer> deliveryStack = new Stack<>();
    Stack<Integer> pickupStack = new Stack<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < deliveries[i]; j++) {
        deliveryStack.push(i + 1);
      }
      for (int j = 0; j < pickups[i]; j++) {
        pickupStack.push(i + 1);
      }
    }

    while(!deliveryStack.isEmpty() && !pickupStack.isEmpty()) {
      Integer deliveryDistance = deliveryStack.peek();
      Integer pickupDistance = pickupStack.peek();

      for (int i = 0; i < cap; i++) {
        if (!deliveryStack.isEmpty()) {
          deliveryStack.pop();
        }
        if (!pickupStack.isEmpty()) {
          pickupStack.pop();
        }
      }

      answer += Math.max(deliveryDistance, pickupDistance) * 2L;
    }

    while(!deliveryStack.isEmpty()) {
      Integer deliveryDistance = deliveryStack.peek();
      for (int i = 0; i < cap; i++) {
        if (!deliveryStack.isEmpty()) {
          deliveryStack.pop();
        }
      }

      answer += deliveryDistance * 2L;
    }

    while(!pickupStack.isEmpty()) {
      Integer pickupDistance = pickupStack.peek();
      for (int i = 0; i < cap; i++) {
        if (!pickupStack.isEmpty()) {
          pickupStack.pop();
        }
      }

      answer += pickupDistance * 2L;
    }

    return answer;
  }

  public long solution2(final int cap, int n, int[] deliveries, int[] pickups) {
    // 마지막 인덱스부터 -해가면서 ds가 0이 되는 지점이 di
    // 마지막 인덱스부터 -해가면서 ps가 cap이 되는 지점이 pi
    //  그 지점이 남았으면 다시 가야가는데 di, pi중 큰놈으로
    //  그 지점이 해소되었으면 작은 놈으로 -1

    int deliverIndex = n - 1;
    int pickupIndex = n - 1;
    int deliverSum = cap;
    int pickupSum = cap;
    int indexForLoop = n - 1;

    int distance = 0;

    while(deliverIndex >= 0 && pickupIndex >= 0) {
      distance = distance + (indexForLoop + 1) * 2;

      for (int i = indexForLoop; i >= 0; i--) {
        int delivery = deliveries[i];
        if (delivery > 0 && deliverSum > 0) {
          deliveries[i] = deliveries[i] - deliverSum;
          deliverSum -= delivery;
          if (deliverSum < 0) {
            deliverIndex = i;
          } else {
            deliverIndex = i - 1;
          }
        }

        int pickup = pickups[i];
        if (pickup > 0 && pickupSum > 0) {
          pickups[i] = pickups[i] - pickupSum;
          pickupSum -= pickup;
          if (pickupSum < 0) {
            pickupIndex = i;
          } else {
            pickupIndex = i - 1;
          }
        }

        if (deliverSum <= 0 && pickupSum <= 0) {
          break;
        }
      }
      if (deliverSum < 0 || pickupSum < 0) {
        indexForLoop = deliverIndex > pickupIndex ? deliverIndex : pickupIndex;
      } else if (deliverSum == 0 && pickupSum == 0) {
        indexForLoop = deliverIndex < pickupIndex ? deliverIndex : pickupIndex;
      }
      deliverSum = cap;
      pickupSum = cap;
    }

    return distance;
  }

  /**
   * https://acisliver.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%83%9D%EB%B0%B0-%EB%B0%B0%EB%8B%AC%EA%B3%BC-%EC%88%98%EA%B1%B0%ED%95%98%EA%B8%B0-JAVA
   *
   *
   * 일렬로 나열된 n개의 집에 택배를 배달하려 합니다
   * 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며, 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다
   *  i번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다. 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다. (1 ≤ i ≤ j ≤ n)
   *  트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다
   *  트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다
   *
   * 다음은 cap=4 일 때
   * 배달 및 수거할 재활용 택배 상자 개수
   * 집 #1	집 #2	집 #3	집 #4	집 #5
   * 배달	1개	0개	3개	1개	2개
   * 수거	0개	3개	0개	4개	0개
   *
   * 3개를 트럭에 실어 출발
   * 4번째 집에 택배 1개를 배달하고, 5번째 집에 택배 2개를 배달 // 거리 5
   * 4번째 집에서 빈 택배 상자 4개를 수거, 물류창고에 내리고, 택배 4개를 트럭에 싣습니다 // 거리 5
   * 3번째 집까지 이동, 3개번째 집에 택배 1개,  3번째 집에 택배 3개 // 거리 3
   * 돌아오면서 2번ㅈ집에서 수거 // 거리 3
   * 총 16
   *
   * 트럭에 실을 수 있는 1 ≤ cap ≤ 50
   * 집의 개수: n
   * 배달할 개수 deliveries
   * 빈 재활용 택배 상자의 개수: pickups
   */

}
