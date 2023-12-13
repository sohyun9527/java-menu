package menu.validation;

import java.util.List;
import menu.domain.Coach;
import menu.domain.Menu;

public class ValidationUtil {

    public static void validateDuplicateMenu(List<Menu> menus) {
        if (menus.stream().distinct().count() != menus.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 존재합니다!");
        }
    }

    public static void validateDuplicateName(List<Coach> coaches) {
        if (coaches.stream().distinct().count() != coaches.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 코치명이 존재합니다!");
        }
    }
}
