package todayhome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test01 {

    @Test
    public void test1() throws Exception {
        Main111.main(new String[]{"a2c9b3c2z0"}); // a2c11b3z0
        Main111.main(new String[]{"a3c11d1c3d3"}); // a3c14d4
        Main111.main(new String[]{"a1a2a1a1"}); // a5
        Main111.main(new String[]{"a123123"}); // a123123
        Main111.main(new String[]{"a123b6"}); // a123b6
        Main111.main(new String[]{"a0000"}); // a0
    }

}

class Main111 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println("Hello Goorm! Your input is " + input);

        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length;) {
            char character = chars[i];
            int index = i+1;
            String number = "0";
            while(index <= chars.length - 1) {
                char next = chars[index];
                if (next >= '0' && next <= '9') {
                    number += next;
                    index++;
                } else {
                    break;
                }
            }
            Integer savedNumber = map.getOrDefault(character, 0);
            map.put(character, savedNumber + Integer.parseInt(number));
            i = index;
        }
        String collect = map.entrySet().stream().map(e -> String.valueOf(e.getKey()) + e.getValue())
            .collect(Collectors.joining());
        System.out.println(collect);

    }
}
