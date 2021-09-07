package wb2021;

import org.junit.Test;

import java.util.*;

/**
 * User: kkwonsy
 * Date: 2021/07/24 4:17 오후
 */
public class Test0724_2 {
    @Test
    public void test2() throws Exception {
        System.out.println(solution("my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "mov!e.mkv 10000b"));
//        "\"music 1011b\n" +
//                "images 0b\n" +
//                "movies 10200b\n" +
//                "other 105b\""
    }

    // 하드 디스크가 꽉참
    // 스태틱 파일들을 컴파일해야함
    // 각 파일의 용량이 얼만지 알고 싶음
    // 각 파일은 이름, 확장자을 가짐
    // S는 파일 리스트를 포함함
    // 라인으로 구분됨
    // 파일 사이즈도 포함.(space로 구분)
    // 4개의 row를 리턴
    // music, images movies, others
    //
    private static final String MUSIC = "music";
    private static final String IMAGES = "images";
    private static final String MOVIES = "movies";
    private static final String OTHER = "other";

    public String solution(String S) {
        final Map<String, List<String>> extensionMap = initExtensions();

        final String SEPARATE_ENTER = "\n";
        final String SEPARATE_SPACE = " ";

        Map<String, Integer> resultMap = initMap();

        String[] files = S.split(SEPARATE_ENTER);
        Arrays.stream(files).forEach(file -> {
            String[] fileArr = file.split(SEPARATE_SPACE);
            if (fileArr.length != 2) {
                return;
            }
            final String filename = fileArr[0];
            final String memory = fileArr[1];
            final Integer memoryInt = Integer.parseInt(memory.substring(0, memory.length()-1));
            final String extension = getExtension(filename);
            String key = extensionMap.entrySet().stream()
                    .filter(ets -> ets.getValue().contains(extension))
                    .map(ets -> ets.getKey()).findFirst()
                    .orElse(null);
            if (key == null) {
                key = OTHER;
            }
            resultMap.put(key, resultMap.get(key) + memoryInt);
        });
        StringBuilder sb = new StringBuilder();
        resultMap.entrySet().stream().forEach(entry -> {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("b").append("\n");
        });
        return sb.toString();
    }

    private Map<String, List<String>> initExtensions() {
        Map<String, List<String>> map = new HashMap<>();
        map.put(MUSIC, Arrays.asList("mp3", "aac", "flac"));
        map.put(IMAGES, Arrays.asList("jpg", "bmp", "gif"));
        map.put(MOVIES, Arrays.asList("mp4", "avi", "mkv"));
        map.put(OTHER, Arrays.asList("7z", "txt", "zip"));
        return map;
    }

    private String getExtension(String filename) {
        final String SEPARATE_DOT = "\\.";
        String[] split = filename.split(SEPARATE_DOT);
        if (split.length < 2) {
            return null;
        }
        return split[split.length - 1];
    }

    private Map<String, Integer> initMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put(MUSIC, 0);
        map.put(IMAGES, 0);
        map.put(MOVIES, 0);
        map.put(OTHER, 0);
        return map;
    }


}
