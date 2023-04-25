package nexon2023;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.junit.Test;

public class Five {

  //a.b.c - - [01/Jul/1995:05:01:16 -0400] "GET /a/b/ HTTP/1.0" 200 3985
  //c.c.d - - [02/Jul/1995:04:02:26 -0400] "GET /a/b/aa.html HTTP/1.0" 200 3985
  //c.c.d - - [03/Jul/1995:03:03:36 -0400] "GET /a/b/aa.gif HTTP/1.0" 200 3985
  //c.c.d - - [04/Jul/1995:02:04:46 -0400] "GET /a/b/casda.GIF HTTP/1.0" 200 3985
  //c.c.d - - [05/Jul/1995:01:05:56 -0400] "GET /a/b/casda.GIF HTTP/1.0" 404 3985

  //01/Jul/1995:00:00:06
  //01/Jul/1995:00:00:06
  //01/Jul/1995:00:00:06
  @Test
  public void test1(){
    String[] arr = {
        "a.b.c - - [01/Jul/1995:00:01:16 -0400] \"GET /a/b/ HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:01:02:26 -0400] \"GET /a/b/aa.html HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:02:03:36 -0400] \"GET /a/b/aa.gif HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:03:04:46 -0400] \"GET /a/b/casda.GIF HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:04:05:56 -0400] \"GET /a/b/casda.GIF HTTP/1.0\" 404 3985"};
    for (String a : arr) {
      System.out.println();
    }
  }

  private static final Scanner scan = new Scanner(System.in);

  public static void main(String args[]) throws Exception {
    // read the string filename
    String path = System.getProperty("user.dir") + "/";
    String filename = "hosts_access_log_01.txt";

//    String filename = scan.nextLine();
    String outputFilename = "req_" + filename;
    HashMap<String, Integer> map = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path + filename))) {
      String line;
      while (true) {
        line = br.readLine();
        if (line == null) {
          break;
        }

        String[] strings = line.split(" ");
        if (strings != null && strings.length > 3) {
          String dateStr = strings[3].substring(1);
          Integer value = map.get(dateStr);
          if (value == null) {
            map.put(dateStr, 1);
          } else {
            map.put(dateStr, value + 1);
          }
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename))) {
      List<String> collect = map.entrySet().stream()
          .filter(e -> e.getValue() > 1)
          .map(e -> e.getKey())
          .collect(Collectors.toList());
      for (String str: collect) {
        bw.write(str + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 텍스트파일 읽어서 타임스탬프를 봐라
   * 그리고 타임스탬프의 리스트를 파일로 떨궈라
   *
   * 출력 파일이름은 req_filename
   *
   * 각 줄은 July 1995..
   *
   * hostname - - timestamp request HTTPresponseCode Bytes
   *
   * [DD/mmm/YYYY:HH:MM:SS -0400]
   *
   * 1회 초과한 것만
   */
}
