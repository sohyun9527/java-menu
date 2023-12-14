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
import menu.util.ReadUntilValid;
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
        CategoryRecommender recommender = new CategoryRecommender();
        MenuRecommender menuRecommender = new MenuRecommender();

        updateHateMenus(coaches);
        recommendToCoaches(coaches, recommender, menuRecommender);
        showRecommendResult(coaches, recommender);
    }

    private Coaches getCoaches() {
        return ReadUntilValid.readUntilValidInput(() -> {
            String names = inputView.readCoachNames();
            List<String> coachNames = List.of(names.split(",", -1));
            return gerateCoaches(coachNames);
        });
    }

    private Coaches gerateCoaches(List<String> coachNames) {
        List<Coach> coaches = new ArrayList<>();
        for (String coachName : coachNames) {
            coaches.add(Coach.of(coachName));
        }
        return Coaches.of(coaches);
    }

    private void updateHateMenus(Coaches coaches) {
        for (Coach coach : coaches.getCoaches()) {
            ReadUntilValid.readUntilValidInput(() -> {
                String menus = inputView.readHateMenus(coach.getName());
                addHateMenusToCoach(coach, menus);
            });
        }
    }

    private void addHateMenusToCoach(Coach coach, String menus) {
        List<String> hateMenus = List.of(menus.split(",", -1));
        for (String hateMenu : hateMenus) {
            coach.addHateMenu(Menu.of(hateMenu));
        }
    }

    private void recommendToCoaches(Coaches coaches, CategoryRecommender recommender,
                                    MenuRecommender menuRecommender) {
        for (Day day : Day.values()) {
            Category category = recommender.getValidCategoryRecommend();
            for (Coach coach : coaches.getCoaches()) {
                menuRecommender.recommendMenuUntilValid(coach, category);
            }
        }
    }

    private void showRecommendResult(Coaches coaches, CategoryRecommender recommender) {
        outputView.printRecommendResultTitle();
        outputView.printDays();
        outputView.printCategories(recommender.getRecommendCategories());
        outputView.printRecommendDetail(coaches);
        outputView.printClearMessage();
    }
}
