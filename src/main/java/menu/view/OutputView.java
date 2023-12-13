package menu.view;

import java.util.List;
import java.util.StringJoiner;
import menu.domain.Coach;
import menu.domain.Menu;
import menu.repository.Category;
import menu.repository.Day;

public class OutputView {
    private static final String START_MESSAGE = "점심 메뉴 추천을 시작합니다.";
    private static final String RECOMMEND_RESULT_MESSAGE = "메뉴 추천 결과입니다.";
    private static final String CLEAR_MESSAGE = "추천을 완료했습니다.";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printRecommendResultTitle() {
        System.out.println(RECOMMEND_RESULT_MESSAGE);
    }

    public void printDays() {
        StringJoiner joiner = new StringJoiner("");
        joiner.add("[ ");
        joiner.add("구분");
        for (Day day : Day.values()) {
            joiner.add(" | ");
            joiner.add(day.getKoreanName());
        }
        joiner.add(" ]");
        System.out.println(joiner);
    }

    public void printRecommendCategories(List<Category> categories) {
        StringJoiner joiner = new StringJoiner("");
        joiner.add("[ ");
        joiner.add("카테고리");
        for (Category category : categories) {
            joiner.add(" | ");
            joiner.add(category.getName());
        }
        joiner.add(" ]");

        System.out.println(joiner);
    }

    public void printRecommendResult(List<Coach> coaches) {
        StringJoiner joiner = new StringJoiner("");
        for (Coach coach : coaches) {
            joiner.add("[ ");
            joiner.add(coach.getName());
            for (Menu recommendMenu : coach.getRecommendMenu()) {
                joiner.add(" | ");
                joiner.add(recommendMenu.getName());
            }
            joiner.add(" ]");
            joiner.add(LINE_SEPARATOR);
        }
        System.out.println(joiner);
    }

    public void printRecommendClear() {
        System.out.println(CLEAR_MESSAGE);
    }
}
