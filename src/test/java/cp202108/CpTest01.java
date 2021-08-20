package cp202108;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CpTest01 {

    @Test
    public void asdf() throws Exception {
        Solution solution = new Solution();
        assertEquals(solution.solution(new String[]{
            "alex pizza paster",
            "alex pizza pizza",
            "alex noodle",
            "bob pasta",
            "bob noodle sandwich pasta",
            "bob steak noodle"
        })[0], "bob");

        String[] solution2 = solution.solution(new String[]{
            "bob steak noodle",
            "alex pizza paster"
        });
        assertEquals(solution2[0], "alex");
        assertEquals(solution2[1], "bob");

        assertEquals(solution.solution(new String[]{
            "alex noodle",
            "bob pasta snack"
        })[0], "bob");
    }

    // 유저가 주문한 음식 데이터
    // 가장 다양하게 주문한 유저는 누구인가
    // 한번에 최대 5개까지 음식 주문
    // 구분은 스페이스바
    class Solution {

        private static final String SEPARATOR = " ";
        private static final int START_MENU_INDEX = 1;
        private static final int USER_INDEX = 0;

        public String[] solution(String[] orders) {


            int biggerSize = 0;
            Map<String, Set<String>> userMenusMap = new HashMap<>();
            for (String order : orders) {
                String[] info = order.split(SEPARATOR);
                String userId = info[USER_INDEX];
                Set<String> savedMenus = userMenusMap.get(userId);
                int renewedSize;
                if (savedMenus == null || savedMenus.isEmpty()) {
                    renewedSize = saveMenusAndReturnSize(userMenusMap, info, userId, new HashSet<>());
                } else {
                    renewedSize = saveMenusAndReturnSize(userMenusMap, info, userId, savedMenus);
                }
                if (biggerSize < renewedSize) {
                    biggerSize = renewedSize;
                }
            }
            final int maxSize = biggerSize;

            return userMenusMap.entrySet().stream()
                .filter(userMenus -> userMenus.getValue().size() == maxSize)
                .map(userMenus -> userMenus.getKey())
                .sorted()
                .toArray(String[]::new);
        }

        private int saveMenusAndReturnSize(
            Map<String, Set<String>> userMenusMap, String[] info, String userId, Set<String> menuSet) {

            for (int i = START_MENU_INDEX; i < info.length; i++) {
                menuSet.add(info[i]);
            }
            userMenusMap.put(userId, menuSet);
            return menuSet.size();
        }
    }
}
