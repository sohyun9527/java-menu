package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import menu.repository.Category;
import menu.repository.MenuBoard;

public class MenuRecommender {

    private final Category category;
    private final List<Coach> coaches;

    public MenuRecommender(Category category, List<Coach> coaches) {
        this.category = category;
        this.coaches = coaches;
    }

    public void recommendToCoaches() {
        for (Coach coach : coaches) {
            recommendUntilValid(coach);
        }
    }

    private void recommendUntilValid(Coach coach) {
        while (true) {
            Menu recommend = generateMenu();
            if (!coach.isAlreadyRecommended(recommend) && !coach.isAlreadyHate(recommend)) {
                coach.addRecommendMenu(recommend);
                break;
            }
        }
    }

    // 한명의 코치에게 싫어하는 메뉴가 아니고, 이미 추천한 적 없다면
    public Menu generateMenu() {
        String menuName = recommend();
        return Menu.of(menuName);
    }

    public String recommend() {
        List<String> recommendList = MenuBoard.findByCategory(category);
        return Randoms.shuffle(recommendList).get(0);
    }
}
