package wb2021;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: kkwonsy
 * Date: 2021/07/24 4:17 오후
 */
public class Test0724 {
    @Test
    public void test1() throws Exception {
        System.out.println(solution1(3, 2, new int[]{1, 1, 1, 0, 2}));

        System.out.println(solution1(3, 4, new int[]{1, 1, 1, 2, 2}));

        System.out.println(solution1(3, 4, new int[]{1, 2, 1, 1, 2}));

        System.out.println(solution1(3, 5, new int[]{1, 2, 2, 1, 2}));

        System.out.println(solution1(3, 2, new int[]{1, 1, 1, 1, 1}));

        System.out.println(solution1(3, 2, new int[]{2, 1, 1, 0, 1}));


        System.out.println(solution1(2, 3, new int[]{0, 0, 1, 1, 2})); // IMPOSSIBLE

        System.out.println(solution1(2, 2, new int[]{2, 0, 2, 0}));
    }

    private static final String ZERO = "0";
    private static final String ONE = "1";
    public String solution1(int U, int L, int[] C) {
        // 2줄, n개의 컬럼인 보드
        // 0과1로 구성
        // 0번째 줄의 합은 U와 같음
        // 1번째 줄의 합은 L과 같음
        // K번째 컬럼의 합은 C[K]와 같음
        // 정답이 여러개면 아무거나
        // 없으면 IMPOSSIBLE

        final String impossible = "IMPOSSIBLE";
        final int sum = Arrays.stream(C).sum();
        if (U + L != sum) {
            return impossible;
        }

        Map<Integer, String> upper = new HashMap<>();
        Map<Integer, String> lower = new HashMap<>();
        int upperSum = 0;
        int lowerSum = 0;

        for (int i = 0; i < C.length; i++) {
            if (C[i] == 0) {
                upper.put(i, ZERO);
                lower.put(i, ZERO);
            } else if (C[i] == 1) {
                if (upperSum != U) {
                    upperSum = doForOne(upper, lower, upperSum, i);
                } else {
                    lowerSum = doForOne(lower, upper, lowerSum, i);
                }
            } else if (C[i] == 2) {
                if (upperSum == U) {
                    lowerSum = switchForTwo(upper, lower, lowerSum, i);
                } else if (lowerSum == L) {
                    upperSum = switchForTwo(lower, upper, upperSum, i);
                } else {
                    upper.put(i, ONE);
                    lower.put(i, ONE);
                    upperSum++;
                    lowerSum++;
                }
            }
        }

        return upper.values().stream().collect(Collectors.joining())
                + ","
                + lower.values().stream().collect(Collectors.joining());
    }

    private int doForOne(Map<Integer, String> upper, Map<Integer, String> lower, int upperSum, int i) {
        upper.put(i, ONE);
        lower.put(i, ZERO);
        upperSum++;
        return upperSum;
    }

    private int switchForTwo(Map<Integer, String> map1, Map<Integer, String> map2, int sum, int i) {
        final Integer index = getIndexOfOne(map1);
        if (index == -1) {
            return sum;
        }

        map1.put(index, ZERO);
        map1.put(i, ONE);
        map2.put(index, ONE);
        map2.put(i, ONE);
        return sum + 2;
    }

    private Integer getIndexOfOne(Map<Integer, String> map) {
        return map.entrySet().stream()
                .filter(e -> e.getValue() == ONE)
                .map(e -> e.getKey()).findFirst()
                .orElse(-1);
    }

}
