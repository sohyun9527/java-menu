package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.CategoryRecommender;
import menu.domain.Coach;
import menu.domain.Menu;
import menu.domain.MenuRecommender;
import menu.repository.Category;
import menu.repository.Day;
import menu.util.ReadUntilValidResult;
import menu.validation.ValidationUtil;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ReadUntilValidResult readUntilValid = new ReadUntilValidResult();

    public MenuController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();

        List<Coach> coaches = getCoaches();
        addHateMenusToCoach(coaches);
        CategoryRecommender recommender = startRecommend(coaches);
        showResult(coaches, recommender);
    }

    private List<Coach> getCoaches() {
        return readUntilValid.readUntilValidInput(() -> {
            String inputName = inputView.readCoachNames();
            List<String> names = List.of(inputName.split(",", -1));
            List<Coach> coaches = generateGroup(names);
            ValidationUtil.validateDuplicateName(coaches);
            return coaches;
        });
    }

    private static List<Coach> generateGroup(List<String> names) {
        List<Coach> coaches = new ArrayList<>();
        for (String name : names) {
            coaches.add(new Coach(name));
        }
        return coaches;
    }

    private void addHateMenusToCoach(List<Coach> coaches) {
        for (Coach coach : coaches) {
            readUntilValid.readUntilValid(() -> {
                String hateInput = inputView.readHateMenu(coach.getName());
                List<Menu> hateMenus = generateHateMenus(hateInput);
                addHateMenus(coach, hateMenus);
            });
        }
    }

    List<Menu> generateHateMenus(String hateInput) {
        List<Menu> hateMenus = new ArrayList<>();
        List<String> hates = List.of(hateInput.split(",", -1));
        for (String hate : hates) {
            if (!hate.isEmpty()) {
                hateMenus.add(Menu.of(hate));
            }
        }
        ValidationUtil.validateDuplicateMenu(hateMenus);
        return hateMenus;
    }

    public void addHateMenus(Coach coach, List<Menu> hateMenu) {
        for (Menu menu : hateMenu) {
            coach.addHateMenu(menu);
        }
    }

    private CategoryRecommender startRecommend(List<Coach> coaches) {
        CategoryRecommender recommender = new CategoryRecommender();

        for (Day day : Day.values()) {
            Category recommend = recommender.recommend();
            MenuRecommender menuRecommender = new MenuRecommender(recommend, coaches);
            menuRecommender.recommendToCoaches();
        }
        return recommender;
    }

    private void showResult(List<Coach> coaches, CategoryRecommender recommender) {
        outputView.printRecommendResultTitle();
        outputView.printDays();
        outputView.printRecommendCategories(recommender.getRecommend());
        outputView.printRecommendResult(coaches);
        outputView.printRecommendClear();
    }
}
