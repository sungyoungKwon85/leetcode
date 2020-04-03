import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class TwoSum {

    /**
     * Given an array of integers,
     * return indices of the two numbers such that they add up to a specific target.
     *
     * You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     *
     * Example:
     *
     * Given nums = [2, 7, 11, 15], target = 9,
     *
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    @Test
    public void test1() {
        int[] nums = {2, 7, 11, 15};
        int[] result = betterSolution(nums, 9);
        Assert.assertEquals(result[0], 0);
        Assert.assertEquals(result[1], 1);
    }

    @Test
    public void test2() {
        int[] nums2 = {3, 3};
        int[] result2 = betterSolution(nums2, 6);
        Assert.assertEquals(result2[0], 0);
        Assert.assertEquals(result2[1], 1);
    }

    @Test
    public void test3() {
        int[] nums2 = {-3, 4, 3, 90};
        int[] result2 = betterSolution(nums2, 0);
        Assert.assertEquals(result2[0], 0);
        Assert.assertEquals(result2[1], 2);
    }

    public int[] betterSolution(int[] nums, int target) {
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("");
    }
}
