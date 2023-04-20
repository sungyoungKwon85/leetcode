package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 * 17번만 실패함 ㅜ
 */
public class 표병합 {

  @Test
  public void test1(){
    String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean",
        "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
        "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4",
        "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
    String[] result = {"EMPTY", "group"};
    Assert.assertArrayEquals(result, solution(commands));
  }

  @Test
  public void test2(){
    String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2",
        "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};
    String[] result = {"d", "EMPTY"};
    Assert.assertArrayEquals(result, solution(commands));
  }

  @Test
  public void test3(){
    String[] commands = {"UPDATE 1 1 menu", "MERGE 1 1 1 2", "MERGE 1 1 1 3", "MERGE 1 1 1 4", "MERGE 1 2 1 3",
        "UPDATE 1 1 hansik", "PRINT 1 1", "PRINT 1 2", "PRINT 1 3", "PRINT 1 4"};
    String[] result = {"hansik", "hansik", "hansik", "hansik"};
    Assert.assertArrayEquals(result, solution(commands));
  }

  public String[] solution(String[] commands) {
    System.out.println(commands);
    ArrayList<String> answer = new ArrayList<>();
    Map<String, Set<String>> mergeMap = new HashMap<>();
    Map<String, Set<String>> valueMap = new HashMap<>();
    Map<String, String> indexMap = new HashMap<>();

    for (String command: commands) {
      String[] split = command.split(" ");
      String execution = split[0];

      if ("UPDATE".equals(execution)) {
        if (split.length == 3) {
          String currentValue = split[1];
          String nextValue = split[2];

          Set<String> indexSet = valueMap.getOrDefault(currentValue, new HashSet<>());
          for (String index: indexSet) {
            indexMap.put(index, nextValue);
          }
          valueMap.remove(currentValue);
          Set<String> nextIndexset = valueMap.getOrDefault(nextValue, new HashSet<>());
          nextIndexset.addAll(indexSet);
          valueMap.put(nextValue, nextIndexset);

        } else if (split.length == 4) {
          String index = split[1] + split[2];
          String nextValue = split[3];
          String currentValue = indexMap.getOrDefault(index, "");
          Set<String> mergeSet = mergeMap.getOrDefault(index, new HashSet<>());
          Set<String> currentValueSet = valueMap.getOrDefault(currentValue, new HashSet<>());

          indexMap.put(index, nextValue);
          currentValueSet.remove(index);

          for (String mergeIndex: mergeSet) {
            indexMap.put(mergeIndex, nextValue);
            currentValueSet.remove(mergeIndex);
          }
          if (!"".equals(currentValue)){
            valueMap.put(currentValue, currentValueSet);
          }

          Set<String> indexSet = valueMap.getOrDefault(nextValue, new HashSet<>());
          indexSet.add(index);
          indexSet.addAll(mergeSet);
          valueMap.put(nextValue, indexSet);
        }

      } else if ("MERGE".equals(execution)) {
        String index1 = split[1] + split[2];
        String index2 = split[3] + split[4];

        if (index1.equals(index2)) {
          continue;
        }

        String value1 = indexMap.getOrDefault(index1, "");
        String value2 = indexMap.getOrDefault(index2, "");
        String nextValue = "";
        String unuseValue = "";
        if ("".equals(value1) && !"".equals(value2)) {
          nextValue = value2;
          unuseValue = value1;
        } else if (!"".equals(value1) && "".equals(value2)) {
          nextValue = value1;
          unuseValue = value2;
        } else if (!"".equals(value1) && !"".equals(value2)) {
          nextValue = value1;
          unuseValue = value2;
        }

        Set<String> mergeSetBase = mergeMap.getOrDefault(index1, new HashSet<>());
        Set<String> mergeSet2 = mergeMap.getOrDefault(index2, new HashSet<>());
        mergeSetBase.addAll(mergeSet2);
        mergeSetBase.add(index1);
        mergeSetBase.add(index2);

        Set<String> unuseSet = valueMap.getOrDefault(unuseValue, new HashSet<>());
        for (String mi: mergeSetBase) {
          indexMap.put(mi, nextValue);
          mergeMap.put(mi, mergeSetBase);
          unuseSet.remove(mi);
        }

        Set<String> valueSet = valueMap.getOrDefault(nextValue, new HashSet<>());
        valueSet.addAll(mergeSetBase);
        valueMap.put(nextValue, valueSet);

        valueMap.put(unuseValue, unuseSet);

      } else if ("UNMERGE".equals(execution)) {
        String index = split[1] + split[2];
        String value = indexMap.get(index);
        Set<String> valueSet = valueMap.getOrDefault(value, new HashSet<>());
        Set<String> mergeSet = mergeMap.getOrDefault(index, new HashSet<>());
        for (String mergeIndex: mergeSet) {
          if (!index.equals(mergeIndex)) {
            indexMap.put(mergeIndex, "");
            valueSet.remove(mergeIndex);
          }
          mergeMap.remove(mergeIndex);
        }

      } else if ("PRINT".equals(execution)) {
        String index = split[1] + split[2];
        String value = indexMap.get(index);
        answer.add(value == null || "".equals(value) ? "EMPTY" : value);
      }
    }

    return answer.toArray(new String[answer.size()]);
  }
}

/**
 * 표의 크기는 50 × 50으로 고정
 * 각 셀은 문자열 값을 가질 수 있고, 다른 셀과 병합될 수 있습니다
 *
 * "UPDATE r c value" -> r, c 위치의 셀을 선택후 변경
 * "UPDATE value1 value2" -> value1을 값으로 가지고 있는 모든 셀을 선택후 value2로 변경
 *
 * "MERGE r1 c1 r2 c2" -> (r1, c1) 위치의 셀과 (r2, c2) 위치의 셀을 선택하여 병합
 *  같은셀이면 무시
 *  사이에 위치한 셀들은 영향을 받지 않습니다.
 *  두 셀 중 한 셀이 값을 가지고 있을 경우 병합된 셀은 그 값을 가지게 됩니다
 *  두 셀 모두 값을 가지고 있을 경우 병합된 셀은 (r1, c1) 위치의 셀 값을 가지게 됩니다.
 *  (r1, c1) 와 (r2, c2) 중 어느 위치를 선택하여도 병합된 셀로 접근
 *
 * "UNMERGE r c" -> 병합을 해제
 *  선택한 셀이 포함하고 있던 모든 셀은 프로그램 실행 초기의 상태로 돌아갑니다.
 *  병합을 해제하기 전 셀이 값을 가지고 있었을 경우 (r, c) 위치의 셀이 그 값을 가지게 됩니다.
 *
 * "PRINT r c" -> 출력. 없으면 "EMPTY"를 출력
 */