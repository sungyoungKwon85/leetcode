package codility.eleven2023;

import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class One {

  @Test
  public void test1(){
    Assert.assertEquals(321, solution(213));
  }
  @Test
  public void test2(){
    Assert.assertEquals(553, solution(553));
  }
  @Test
  public void test3(){
    Assert.assertEquals(-1, solution(Integer.MAX_VALUE));
  }
  @Test
  public void test4(){
    Assert.assertEquals(-1, solution(100000000));
  }
  @Test
  public void test6(){
    Assert.assertEquals(99999999, solution(99999999));
  }
  @Test
  public void test10(){
    Assert.assertEquals(99999998, solution(99989999));
  }
  @Test
  public void test7(){
    Assert.assertEquals(1, solution(1));
  }
  @Test
  public void test8(){
    Assert.assertEquals(11, solution(11));
  }
  @Test
  public void test9(){
    Assert.assertEquals(1100, solution(1001));
  }
  @Test
  public void test5(){
    int n = 1234;
    char[] chars = String.valueOf(n).toCharArray();
    System.out.println(Integer.parseInt(String.valueOf(chars)));
  }

  public int solution(int n) {
    // Implement your solution here
    if (n <= 10) return n;
    if (n >= 100000000) return -1;

    char[] chars = String.valueOf(n).toCharArray();
    int len = chars.length;
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        char first = chars[i];
        char second = chars[j];
        if (first < second) {
          chars[i] = second;
          chars[j] = first;
        }
      }
    }

    return Integer.parseInt(String.valueOf(chars));
  }

  /**
   * 두개의 양수 시블링
   * 십진법을 재정렬
   * 123, 213은 시블링
   * 355와 535는 시블링
   *
   * 553 -> 355, 535, 553
   *
   * 젤 큰거 리턴하기
   * 100,000,000 초과하면 -1 리턴하기
   *
   * integer max
   * 2147483647
   * 100000000
   */
}
