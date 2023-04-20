package codility.eleven2023;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class Two {

  @Test
  public void test1(){
    Assert.assertEquals(false, solution(new int[]{7}));
  }
  @Test
  public void test2(){
    Assert.assertEquals(true, solution(new int[]{4,3}));
  }
  @Test
  public void test3(){
    Assert.assertEquals(true, solution(new int[]{11,1,8,12,14}));
    // 11, 12
  }
  @Test
  public void test6(){
    Assert.assertEquals(true, solution(new int[]{11,1,8,10,14}));
    // 11, 10
  }
  @Test
  public void test4(){
    Assert.assertEquals(true, solution(new int[]{4,10,8,5,9}));
    // 4,5 | 8,9 | 9,10
  }
  @Test
  public void test5(){
    Assert.assertEquals(false, solution(new int[]{5,5,5,5,5}));
  }
  @Test
  public void test7(){
    Assert.assertEquals(true, solution(new int[]{5,5,5,5,4}));
  }
  @Test
  public void test8(){
    Assert.assertEquals(true, solution(new int[]{5,5,5,5,2,2,2,2,2,7,7,7,7,7,7,7,7,9,9,9,9,9,9,9,4}));
  }
  @Test
  public void test9(){
    Assert.assertEquals(true, solution(new int[]{1000000000, 999999999}));
  }
  @Test
  public void test10(){
    Assert.assertEquals(false, solution(new int[]{1, 9}));
  }
  @Test
  public void test11(){
    Assert.assertEquals(true, solution(new int[]{1, 2}));
  }

  public boolean solution(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      int key = arr[i];
      Integer minusOne = map.get(key - 1);
      Integer plusOne = map.get(key + 1);
      if (minusOne == null && plusOne == null) {
        map.put(key, 0);
      } else {
        return true;
      }
    }
    return false;
  }

  /**
   * 적어도 두 요소가 1차이 나면 true?
   */
}
