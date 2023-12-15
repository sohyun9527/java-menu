package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Menus {
    private static final int MAX_COUNT = 3;
    private static final String MENU_NAME_DELIMITER = ",";
    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public static Menus generateHateMenus(String menuNames) {
        List<String> names = List.of(menuNames.split(MENU_NAME_DELIMITER, -1));
        List<Menu> menuList = new ArrayList<>();
        for (String name : names) {
            menuList.add(Menu.of(name));
        }
        validateOverMenuCount(menuList);
        validateDuplicateMenu(menuList);
        return new Menus(menuList);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    private static void validateOverMenuCount(List<Menu> menuList) {
        if (menuList.size() > MAX_COUNT) {
            throw new IllegalArgumentException("[ERROR] 싫어하는 메뉴는 3개까지 입력 가능합니다.");
        }
    }


    private static void validateDuplicateMenu(List<Menu> menuList) {
        if (menuList.stream().distinct().count() != menuList.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 존재합니다!");
        }
    }
}
