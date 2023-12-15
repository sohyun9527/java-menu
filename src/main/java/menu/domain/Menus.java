package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Menus {
    private static final String MENU_NAME_DELIMITER = ",";
    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public static Menus of(String menuNames) {
        List<String> names = List.of(menuNames.split(MENU_NAME_DELIMITER, -1));
        List<Menu> menuList = new ArrayList<>();
        for (String name : names) {
            menuList.add(Menu.of(name));
        }
        validateDuplicateMenu(menuList);
        return new Menus(menuList);
    }

    private static void validateDuplicateMenu(List<Menu> menuList) {
        if (menuList.stream().distinct().count() != menuList.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 존재합니다!");
        }
    }
}
