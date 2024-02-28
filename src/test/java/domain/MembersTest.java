package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MembersTest {

    @Test
    @DisplayName("Members 생성 성공: 사이즈 일치")
    void test_ok_constructor() {
        Members members = Members.from("a,bb,ccc,ddddd");
        assertThat(members.getCount()).isEqualTo(4);
    }

    @Test
    @DisplayName("Members 생성 실패: 중복 이름")
    void test_exception_duplicatedNames() {
        assertThatThrownBy(() -> Members.from("a,b,c,c"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이름은 서로 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16"})
    @DisplayName("Members 입력 실패: 인원수 경계값(1, 16)")
    void test_exception_memberCount(String rawNames) {
        assertThatThrownBy(() -> Members.from(rawNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("명만 허용됩니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15"})
    @DisplayName("Members 입력 성공: 인원수 경계값(2, 15)")
    void test_ok_memberCount(String rawNames) {
        assertThatCode(() -> Members.from(rawNames))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {" a  , b , c ", "a,b,c"})
    @DisplayName("Members 입력 성공: 쉼표 구분, 쉼표 좌우 공백 제거")
    void test_ok_delimiter(String rawNames) {
        assertThatCode(() -> Members.from(rawNames))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {",,,a", "a,,,", ",,,", "bb, ,cc"})
    @DisplayName("Members 입력 실패: 비정상적인 쉼표 입력")
    void test_exception_delimiter(String rawNames) {
        assertThatThrownBy(() -> Members.from(rawNames))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Members 입력 실패: null 입력")
    void test_exception_null() {
        assertThatThrownBy(() -> Members.from(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("null을 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("성공: 멤버 이름으로 인덱스를 찾을 수 있다")
    void findIndexByName() {
        Members members = Members.from("a,b,c");
        Assertions.assertAll(
            () -> assertThat(members.findIndexByName("a")).isEqualTo(0),
            () -> assertThat(members.findIndexByName("b")).isEqualTo(1),
            () -> assertThat(members.findIndexByName("c")).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("실패: 존재하지 않는 멤버의 인덱스를 찾으려 하면 에러 발생")
    void findIndexByName_exception() {
        Members members = Members.from("a,b,c");
        assertThatThrownBy(() -> members.findIndexByName("d"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않는 플레이어입니다.");
    }
}
