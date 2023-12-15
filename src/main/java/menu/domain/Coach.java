package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private final String name;
    private final List<Menu> hates = new ArrayList<>();
    private final List<Menu> recommends = new ArrayList<>();

    public Coach(String name) {
        this.name = name;
    }

    public static Coach of(String name) {
        // 이름이 2 ~ 4글자인지 검수
        validateNameLength(name);
        return new Coach(name);
    }

    private static void validateNameLength(String name) {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("[ERROR] 이름의 길이는 2 ~ 4자 사이로 제한되어있습니다");
        }
    }
}
