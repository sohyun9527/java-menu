package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Coach;
import menu.domain.Menu;
import menu.repository.Category;
import menu.repository.MenuBoard;
import menu.service.CategoryRecommender;
import menu.service.MenuRecommender;
import menu.util.ReadUntilValidResult;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {
    private final InputView inputView;
    private final OutputView outputView;
    private ReadUntilValidResult readUntilValid = new ReadUntilValidResult();

    public MenuController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Menu> menuBoard = generateMenuBoard();
        outputView.printStartMessage();

        List<Coach> coaches = getCoaches();
        addHateMenusToCoach(menuBoard, coaches);
        CategoryRecommender recommender = startRecommend(coaches);
        showResult(coaches, recommender);
    }

    private void showResult(List<Coach> coaches, CategoryRecommender recommender) {
        outputView.printRecommendResultTitle();
        outputView.printDays();
        outputView.printRecommendCategories(recommender.getRecommend());
        outputView.printRecommendResult(coaches);
        outputView.printRecommendClear();
    }

    private CategoryRecommender startRecommend(List<Coach> coaches) {
        CategoryRecommender recommender = new CategoryRecommender();

        for (int i = 0; i < 5; i++) {
            Category recommend = recommender.recommend();
            System.out.println("recommend = " + recommend.getName());
            MenuRecommender menuRecommender = new MenuRecommender(recommend, coaches);
            menuRecommender.recommendToCoaches();
        }
        return recommender;
    }

    private void addHateMenusToCoach(List<Menu> menuBoard, List<Coach> coaches) {
        readUntilValid.readUntilValid(() -> {
            for (Coach coach : coaches) {
                String hateInput = inputView.readHateMenu(coach.getName());
                List<Menu> hateMenus = generateHateMenus(hateInput, menuBoard);
                addHateMenus(coach, hateMenus);
            }
        });
    }

    private List<Coach> getCoaches() {
        return readUntilValid.readUntilValidInput(() -> {
            String inputName = inputView.readCoachNames();
            List<String> names = List.of(inputName.split(",", -1));
            List<Coach> coaches = generateGroup(names);
            validateDuplication(coaches);
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

    private static void validateDuplication(List<Coach> coaches) {
        if (coaches.stream().distinct().count() != coaches.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 코치 이름이 있습니다.");
        }
    }


    List<Menu> generateHateMenus(String hateInput, List<Menu> menuBoard) {
        List<Menu> hateMenus = new ArrayList<>();
        List<String> hates = List.of(hateInput.split(",", -1));
        for (String hate : hates) {
            if (!hate.isEmpty()) {
                hateMenus.add(Menu.of(hate));
            }
        }

        return hateMenus;
    }

    public void addHateMenus(Coach coach, List<Menu> hateMenu) {
        for (Menu menu : hateMenu) {
            coach.addHateMenu(menu);
        }
    }

    public List<Menu> generateMenuBoard() {
        List<Menu> menuBoard = new ArrayList<>();

        for (MenuBoard menu : MenuBoard.values()) {
            Category category = menu.getCategory();
            List<String> categoryMenu = menu.getCategoryMenus();
            for (String name : categoryMenu) {
                menuBoard.add(new Menu(name, category));
            }
        }
        return menuBoard;
    }


}
