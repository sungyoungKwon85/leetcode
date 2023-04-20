package leetcode;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class Sum3Closest {

  @Test
  public void test1(){
    Assert.assertEquals(2, threeSumClosest(new int[]{-1,2,1,-4}, 1));
  }
  @Test
  public void test2(){
    Assert.assertEquals(0, threeSumClosest(new int[]{0,0,0}, 1));
  }
  @Test
  public void test4(){
    Assert.assertEquals(0, threeSumClosest(new int[]{0,0,0}, 10000));
  }
  @Test
  public void test3(){
    Assert.assertEquals(4, threeSumClosest(new int[]{-1,2,1,-4,3,7,-5}, 4));
  }

  public int threeSumClosest(int[] nums, int target) {
    if (nums.length == 3) {
      return Arrays.stream(nums).sum();
    }

    int[] sorted = Arrays.stream(nums).sorted().toArray();
    int center = nums.length / 2;
    int left = 0;
    int right = nums.length - 1;
    int closest = 1000;
    int step = 1;
    int direction = -1;

    while (center != 0 && center != nums.length - 1) {
      int sum = sorted[left] + sorted[right] + sorted[center];
      if (sum == target) return sum;

      int absNow = Math.abs(target - sum);
      int absOrigin = Math.abs(target - closest);
      if (absNow < absOrigin) {
        closest = sum;
      }

      if (sum > target) {
        right--;
      } else if (sum < target) {
        left++;
      }

      if (right == center || left == center) {
        left = 0;
        right = sorted.length - 1;
        center = center + (step* direction);
        step++;
        direction *= -1;
      }
    }

    return closest;
  }

}
/**
 * Given an integer array nums of length n and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 */