package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class Sum3 {


  @Test
  public void test1(){
//    Integer[][] result = new Integer[][]{{-1, -1, 2}, {-1, 0, 1}};
    List<List<Integer>> res = threeSum(new int[]{-1, 0, 1, 2, -1, -4});

    Assert.assertEquals(true, (res.get(0).contains(-1) && res.get(0).contains(1) && res.get(0).contains(0))
        || (res.get(1).contains(-1) && res.get(1).contains(1) && res.get(1).contains(0)));
    Assert.assertEquals(true, (res.get(0).stream().filter(e -> e == -1).count() == 2 && res.get(0).contains(2))
        || (res.get(1).stream().filter(e -> e == -1).count() == 2 && res.get(1).contains(2)));
  }
  @Test
  public void test2(){
    Assert.assertEquals(0, threeSum(new int[]{0,1,1}).size());
  }
  @Test
  public void test4(){
    List<List<Integer>> res = threeSum(new int[]{-1, 0, 1, 2, -3, 3, -2});
    // -1 0 1
    // 0 3 -3
    // 0 2 -2
    // -1 3 -2
    // 1 2 -3
    // center = len / 2

  }

  @Test
  public void test5() {
    List<List<Integer>> res = threeSum(new int[]{0, 0, 0, 0});
  }

  @Test
  public void test3(){
    Integer[][] result = new Integer[][]{{0, 0, 0}};
    List<List<Integer>> res = threeSum(new int[]{0, 0, 0});
    Assert.assertArrayEquals(result[0], res.get(0).toArray());
  }
  public List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> result = new HashSet<>();

    int center = nums.length / 2;
    int left = 0;
    int right = nums.length - 1;
    int direction = -1;
    int step = 1;

    int[] sortedNums = Arrays.stream(nums).sorted().toArray();

    while(center != 0 && center != nums.length - 1) {
      int c = sortedNums[center];
      int l = sortedNums[left];
      int r = sortedNums[right];
      int sum = c + l + r;
      if (sum == 0) { // OK.
        result.add(Arrays.asList(sortedNums[left], sortedNums[center], sortedNums[right]));
        left++;
        right--;
      } else if (sum > 0) {
        right--;
      } else if (sum < 0) {
        left++;
      }

      if (left == center || right == center) {
        center = center + (step * direction);
        left = 0;
        right = nums.length - 1;
        step++;
        direction *= -1;
      }
    }
    return new ArrayList<>(result);
  }
}
/**
 * Given an integer array nums,
 * return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
