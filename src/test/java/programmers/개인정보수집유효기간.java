package programmers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class 개인정보수집유효기간 {
  @Test
  public void test1(){
    String today = "2022.05.19";
    String[] terms = {"A 6", "B 12", "C 3"};
    String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
    int[] result = {1, 3};
    Assert.assertArrayEquals(solution(today, terms, privacies), result);
  }
  @Test
  public void test2(){
    String today = "2020.01.01";
    String[] terms = {"Z 3", "D 5"};
    String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
    int[] result = {1, 4, 5};
    Assert.assertArrayEquals(solution(today, terms, privacies), result);
  }

  @Test
  public void test3(){
    String today = "2022.02.28" ;
    String[] terms = {"A 23"};
    String[] privacies = {"2020.01.28 A"};
    int[] result = {1};
    Assert.assertArrayEquals(solution(today, terms, privacies), result);
  }

  @Test
  public void test4(){
    String today = "2022.12.08" ;
    String[] terms = {"A 18"};
    String[] privacies = {"2020.06.08 A"};
    int[] result = {1};
    Assert.assertArrayEquals(solution(today, terms, privacies), result);
  }

  @Test
  public void test(){
//    String result = "";
//    System.out.println(Stream.of(result.split("")).mapToInt(Integer::parseInt).toArray());
  }

  public int[] solution(String today, String[] terms, String[] privacies) {
    Map<String, Integer> termMap = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    LocalDate todayDate = LocalDate.parse(today, formatter);

    // 약관 종류와 유효기간을 매핑하여 Map에 저장
    for (String term : terms) {
      String[] tokens = term.split(" ");
      String type = tokens[0];
      int duration = Integer.parseInt(tokens[1]);
      termMap.put(type, duration);
    }

    List<Integer> result = new ArrayList<>();

    // 개인정보의 유효기간을 계산하여 파기 대상인 경우 결과 리스트에 추가
    for (int i = 0; i < privacies.length; i++) {
      String privacy = privacies[i];
      String[] tokens = privacy.split(" ");
      LocalDate collectedDate = LocalDate.parse(tokens[0], formatter);
      String type = tokens[1];
      int duration = termMap.get(type);
      LocalDate expirationDate = collectedDate.plusMonths(duration).minusDays(1);
      if (expirationDate.isBefore(todayDate)) {
        result.add(i + 1);
      }
    }

    // 결과 리스트를 오름차순으로 정렬하여 배열로 변환하여 반환
    Collections.sort(result);
    int[] answer = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      answer[i] = result.get(i);
    }
    return answer;
  }


  public int[] solution2(String today, String[] terms, String[] privacies) {
    final String DELIMITER = " ";

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    LocalDate now = LocalDate.parse(today, format);

    Map<String, Integer> termMap = new HashMap<>();
    for (int i = 0; i < terms.length; i++) {
      String[] typeAndPeriod = terms[i].split(DELIMITER);
      termMap.put(typeAndPeriod[0], Integer.parseInt(typeAndPeriod[1]));
    }

    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < privacies.length; i++) {
      String[] dateAndType = privacies[i].split(DELIMITER);
      Integer period = termMap.get(dateAndType[1]);
      LocalDate deadline = LocalDate.parse(dateAndType[0], format).plusMonths(period).minusDays(1);
      if (deadline.getDayOfMonth() > 28) deadline = deadline.withDayOfMonth(28);
      if (now.isAfter(deadline)) result.add(i + 1);
    }

    Collections.sort(result);
    int[] answer = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      answer[i] = result.get(i);
    }
    return answer;
  }
}

/**
 * 개인정보 n개
 * 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다
 * 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다.
 * 유효기간이 지났다면 반드시 파기해야 합니다.
 *
 * 입출력 예
 * today	terms	privacies	result
 * "2022.05.19"	["A 6", "B 12", "C 3"]	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]	[1, 3]
 * "2020.01.01"	["Z 3", "D 5"]	["2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"] [1, 4, 5]
 *
 * terms: "약관종류 유효기간" 형태 (1 ≤ terms의 길이 ≤ 20)
 * 약관 종류는 A~Z중 알파벳 대문자, 약관 종류는 중복되지 않습니다
 * 유효기간은 달 수 (1 이상 100 이하)
 *
 * 1 ≤ privacies의 길이 ≤ 100
 * privacies[i]는 i+1번 개인정보의 수집 일자와 약관 종류를 나타냅니다.
 * today 이전의 날짜만 주어집니다
 *
 * 1 ≤ DD ≤ 28
 *
 * result: 파기해야 할 개인정보 번호
 */