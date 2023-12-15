package menu.view;

import java.util.List;
import java.util.StringJoiner;
import menu.domain.Category;
import menu.domain.Days;
import menu.domain.Menu;

public class OutputView {
    private static final String DELIMITER = " | ";
    private static final String PREFIX = "[ ";
    private static final String SUFFIX = " ]";

    private static final String START_MESSAGE = "점심 메뉴 추천을 시작합니다.";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printDays() {
        StringJoiner joiner = generateJoiner();
        joiner.add("구분");
        for (Days value : Days.values()) {
            joiner.add(value.getKoreanName());
        }
        System.out.println(joiner);
    }

    public void printCategories(List<Category> categoryList) {
        StringJoiner joiner = generateJoiner();
        joiner.add("카테고리");
        for (Category category : categoryList) {
            joiner.add(category.getName());
        }
        System.out.println(joiner);
    }

    public StringJoiner generateJoiner() {
        return new StringJoiner(DELIMITER, PREFIX, SUFFIX);
    }

    public void printRecommendMenu(String coachName, List<Menu> recommends) {
        StringJoiner joiner = generateJoiner();
        joiner.add(coachName);
        for (Menu recommend : recommends) {
            joiner.add(recommend.getName());
        }
        System.out.println(joiner);
    }
}
