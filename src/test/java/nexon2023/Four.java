package nexon2023;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import org.junit.Test;

public class Four {

  @Test
  public void test() {
    String[] arr = {"a.b.c - - [01/Jul/1995:00:00:06 -0400] \"GET /a/b/ HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:00:00:06 -0400] \"GET /a/b/aa.html HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:00:00:06 -0400] \"GET /a/b/aa.gif HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:00:00:06 -0400] \"GET /a/b/casda.GIF HTTP/1.0\" 200 3985",
        "c.c.d - - [01/Jul/1995:00:00:06 -0400] \"GET /a/b/casda.GIF HTTP/1.0\" 404 3985"};
    for (String a : arr) {
      if (a.contains("\"GET ") && (a.contains(".gif ") || a.contains(".GIF ")) && a.contains(" 200 ")) {
        System.out.println(a);
        int start = a.indexOf("GET ") + 4;
        int end = a.indexOf(".gif", start) + 4;
        String substring = a.substring(start, end);
        String substring1 = substring.substring(substring.lastIndexOf("/") + 1);
      }
    }
  }

  private static final Scanner scan = new Scanner(System.in);

  //a.b.c - - [01/Jul/1995:00:00:06 -0400] "GET /a/b/ HTTP/1.0" 200 3985
  //c.c.d - - [01/Jul/1995:00:00:06 -0400] "GET /a/b/aa.html HTTP/1.0" 200 3985
  //c.c.d - - [01/Jul/1995:00:00:06 -0400] "GET /a/b/aa.gif HTTP/1.0" 200 3985
  //c.c.d - - [01/Jul/1995:00:00:06 -0400] "GET /a/b/casda.GIF HTTP/1.0" 200 3985
  //c.c.d - - [01/Jul/1995:00:00:06 -0400] "GET /a/b/casda.GIF HTTP/1.0" 404 3985

  //livevideo.GIF
  //count.gif
  //NASA-logosmall.gif
  //KSC-logosmall.gif
  public static final String SP_GET = "GET ";
  public static final String SP_GIF_L = ".gif ";
  public static final String SP_GIF_U = ".GIF ";
  public static final String SP_200 = " 200 ";

  public static void main(String[] args) {
    String path = System.getProperty("user.dir") + "/";
    String filename = "hosts_access_log_00.txt";
//    String filename = scan.nextLine();
    String outputFilename = "gifs_" + filename;

    HashSet<String> set = new HashSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path + filename))) {
      String line;
      while (true) {
        line = br.readLine();
        if (line == null) {
          break;
        }
        if (line.contains(SP_GET) && (line.contains(SP_GIF_L) || line.contains(SP_GIF_U)) && line.contains(SP_200)) {
          int start = line.indexOf(SP_GET) + 4;
          int end = line.indexOf(SP_GIF_L, start);
          if (end == -1) {
            end = line.indexOf(SP_GIF_U, start);
          }
          end += 4;
          String substring = line.substring(start, end);
          String finalStr = substring.substring(substring.lastIndexOf("/") + 1);
          set.add(finalStr);
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename))) {
      for (String str: set) {
        bw.write(str + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * response list로 구성된 로그파일이 있음
   * 새로운 파일을 만들어라
   *  gif
   *  유니크
   * GET 요청으로 왔음
   * 응답은 200으로 함
   */
}
