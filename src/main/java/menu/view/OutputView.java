package menu.view;

import java.util.List;
import java.util.StringJoiner;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Day;
import menu.domain.Menu;
import menu.repository.Category;

public class OutputView {
    private static final String START_MESSAGE = "점심 메뉴 추천을 시작합니다.";
    private static final String RECOMMEND_RESULT = "메뉴 추천 결과입니다.";
    private static final String CLEAR_MESSAGE = "추천을 완료했습니다.";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printStartMessage() {
        System.out.println(START_MESSAGE + LINE_SEPARATOR);
    }

    public void printRecommendResultTitle() {
        System.out.println(RECOMMEND_RESULT);
    }

    public void printDays() {
        StringJoiner joiner = initialize();
        joiner.add("구분");
        for (Day day : Day.values()) {
            joiner.add(day.getKoreanName());
        }
        System.out.println(joiner);
    }

    public void printCategories(List<Category> categories) {
        StringJoiner joiner = initialize();
        joiner.add("카테고리");
        for (Category category : categories) {
            joiner.add(category.getName());
        }
        System.out.println(joiner);
    }

    public void printRecommendDetail(Coaches coaches) {
        for (Coach coach : coaches.getCoaches()) {
            StringJoiner joiner = initialize();

            joiner.add(coach.getName());
            addRecommendMenus(coach, joiner);
            System.out.println(joiner);
        }
    }

    private void addRecommendMenus(Coach coach, StringJoiner joiner) {
        for (Menu recommendMenu : coach.getRecommendMenus()) {
            joiner.add(recommendMenu.getName());
        }
    }

    public void printClearMessage() {
        System.out.println(CLEAR_MESSAGE);
    }

    public StringJoiner initialize() {
        return new StringJoiner(" | ", "[ ", " ]");
    }


}
