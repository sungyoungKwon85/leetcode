package codility.nvfin;

import java.util.Arrays;

import org.junit.Test;

public class ATest {

    @Test
    public void teset1() {
        System.out.println(solution(new int[]{1, 3, 6, 4, 1, 2}));
    }

    public int solution(int[] A) {
        Arrays.sort(A);

        int count = 1;
        boolean isMatch = false;

        while (true) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == count) {
                    isMatch = true;
                    break;
                }
            }
            if (isMatch) {
                isMatch = false;
            } else {
                return count;
            }
            count++;
        }
    }
}
