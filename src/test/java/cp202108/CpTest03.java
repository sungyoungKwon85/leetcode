package cp202108;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CpTest03 {
    // 서버 n개, 1번부터
    // 서버별 캐릭터 최대 5개, 넘으면 가장 오래된 캐릭터는 삭제되고 그 자리에 추가
    // 다른 유저가 생성한 케릭터 닉네임은 고려 안하고 해당 유저는 중복 닉네임 불가(서버별)
    // 각 서버별 어떤 케릭터가 생성되었는지 리턴
    // 정렬기준.
    // 번호 작은 서버 먼저
    // 더 오래된 닉네임 먼저
    // 캐릭터 없는 서버 무시

    @Test
    public void asdf() throws Exception {
        Solution solution = new Solution();
        assertArrayEquals(solution.solution(1, new String[]{
            "1 fracta",
            "1 sina",
            "1 hana",
            "1 robel",
            "1 abc",
            "1 sina",
            "1 lynn"
        }), new String[]{
            "sina", "hana", "robel", "abc", "lynn"
        });
        assertArrayEquals(solution.solution(4, new String[]{
            "3 b", "3 a",
            "3 k", "3 q", "3 z", "3 m", "3 b",
            "1 a", "1 b", "1 abc",
            "1 abcd", "1 abc", "1 aaa", "1 a", "1 z", "1 q"

        }), new String[]{
            "abc", "abcd", "aaa", "z", "q",
            "k", "q", "z", "m", "b"
        });
        assertArrayEquals(solution.solution(4, new String[]{
            "1 a", "1 b", "1 abc",
            "3 b", "3 a",
            "1 abcd", "1 abc", "1 aaa", "1 a", "1 z", "1 q",
            "3 k", "3 q", "3 z", "3 m", "3 b"

        }), new String[]{
            "abc", "abcd", "aaa", "z", "q",
            "k", "q", "z", "m", "b"
        });
    }


    class Solution {

        private static final String SEPARATOR = " ";
        private static final int MAX_CHARACTER_SIZE_FOR_USER = 5;

        public String[] solution(int n, String[] record) {



            Map<Integer, Set<String>> serverNamesMap = new LinkedHashMap<>();
            for (String info: record) {
                String[] serverAndName = info.split(SEPARATOR);
                Integer server = Integer.parseInt(serverAndName[0]);
                String name = serverAndName[1];
                if (existServer(n, server)) {
                    Set<String> names = serverNamesMap.get(server);
                    if (names == null || names.isEmpty()) {
                        Set<String> newNames = createSetForRemoveEldestEntry();
                        newNames.add(name);
                        serverNamesMap.put(server, newNames);
                    } else {
                        names.add(name);
                        serverNamesMap.put(server, names);
                    }
                }
            }
            List<Entry<Integer, Set<String>>> entries = sortByServer(serverNamesMap);
            return entries.stream().flatMap(e -> e.getValue().stream()).toArray(String[]::new);
        }

        private List<Entry<Integer, Set<String>>> sortByServer(Map<Integer, Set<String>> serverNamesMap) {
            List<Entry<Integer, Set<String>>> entries = new LinkedList<>(serverNamesMap.entrySet());
            Collections.sort(entries, Comparator.comparing(Entry::getKey));
            return entries;
        }

        private Set<String> createSetForRemoveEldestEntry() {
            return Collections.newSetFromMap(new LinkedHashMap<String, Boolean>(){
                protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
                    return size() > MAX_CHARACTER_SIZE_FOR_USER;
                }
            });
        }

        private boolean existServer(int n, int server) {
            return server <= n ? true : false;
        }
    }
}
