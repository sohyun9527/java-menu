package menu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coach {
    private final String name;
    private List<Menu> hateMenu = new ArrayList<>();
    private List<Menu> recommendMenu = new ArrayList<>();

    public Coach(String name) {
        validateName(name);
        this.name = name;
    }

    public void addHateMenu(Menu menu) {
        hateMenu.add(menu);
    }

    public void addRecommendMenu(Menu menu) {
        if (!isAlreadyRecommended(menu)) {
            recommendMenu.add(menu);
        }
    }

    public boolean isAlreadyHate(Menu menu) {
        return hateMenu.contains(menu);
    }

    public boolean isAlreadyRecommended(Menu menu) {
        return recommendMenu.contains(menu);
    }

    public void validateName(String name) {
        validateNameLength(name);
        validateContainSpace(name);
    }

    public void validateNameLength(String name) {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("[ERROR] 코치의 이름은 2 ~ 4글자 사이여야합니다.");
        }
    }

    public void validateContainSpace(String name) {
        if (name.isEmpty() || name.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 코치 이름의 구분자는 (,) 입니다!");
        }
    }

    public String getName() {
        return name;
    }

    public List<Menu> getRecommendMenu() {
        return recommendMenu;
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
