package li2020;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * User: kkwonsy
 * Date: 2020/07/21 9:33 오후
 */
public class Test2 {

    @Test
    public void test2() {
        Assertions.assertThat(solution("102", "989"))
                .isEqualTo("1091");
        Assertions.assertThat(solution("2813928498324982342494239423890442423", "999199328448234399938449840038934324328"))
                .isEqualTo("1002013256946559382280944079462824766751");
    }

    public String solution (String a, String b){
        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        boolean isOverTen = false;
        while (i >= 0 || j >= 0) {
            int eA;
            if (i < 0) {
                eA = 0;
            } else {
                eA = Integer.parseInt(String.valueOf(a.charAt(i)));
            }
            int eB;
            if (j < 0) {
                eB = 0;
            } else {
                eB = Integer.parseInt(String.valueOf(b.charAt(j)));
            }
            int sum = eA + eB;
            if (isOverTen) {
                sum++;
                isOverTen = false;
            }
            if (sum >= 10) {
                isOverTen = true;
                sum -= 10;
            }
            sb.append(sum);
            i--;
            j--;
        }
        if (isOverTen) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    @Test
    public void testtt() {
        String a = "7";
        int ii = Integer.parseInt(String.valueOf(a.charAt(0)));
        System.out.println(new StringBuilder().append(ii).toString());
    }
}
