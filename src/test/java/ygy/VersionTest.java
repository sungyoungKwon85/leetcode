package ygy;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * User: kkwonsy
 * Date: 2021/06/10 8:52 오후
 */
public class VersionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void when_Version_is_null() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustNotBeNull);
        Version version = new Version(null);
    }

    @Test
    public void when_Version_not_match_of_Regx() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("a.a.b");
    }

    @Test
    public void when_Version_not_match_of_Regx2() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("0.");
    }

    @Test
    public void when_Version_not_match_of_Regx3() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("0.0.");
    }

    @Test
    public void when_Version_not_match_of_Regx4() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("0.0.3");
    }

    @Test
    public void when_Version_match_of_Regx1() {
        Version version = new Version("0.0.2");
        Assertions.assertThat(version).isNotNull();
    }
    @Test
    public void when_Version_match_of_Regx2() {
        Version version = new Version("9");
        Assertions.assertThat(version).isNotNull();
    }
    @Test
    public void when_Version_match_of_Regx3() {
        Version version = new Version("0.9");
        Assertions.assertThat(version).isNotNull();
    }

    @Test
    public void when_Version_not_match_of_Regx5() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("0.0.2.4");
    }

    @Test
    public void when_Version_not_match_of_Regx6() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("3.8.0-SNAPSHO");
    }


    @Test
    public void when_SNAPSHOT_is_lowercase() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorVersionMustMatchPattern);
        Version version = new Version("3.8.0-snapshot");
    }

    @Test
    public void when_SNAPSHOT_is_uppercase() {
        Version version = new Version("3.8.0-SNAPSHOT");
        Assertions.assertThat(version.isSnapshot()).isTrue();
    }

    @Test
    public void when_SNAPSHOT_does_not_exist() {
        Version version = new Version("3.8.0");
        Assertions.assertThat(version.isSnapshot()).isFalse();
    }

    @Test
    public void when_compareTo_between_same_two_versions() {
        Version version1 = new Version("3.8.0");
        Version version2 = new Version("3.8.0");
        Assertions.assertThat(version1.compareTo(version2)).isEqualTo(0);
    }

    @Test
    public void when_compareTo_between_another_is_SNAPHOST() {
        Version version1 = new Version("3.8.0");
        Version version2 = new Version("3.8.0-SNAPSHOT");
        Assertions.assertThat(version1.compareTo(version2)).isEqualTo(1);
    }

    @Test
    public void when_compareTo_between_one_is_SNAPHOST() {
        Version version1 = new Version("3.8.0-SNAPSHOT");
        Version version2 = new Version("3.8.0");
        Assertions.assertThat(version1.compareTo(version2)).isEqualTo(-1);
    }

    @Test
    public void when_compareTo_between_X_are_different() {
        Version version1 = new Version("3");
        Version version2 = new Version("2");
        Assertions.assertThat(version1.compareTo(version2)).isEqualTo(1);
    }

    @Test
    public void when_compareTo_between_Y_are_different() {
        Version version1 = new Version("2.4");
        Version version2 = new Version("2.1");
        Assertions.assertThat(version1.compareTo(version2)).isEqualTo(1);
    }

    @Test
    public void when_compareTo_between_Z_are_different() {
        Version version1 = new Version("2.4.3");
        Version version2 = new Version("2.4.0");
        Assertions.assertThat(version1.compareTo(version2)).isEqualTo(1);
    }

    @Test
    public void when_compareTo_another_version_is_null() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(errorOtherMustNotBeNull);
        Version version1 = new Version("3.8.0");
        version1.compareTo(null);
    }


    // 생성자 아규먼트는 null인 경우 IllegalArgumentException/errorVersionMustNotBeNull
    // 생성자 아규먼트는 Regx \d+(\.\d+){0,2}(-SNAPSHOT)?. 과 매칭되어야 함, 아닌경우 IllegalArgumentException/errorVersionMustMatchPattern

    // version의 포멧은 x, x.y 이든 x.y.z- 및 SNAPSHOP이 있든 없든 괜찮음. x의 경우 0은 Y, Z를 위해 사용하.ㅁ
    // X.Y의 경우 0은 Z를 위함

    // isSnapShot() 메서드를 호출해서 정의되었는지 인식해야함, case-sensitive함

    // compareTo가 잘 구현됐는지
    // null을 받으면 errorOtherMustNotBeNull
    // 스냅샷 버전은 non스냅샷 버전보다 언제나 낮아야 함
    // x > y > z 순서.


    // expected error messages:
    static final String errorVersionMustNotBeNull = "'version' must not be null!";
    static final String errorOtherMustNotBeNull =  "'other' must not be null!";
    static final String errorVersionMustMatchPattern =  "'version' must match: 'major.minor.patch(-SNAPSHOT)'!";

    class Version implements Comparable<Version> {
        Version() {
            throw new IllegalArgumentException(errorVersionMustNotBeNull);
        }
        Version(String version) {
            // ...
        }

        boolean isSnapshot() {
            return true;
        }

        @Override
        public int compareTo(Version o) {
            return 0;
        }
    }
}


