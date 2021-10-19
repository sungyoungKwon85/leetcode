package todayhome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test02 {

    @Test
    public void test1() throws Exception {
        assertEquals(solution(4, Arrays.asList(1,2,5,10)), 17);
        assertEquals(solution(7, Arrays.asList(10,20,30,40,50,60,70)), 280);
    }

    private int solution(int peopleNumber, List<Integer> costs) {
        List<Integer> sorted = costs.stream().sorted().collect(Collectors.toList());

        Integer count = 0;

        Integer first;
        Integer second;
        Integer last;
        Deque deque = new LinkedList<Integer>();
        deque.addAll(sorted);

        while(!deque.isEmpty()) {
            first = (Integer) deque.pollFirst();
            second = (Integer) deque.pollFirst();
            count += second;

            if (deque.isEmpty()) break;

            count += first;
            deque.offerFirst(first);

            last = (Integer) deque.pollLast();
            deque.pollLast();
            count += last;

            if (deque.isEmpty()) break;

            deque.pollFirst();
            deque.offerFirst(second);
            deque.offerFirst(first);
            count += second;
        }

        return count;
    }

}

class Main112 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println("Hello Goorm! Your input is " + input);
        int peopleNumber = Integer.parseInt(input);

        List<Integer> costs = new ArrayList<>();
        for (int i = 0; i < peopleNumber; i++) {
            String cost = br.readLine();
            costs.add(Integer.parseInt(cost));
        }
        Main112 main = new Main112();
        System.out.println(main.solution(costs));
    }

    private int solution(List<Integer> costs) {
        List<Integer> sorted = costs.stream().sorted().collect(Collectors.toList());

        Integer count = 0;

        Integer first;
        Integer second;
        Integer last;
        Deque deque = new LinkedList<Integer>();
        deque.addAll(sorted);

        while(!deque.isEmpty()) {
            first = (Integer) deque.pollFirst();
            second = (Integer) deque.pollFirst();
            count += second;

            if (deque.isEmpty()) break;

            count += first;
            deque.offerFirst(first);

            last = (Integer) deque.pollLast();
            deque.pollLast();
            count += last;

            if (deque.isEmpty()) break;

            deque.pollFirst();
            deque.offerFirst(second);
            deque.offerFirst(first);
            count += second;
        }

        return count;
    }


}