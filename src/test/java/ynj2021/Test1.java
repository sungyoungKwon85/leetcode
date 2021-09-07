package ynj2021;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test1 {
    @Test
    public void asdf() throws Exception {
        // 두개의 정수, 재정렬함으로써 하나로부터 10진수 대표를 얻을 수 있다???
        // 123, 213은 siblings이다.
        // 535, 355도
        // 553을 family라고 함
        // 355, 535, 553세개가 됨.
        // 가장큰 수를 리턴하라.
        // 553 -> 553
        // 213 -> 321

        System.out.println(func1(553));
        System.out.println(func1(213));
    }

    private int func1(int N) {
        char[] chars = String.valueOf(N).toCharArray();
        Arrays.sort(chars);
        String s = new StringBuilder(String.valueOf(chars)).reverse().toString();
        return Integer.parseInt(s);
    }


    @Test
    public void test2() throws Exception {
        // 가장 작은 정수를 뱉음
        // 정수의 개개인 자릿수의 합이 N이 되는..
        // 16 -> 79
        // 19 -> 199
        // 7 -> 7
        System.out.println(func2(16));
        System.out.println(func2(19));
        System.out.println(func2(7));
    }

    private int func2(int N) {
        int unit = 9;
        if (N <= unit) {
            return N;
        }

        StringBuilder sb = new StringBuilder();
        int remains = N;

        while (remains >= 9) {
            sb.append(9);
            remains -= unit;
        }
        sb.append(remains);

        return Integer.parseInt(sb.reverse().toString());
    }


    @Test
    public void test3() throws Exception {
        // K만큼 연속된 요소를 A로부터 지우고자함
        // 남은 요소의 차이가 적어야함
        // 그 차이를 리턴
        // {5,3,6,1,3}, 2 -> 2 (6,1 빼면 5,3,3이니까 2차이)
        // {8,8,4,3}, 2 -> 0
        // {8,7,4,8}, 2 -> 0
        // {3,5,1,3,9,8}, 4 -> 1
        // {8,5,1,3,9,8}, 4 -> 0
        System.out.println(func3(new int[]{5, 3, 6, 1, 3}, 2));
        System.out.println(func3(new int[]{8, 8, 4, 3}, 2));
        System.out.println(func3(new int[]{8, 7, 4, 8}, 2));
        System.out.println(func3(new int[]{3, 5, 1, 3, 9, 8}, 4));
        System.out.println(func3(new int[]{8, 5, 1, 3, 9, 8}, 4));
    }

    private int func3(int[] A, int K) {
        int removeFrom = 0;
        int removeTo = K-1;
        int gap = 9;

        while (removeTo < A.length) {
            int[] ints = new int[A.length-K];
            int newIndex = 0;
            for (int j = 0; j < A.length; j++) {
                if (!(j >= removeFrom && j <= removeTo)) {
                    ints[newIndex++] = A[j];
                }
            }

            int max = -1;
            int min = 10;
            for (int anInt : ints) {
                if (min > anInt) min = anInt;
                if (max < anInt) max = anInt;
            }
            int newGap = max - min;
            if (gap > newGap) gap = newGap;

            removeFrom++;
            removeTo++;
        }

        return gap;
    }


    @Test
    public void test4() throws Exception {
        System.out.println(func4(new int[]{13,7,2,8,3})); // 3
        System.out.println(func4(new int[]{1,2,4,8})); // 1
        System.out.println(func4(new int[]{16,16})); // 2

    }

    private int func4(int[] A) {
        Arrays.sort(A);
        final int maxLength = Integer.toBinaryString(A[A.length - 1]).length();

        List<char[]> list = Arrays.stream(A).mapToObj(e -> {
            String binary = Integer.toBinaryString(e);
            int bLength = binary.length();
            int bIndex = 0;
            char[] chars = new char[maxLength];
            for (int i = 0; i < maxLength; i++) {
                if (bLength++ < maxLength) {
                    chars[i] = '0';
                } else {
                    chars[i] = binary.charAt(bIndex++);
                }
            }
            return chars;
        }).collect(Collectors.toList());

        int result = 0;
        for (int i = 0; i < maxLength; i++) {
            int count = 0;
            for (char[] chars: list) {
                if (chars[i] == '1') count++;
            }
            if (result < count) result = count;
        }
        return result;
    }
}
