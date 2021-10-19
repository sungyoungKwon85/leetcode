package zb2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class Test3 {

    @Test
    public void asdf() throws Exception {
        Cisar14.solution(2, new int[]{1,3,5,7,9}); // 4
        Cisar14.solution(2, new int[]{1,3,5,7,12}); // 6
        Cisar14.solution(2, new int[]{1,3,5,7,32}); // 25
        Cisar14.solution(4, new int[]{1,3,5,7,12}); // 5
    }


}

class Cisar14 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String input2 = br.readLine();

        String[] split1 = input.split(" ");
        String[] split2 = input2.split(" ");
        int[] stairs = new int[split2.length];
        for (int i = 0; i < split2.length; i++) {
            stairs[i] = Integer.parseInt(split2[i]);
        }

        solution(Integer.parseInt(split1[0]), stairs);
    }
    public static void solution(int times, int[] stairs) {
        int biggest = 0;
        double avg = Math.ceil((stairs[stairs.length - 1] - (double)stairs[0]) / (double)times);
        for (int i = 0; i < stairs.length-1; i++) {
            int stair1 = stairs[i];
            int stair2 = stairs[i + 1];
            int diff = stair2 - stair1;
            if (diff > biggest) biggest = diff;
        }
        System.out.println(avg < biggest ? biggest : (int)avg);
    }
}