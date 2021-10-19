package zb2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Test2 {

    @Test
    public void asdf() throws Exception {
        Cisar13.solution(Arrays.asList("1 3 5", "2 2 4", "3 5 6", "4 7 8", "5 7 10")); // 3 2
        Cisar13.solution(Arrays.asList( // 4 5
            "1 3 5", "2 1 4", "3 5 7", "4 0 6", "5 3 8", "6 5 9", "7 6 10", "8 8 11", "9 8 12", "10 2 13", "11 12 24"));
    }


}

class Cisar13 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        List<String> list = new ArrayList<>();
        for (int i = 0; (i < Integer.parseInt(input)); i++) {
            String string = br.readLine();
            list.add(string);
        }
        solution(list);
    }

    public static void solution(List<String> list) {
        int availableUsers = 0;
        Map<Integer, Integer> oneMap = new HashMap<>();
        Map<Integer, Integer> maxMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String[] strings = list.get(i).split(" ");
            int in = Integer.parseInt(strings[1]);
            int out = Integer.parseInt(strings[2]);
            boolean available = true;

            for (int key = in; key < out; key++) {
                Integer value = oneMap.getOrDefault(key, 0);
                Integer valueMax = maxMap.getOrDefault(key, 0);
                if (value != 0) {
                    available = false;
                }
                maxMap.put(key, valueMax + 1);
            }

            if (available) {
                availableUsers++;
                for (int j = in; j < out; j++) {
                    oneMap.put(j, oneMap.getOrDefault(j, 0) + 1);
                }
            }
        }
        System.out.println(availableUsers);
        System.out.println(Collections.max(maxMap.values()));
    }
}