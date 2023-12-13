package menu.domain;

import menu.repository.Category;
import menu.repository.MenuBoard;

import java.util.Arrays;

public class Menu {
    private final String name;
    private final Category category;

    public Menu(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public static Menu of(String name) {
        return Arrays.stream(MenuBoard.values())
                .filter(menuBoard -> menuBoard.getCategoryMenus().contains(name))
                .findFirst()
                .map(menuBoard -> new Menu(name, menuBoard.getCategory()))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다!"));
    }

}
