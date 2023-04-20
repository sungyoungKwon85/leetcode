package leetcode;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TwoSum {

  /**
   * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
   * You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * You can return the answer in any order.
   */

  @Test
  public void test1() throws Exception {
    int[] arr = {2, 7, 11, 15};
    int[] expected = {0, 1};
    int[] result = twoSum(arr, 9);
    for (int num: result) {
      System.out.print(num);
    }

    assertArrayEquals(expected, result);
  }

  @Test
  public void test2() throws Exception {
    int[] arr = {3,2,4};
    int[] expected = {1, 2};
    int[] result = twoSum(arr, 6);
    for (int num: result) {
      System.out.print(num);
    }

    assertArrayEquals(expected, result);
  }

  @Test
  public void test3() throws Exception {
    int[] arr = {3,3};
    int[] expected = {0, 1};
    int[] result = twoSum(arr, 6);
    for (int num: result) {
      System.out.print(num);
    }

    assertArrayEquals(expected, result);
  }

  private int[] twoSum(int[] nums, int target) {
    Map map = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (map.containsKey(num)) {
        return new int[]{(int) map.get(num), i};
      }
      map.put(target - num, i);
    }
    return null;
  }
}
