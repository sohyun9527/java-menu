package menu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import menu.Menu;

public class Coach {

    private static final int HATE_MENU_MAX_COUNT = 2;
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

    public void addHateMenu(Menu menu) {
        validateDuplicateHateMenu(menu);
        validateOverSizeHateMenu();
        hateMenus.add(menu);
    }

    public void addRecommendMenu(Menu menu) {
        recommendMenus.add(menu);
    }

    public boolean isAlreadyRecommend(Menu menu) {
        return recommendMenus.contains(menu);
    }

    public boolean isContainedHateMenu(Menu menu) {
        return hateMenus.contains(menu);
    }

    private void validateOverSizeHateMenu() {
        if (hateMenus.size() > HATE_MENU_MAX_COUNT) {
            throw new IllegalArgumentException("[ERROR] 싫어하는 메뉴는 2개가 최대입니다");
        }
    }

    private void validateDuplicateHateMenu(Menu menu) {
        if (isContainedHateMenu(menu)) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 존재합니다!");
        }
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

    public String getName() {
        return name;
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
