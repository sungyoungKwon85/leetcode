package cp202108;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CpTest02 {

    @Test
    public void asdf() throws Exception {
        Solution solution = new Solution();
        assertEquals(solution.solution(new int[]{1, 2, 3}), 1);
        assertEquals(solution.solution(new int[]{3, -1, 0, 4}), 2);
        assertEquals(solution.solution(new int[]{1, 2, 3, 4}), 2);
        assertEquals(solution.solution(new int[]{1, 2, 3, 4, 5}), 3);
        assertEquals(solution.solution(new int[]{2, 1}), 1);
        assertEquals(solution.solution(new int[]{3, 2, 1}), 2);
    }

    // 수열
    // 증/감/증/감 지그재그 수열
    // 지그재그 수열을 만들기 위해 중간에 정수를 넣을 것인데 추가해야 하는 수의 개수의 최솟값을 구하기
    // 시작값이 짝수이면 증, 홀수이면 감
    // 안맞으면 count++
    class Solution {

        public int solution(int[] s) {


            int numberOfInsertion = 0;
            for (int i = 0; i < s.length - 1; i++) {
                int from = s[i];
                int to = s[i + 1];
                if (isTurnOfIncrease(i, numberOfInsertion)) {
                    if (from >= to) {
                        numberOfInsertion++;
                    }
                } else {
                    if (from <= to) {
                        numberOfInsertion++;
                    }
                }
            }
            return numberOfInsertion;
        }

        private boolean isTurnOfIncrease(int i, int numberOfInsertion) {
            return (i + numberOfInsertion) % 2 == 0 ? true : false;
        }
    }
}
