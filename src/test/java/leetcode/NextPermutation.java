package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class NextPermutation {

  @Test
  public void test0() {
    int[] arr = {1, 3, 2};
    nextPermutation(arr);
    int[] res = {2, 1, 3};
    Assert.assertArrayEquals(res, arr);
  }

  @Test
  public void test1() {
    int[] arr = {1, 2, 3};
    nextPermutation(arr);
    int[] res = {1, 3, 2};
    Assert.assertArrayEquals(res, arr);
  }
  @Test
  public void test2() {
    int[] arr = {2, 3, 1};
    nextPermutation(arr);
    int[] res = {3, 1, 2};
    Assert.assertArrayEquals(res, arr);
  }
  @Test
  public void test3() {
    int[] arr = {3, 2, 1};
    nextPermutation(arr);
    int[] res = {1, 2, 3};
    Assert.assertArrayEquals(res, arr);
  }
  @Test
  public void test4() {
    int[] arr = {1, 1, 5};
    nextPermutation(arr);
    int[] res = {1, 5, 1};
    Assert.assertArrayEquals(res, arr);
  }
  @Test
  public void test5() {
    int[] arr = {1, 2, 3, 4};
    nextPermutation(arr);
    int[] res = {1, 2, 4, 3};
    Assert.assertArrayEquals(res, arr);
  }
  @Test
  public void test6() {
    int[] arr = {4, 2, 3, 1};
    nextPermutation(arr);
    int[] res = {4, 3, 1, 2};
    Assert.assertArrayEquals(res, arr);
  }
  @Test
  public void test7() {
    int[] arr = {1, 2};
    nextPermutation(arr);
    int[] res = {2, 1};
    Assert.assertArrayEquals(res, arr);
  }

  @Test
  public void test8() {
    int[] arr = {4,2,0,2,3,2,0};
    nextPermutation(arr);
    int[] res = {4,2,0,3,0,2,2};
    Assert.assertArrayEquals(res, arr);
  }

  public void nextPermutation(int[] nums) {
    int len = nums.length;
    int from = 0;
    int to = 0;
    boolean done = false;

    int step = len - 1;
    for (int i = len-2; i >= 0; i--) {
      for (int j = len-1; j >= step; j--) {
        int first = nums[i];
        int second = nums[j];
        if (first < second) {
          from = i;
          to = j;
          done = true;
          break;
        }
      }
      step--;
      if (done) break;
    }
    if (from == 0 && to == 0) {
      from = -1;
    }
    else {
      int temp = nums[from];
      nums[from] = nums[to];
      nums[to] = temp;
    }
    for (int i = from + 1; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        int first = nums[i];
        int second = nums[j];
        if (first > second) {
          int tmp = first;
          nums[i] = second;
          nums[j] = tmp;
        }
      }
    }
    System.out.println();
  }
}
/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3],
 * the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 *
 * The replacement must be in place and use only constant extra memory.
 */
