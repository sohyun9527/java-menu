package menu.controller;

import menu.domain.Category;
import menu.domain.CategoryRecommender;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Days;
import menu.domain.Menu;
import menu.domain.MenuRecommender;
import menu.domain.Menus;
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
        Coaches coaches = Coaches.of(inputView.readCoachNames());

        for (Coach coach : coaches.getCoaches()) {
            Menus hateMenus = Menus.generateHateMenus(inputView.readHateMenus());
            for (Menu hateMenu : hateMenus.getMenus()) {
                coach.addHate(hateMenu);
            }
        }

        CategoryRecommender recommender = new CategoryRecommender();
        MenuRecommender menuRecommender = new MenuRecommender();

        for (Days value : Days.values()) {
            Category recommendCategory = recommender.recommend();
            for (Coach coach : coaches.getCoaches()) {
                menuRecommender.recommend(recommendCategory, coach);
            }
        }

        outputView.printDays();
        outputView.printCategories(recommender.getRecommended());
        for (Coach coach : coaches.getCoaches()) {
            outputView.printRecommendMenu(coach.getName(), coach.getRecommends());
        }
    }
}
