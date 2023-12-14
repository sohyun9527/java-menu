package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.CategoryRecommender;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Day;
import menu.domain.Menu;
import menu.domain.MenuRecommender;
import menu.repository.Category;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView;
    private final OutputView outputView;

    public MenuController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Coaches coaches = getCoaches();

        for (Coach coach : coaches.getCoaches()) {
            String menus = inputView.readHateMenus(coach.getName());
            if (menus.isBlank()) {
                continue;
            }
            List<String> hateMenus = List.of(menus.split(",", -1));
            for (String hateMenu : hateMenus) {
                coach.addHateMenu(Menu.of(hateMenu));
            }
        }
        CategoryRecommender recommender = new CategoryRecommender();
        MenuRecommender menuRecommender = new MenuRecommender();
        outputView.printRecommendResultTitle();
        outputView.printDays();
        for (Day day : Day.values()) {
            Category category = recommender.getValidCategoryRecommend();
            for (Coach coach : coaches.getCoaches()) {
                menuRecommender.recommendMenuUntilValid(coach, category);
            }
        }
        outputView.printCategories(recommender.getRecommendCategories());
        outputView.printRecommendDetail(coaches);
        outputView.printClearMessage();
    }

    private Coaches getCoaches() {
        String names = inputView.readCoachNames();
        List<String> coachNames = List.of(names.split(",", -1));
        return gerateCoaches(coachNames);
    }

    private Coaches gerateCoaches(List<String> coachNames) {
        List<Coach> coaches = new ArrayList<>();
        for (String coachName : coachNames) {
            coaches.add(Coach.of(coachName));
        }
        return Coaches.of(coaches);
    }
}
