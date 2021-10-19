package zb2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class Test1 {

    @Test
    public void asdf() throws Exception {
        Cisar11.solution("cloud", 1); // dmpve
        Cisar11.solution("cloud", 3); // fupxm
    }


}
class Cisar11 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strLengthAndShift = br.readLine();

        String[] strings = strLengthAndShift.split(" ");
        String shift = strings[1];

        String string = br.readLine();
        solution(string, Integer.parseInt(shift));
    }
    public static void solution(String str, int shift) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int zAscii = 'z' - '0';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int pow = (int) (Math.pow(shift, i + 1) % alphabet.length());
            char element = str.charAt(i);
            int i1 = (element - '0') + pow;
            if (i1 > zAscii) {
                i1 = i1 - alphabet.length();
            }
            sb.append((char) (i1 + '0'));
        }
        System.out.println(sb);
    }
}