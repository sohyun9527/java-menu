package menu.view;

import java.util.List;
import java.util.StringJoiner;
import menu.domain.Day;
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

    public StringJoiner initialize() {
        return new StringJoiner(" | ", "[ ", " ]");
    }


}
