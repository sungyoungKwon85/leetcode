package li2020;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * User: kkwonsy
 * Date: 2020/07/21 9:12 오후
 */
public class Test1 {

    @Test
    public void test1() {
        String input = "interview";
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        System.out.println(new StringBuilder().append(chars).reverse().toString());
    }


}
