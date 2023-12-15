package menu.domain;

import java.util.Arrays;
import java.util.Objects;

public class Menu {

    private final String name;

    private final Category category;

    public Menu(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public static Menu of(String name) {
        return Arrays.stream(MenuBoard.values())
                .filter(menuBoard -> menuBoard.getMenuNames().contains(name))
                .findFirst()
                .map(menuBoard -> new Menu(name, menuBoard.getCategory()))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));

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
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
