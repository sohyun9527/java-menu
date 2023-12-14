package menu.domain;

import java.util.ArrayList;
import java.util.List;
import menu.Menu;

public class Coach {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 4;
    private final String name;
    private final List<Menu> hateMenus = new ArrayList<>();
    private final List<Menu> recommendMenus = new ArrayList<>();

    public Coach(String name) {
        this.name = name;
    }

    public static Coach of(String name) {
        validateContainSpace(name);
        validateNameLength(name);
        return new Coach(name);
    }

    public static void validateContainSpace(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 지정된 구분자(,)로 공백 없이 나눠주세요.");
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 코치의 이름은 2 ~ 4자 사이여야합니다.");
        }
    }
}
