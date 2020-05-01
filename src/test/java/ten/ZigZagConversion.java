package ten;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 */
public class ZigZagConversion {

    @Test
    public void test1() {
        Assertions.assertThat(convert("PAYPALISHIRING", 3)).isEqualTo("PAHNAPLSIIGYIR");
    }
    @Test
    public void test2() {
        Assertions.assertThat(convert("PAYPALISHIRING", 4)).isEqualTo("PINALSIGYAHRPI");
    }
    @Test
    public void test3() {
        Assertions.assertThat(convert("0123456789ABCD", 5)).isEqualTo("0817926A35BD4C");
    }
    @Test
    public void test4() {
        Assertions.assertThat(convert("ABC", 2)).isEqualTo("ACB");
    }
    @Test
    public void test5() {
        Assertions.assertThat(convert("ABCD", 2)).isEqualTo("ACBD");
    }
    @Test
    public void test6() {
        Assertions.assertThat(convert("ABCDEF", 4)).isEqualTo("ABFCED");
    }
    @Test
    public void test7() {
        Assertions.assertThat(convert("0123456789ABCD", 9)).isEqualTo("0123D4C5B6A798");
    }

    public String convert(String s, int numRows) {
        return "";
    }

    public String convert_fail(String s, int numRows) {
        if (numRows == 1) return s;
        if (s.length() == 1) return s;
        if (s.length() < numRows) return s;

        int length = s.length();
        int repeat = 0;
        while (repeat * (2 * numRows - 2) < length) {
            repeat++;
        }
        if (repeat * (2 * numRows - 2) != length) {
            repeat--;
        }

        int remain = length - (repeat * (2 * numRows - 2));

        int width = (int)(repeat * (numRows - 1) + Math.ceil((double) remain / (double) numRows));
        if (repeat == 0) {
            width = length - numRows + 1;
        }

        char arr[][] = new char[numRows][width];
        int tempRepeat = repeat;
        int stringIndex = 0;

        boolean flag = true;
        int rowExtra = 2;
        for (int column = 0; column < width; column++) {
            if (stringIndex == length) break;
            if (flag) {
                for (int row = 0; row < numRows; row++) {
                    if (stringIndex == length) break;
                    if (tempRepeat >= 0) {
                        arr[row][column] = s.charAt(stringIndex++);
                    }
                }
                if( numRows != 2) {
                    flag = false;
                }
            } else {
                if (numRows - rowExtra >= 0) {
                    arr[numRows - rowExtra][column] = s.charAt(stringIndex++);
                    rowExtra++;
                    if (numRows - rowExtra == 0) {
                        flag = true;
                        rowExtra = 2;
                        tempRepeat--;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sb.append(arr[i][j]);
                }
            }
        }


        return sb.toString();
    }
}
