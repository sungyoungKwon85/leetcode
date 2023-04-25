package nexon2023;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
public class One {

  @Test
  public void test1(){
    List<Integer> a = Arrays.asList(1, 2, 3);
    List<Integer> rotate = Arrays.asList(1, 2, 3, 4);
    List<Integer> result = Arrays.asList(1, 0, 2, 1);
    Assert.assertArrayEquals(result.toArray(), getMaxElementIndexes(a, rotate).toArray());
  }
  @Test
  public void test2(){
    List<Integer> a = Arrays.asList(1, 2, 4, 3);
    List<Integer> rotate = Arrays.asList(0, 2);
    List<Integer> result = Arrays.asList(2, 0);
    Assert.assertArrayEquals(result.toArray(), getMaxElementIndexes(a, rotate).toArray());
  }
  @Test
  public void test3(){
    List<Integer> a = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> rotate = Arrays.asList(1, 2, 3, 4);
    List<Integer> result = Arrays.asList(3, 2, 1, 0);
    Assert.assertArrayEquals(result.toArray(), getMaxElementIndexes(a, rotate).toArray());
  }
  @Test
  public void test4(){
    List<Integer> a = Arrays.asList(1, 2, 5, 4, 3);
    List<Integer> rotate = Arrays.asList(1, 2, 3, 4);
    List<Integer> result = Arrays.asList(1, 0, 4, 3);
    Assert.assertArrayEquals(result.toArray(), getMaxElementIndexes(a, rotate).toArray());
  }
  @Test
  public void test5(){
    List<Integer> a = Arrays.asList(5, 1, 2, 4, 3);
    List<Integer> rotate = Arrays.asList(1, 2, 3, 4);
    List<Integer> result = Arrays.asList(4, 3, 2, 1);
    Assert.assertArrayEquals(result.toArray(), getMaxElementIndexes(a, rotate).toArray());
  }
  @Test
  public void test6(){
    List<Integer> a = Arrays.asList(5);
    List<Integer> rotate = Arrays.asList(1, 2, 3, 4);
    List<Integer> result = Arrays.asList(0, 0 ,0 ,0);
    Assert.assertArrayEquals(result.toArray(), getMaxElementIndexes(a, rotate).toArray());
  }

  public static List<Integer> getMaxElementIndexes(List<Integer> a, List<Integer> rotate) {
    // Write your code here
    final int len = a.size();
    int max = 0;
    int maxIndex = 0;
    for (int i = 0; i < len; i++) {
      if (max < a.get(i)) {
        max = a.get(i);
        maxIndex = i;
      }
    }

    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < rotate.size(); i++) {
      int movedIndex = (len + maxIndex - (rotate.get(i) % len)) % len;
      result.add(movedIndex);
    }

    return result;
  }
}
