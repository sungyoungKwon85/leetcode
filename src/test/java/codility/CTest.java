package codility;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

// 20200517 nv.fin.2
public class CTest {

    @Test
    public void test1() {
        int solution = solution(new int[]{3, 2, -2, 5, -3});
        Assertions.assertThat(solution).isEqualTo(3);
    }

    @Test
    public void test2() {
        int solution = solution(new int[]{1, 1, 2, -1, 2, -1});
        Assertions.assertThat(solution).isEqualTo(1);
    }

    @Test
    public void test3() {
        int solution = solution(new int[]{1, 2, 3, -4});
        Assertions.assertThat(solution).isEqualTo(0);
    }

    @Test
    public void test4() {
        int solution = solution(new int[]{-4, -3, 1, 2, 3});
        Assertions.assertThat(solution).isEqualTo(3);
    }

    @Test
    public void test5() {
        int solution = solution(new int[]{-4, -3, -2, -1, 0, 1});
        Assertions.assertThat(solution).isEqualTo(1);
    }

    @Test
    public void test5_1() {
        int solution = solution(new int[]{-1, 0, 1, 2, 3, 4});
        Assertions.assertThat(solution).isEqualTo(1);
    }

    @Test
    public void test6() {
        int solution = solution(new int[]{-3, -3, -3, -3, -3});
        Assertions.assertThat(solution).isEqualTo(0);
    }

    public int solution(int[] A) {
        Arrays.sort(A);
        int leftIndex = 0;
        int rightIndex = A.length - 1;
        while (true) {
            int left = A[leftIndex];
            int right = A[rightIndex];
            if (left == -1 * right) {
                return right;
            } else if (Math.abs(left) > Math.abs(right)) {
                leftIndex++;
            } else if (Math.abs(left) < Math.abs(right)) {
                rightIndex--;
            } else {
                leftIndex++;
                rightIndex--;
            }

            if (leftIndex >= rightIndex) {
                break;
            }
        }
        return 0;
    }

}
