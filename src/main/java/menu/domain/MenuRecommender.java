package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import menu.repository.Category;
import menu.repository.MenuBoard;

public class MenuRecommender {

    public void recommendMenuUntilValid(Coach coach, Category category) {
        while (true) {
            List<String> menuNames = getMenus(category);
            String randomRecommend = recommend(menuNames);
            Menu menu = Menu.of(randomRecommend);

            if (!coach.isContainedHateMenu(menu) && !coach.isAlreadyRecommend(menu)) {
                coach.addRecommendMenu(menu);
                return;
            }
        }
    }

    public String recommend(List<String> menus) {
        return Randoms.shuffle(menus).get(0);
    }

    public List<String> getMenus(Category category) {
        return Arrays.stream(MenuBoard.values())
                .filter(menuBoard -> menuBoard.getCategory() == category)
                .findFirst()
                .map(MenuBoard::getMenus)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리입니다!"));
    }
}
