package menu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        validateContainBlank(name);
        return new Coach(name);
    }

    private static void validateContainBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 코치 이름에 공백이 포함되어있습니다.");
        }
    }


    public void addRecommend(Menu menu) {
        recommends.add(menu);
    }

    public void addHate(Menu menu) {
        hates.add(menu);
    }

    public boolean isRecommended(Menu menu) {
        return recommends.contains(menu);
    }

    public boolean isHateMenu(Menu menu) {
        return hates.contains(menu);
    }

    private static void validateNameLength(String name) {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("[ERROR] 이름의 길이는 2 ~ 4자 사이로 제한되어있습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coach coach = (Coach) o;
        return Objects.equals(name, coach.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
