package codility.nvfin;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

// 20200517 nv.fin.3
public class DTest {

    @Test
    public void test9() {
        int solution = solution(new int[]{1, 3, 2, 1}, new int[]{4, 2, 5, 3, 2});
        Assertions.assertThat(solution).isEqualTo(2);
    }

    @Test
    public void test1() {
        int solution = solution(new int[]{2, 1}, new int[]{3, 3});
        Assertions.assertThat(solution).isEqualTo(-1);
    }

    @Test
    public void test2() {
        int solution = solution(new int[]{2, 1, 1, 2, 3}, new int[]{3});
        Assertions.assertThat(solution).isEqualTo(3);
    }

    @Test
    public void test3() {
        int solution = solution(new int[]{2, 1, 1, 2, 3}, new int[]{3, 4, 3, 3, 2});
        Assertions.assertThat(solution).isEqualTo(2);
    }

    @Test
    public void test4() {
        int solution = solution(new int[]{2, 3}, new int[]{3, 4, 3, 3, 2});
        Assertions.assertThat(solution).isEqualTo(2);
    }

    @Test
    public void test5() {
        int solution = solution(new int[]{2, 3, 9, 9, 9, 9, 1}, new int[]{3, 4, 3, 3, 2, 9, 9, 9, 9, 99, 99, 9, 1});
        Assertions.assertThat(solution).isEqualTo(1);
    }

    // fail code
    @Test
    public void test6() {
        int solution = solution(new int[]{2, 3}, new int[]{1, 1, 2, 3});
        Assertions.assertThat(solution).isEqualTo(2);
    }

    public int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        ;
        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0;
        for (int k = 0; k < n; k++) {
            while (i < m - 1 && B[i] < A[k]) {
                i += 1;
            }

            if (A[k] == B[i]) {
                return A[k];
            }

        }
        return -1;
    }

}
