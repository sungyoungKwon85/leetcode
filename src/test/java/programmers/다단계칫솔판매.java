package programmers;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class 다단계칫솔판매 {

  /**
   * 민호는 다단계 조직을 이용하여 칫솔을 판매하고 있습니다.
   *
   * 모든 판매원은 칫솔의 판매에 의하여 발생하는 이익에서 10% 를 계산하여 자신을 조직에 참여시킨 추천인에게 배분하고 나머지는 자신이 가집니다.
   * 자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10% 까지 자신에 이익이 됩니다.
   * 단, 10% 를 계산할 때에는 원 단위에서 절사하며, 10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
   *
   * 개당 100 원
   * 판매원	판매 수량	이익금
   * young	12	1,200 원
   * john	4	400 원
   * tod	2	200 원
   * emily	5	500 원
   * mary	10	1,000 원
   *
   *           center
   * john                   mary
   *               edward  emaily   jaimie
   *           sam  young              tod
   * 판매원 young 에 의하여 1,200 원의 이익이 발생했습니다.
   * young 은 이 중 10% 에 해당하는 120 원을, 자신을 조직에 참여시킨 추천인인 edward 에게 배분하고 자신은 나머지인 1,080 원을 가집니다.
   * edward 는 young 에게서 받은 120 원 중 10% 인 12 원을 mary 에게 배분하고 자신은 나머지인 108 원을 가집니다.
   * 12 원을 edward 로부터 받은 mary 는 10% 인 1 원을 센터에 (즉, 민호에게) 배분하고 자신은 나머지인 11 원을 가집니다.
   *
   * 입출력 예
   * enroll	referral	seller	amount	result
   * ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]
   *  ["young", "john", "tod", "emily", "mary"]	[12, 4, 2, 5, 10]	[360, 958, 108, 0, 450, 18, 180, 1080]
   * ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]
   *  ["sam", "emily", "jaimie", "edward"]	[2, 3, 5, 4]	[0, 110, 378, 180, 270, 450, 0, 0]
   *
   * enroll 에 등장하는 이름은 조직에 참여한 순서에 따릅니다.
   */

  @Test
  public void test1(){
    String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}; // 민호를 제외한 조직 구성원
    String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}; // 배열 enroll 내에서 i 번째에 있는 판매원을 조직에 참여시킨 사람
    String[] seller = {"young", "john", "tod", "emily", "mary"}; // 어느 판매원에 의한 것인지
    int[] amount = {12, 4, 2, 5, 10}; // 판매량
    int[] result = {360, 958, 108, 0, 450, 18, 180, 1080};
    Assert.assertArrayEquals(result, solution(enroll, referral, seller, amount));
  }
  @Test
  public void test2(){
    String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}; // 민호를 제외한 조직 구성원
    String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}; // 배열 enroll 내에서 i 번째에 있는 판매원을 조직에 참여시킨 사람
    String[] seller = {"sam", "emily", "jaimie", "edward"}; // 어느 판매원에 의한 것인지
    int[] amount = {2, 3, 5, 4}; // 판매량
    int[] result = {0, 110, 378, 180, 270, 450, 0, 0};
    Assert.assertArrayEquals(result, solution(enroll, referral, seller, amount));
  }

  public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    // 1. 판매원들을 0으로 세팅
    // 2. BrushNode로 관계 세팅
    // 3. 타고타고 분배
    Map<String, BrushNode> map = new HashMap();
    String center = "center";
    map.put(center, new BrushNode(center, 0));
    for (int i = 0; i < enroll.length; i++) {
      String enrollName = enroll[i];
      String parentName = referral[i];
      BrushNode current = new BrushNode(enrollName, 0);
      if ("-".equals(parentName)) {
        current.prev = map.get(center);
        map.put(enrollName, current);
      } else {
        current.prev = map.get(parentName);
        map.put(enrollName, current);
      }
    }

    for (int i = 0; i < seller.length; i++) {
      BrushNode current = map.get(seller[i]);
      BrushNode parent = current.prev;
      int price = amount[i] * 100;
      if (parent == null) {
        current.val += price;
        continue;
      }

      while(current != null) {
        parent = current.prev;
        int tenPercentage = (int) (price * 0.1);
        if (parent != null) current.val += (price - tenPercentage);
        else current.val += price;

        current = parent;
        price = tenPercentage;
      }
    }

    int[] result = new int[enroll.length];
    for (int i = 0; i < enroll.length; i++) {
      result[i] = map.get(enroll[i]).val;
    }
    return result;
  }

  public class BrushNode {
    String name;
    int val;
    BrushNode prev;

    public BrushNode(String name, int val) {
      this.name = name;
      this.val = val;
    }
  }
}


























