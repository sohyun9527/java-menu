package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.repository.Category;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CategoryRecommender {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 5;
    private static final int MAX_COUNT = 3;
    private Map<Category, Integer> counter = new HashMap<>();

    public Category recommend() {
        while (true) {
            int number = randomNumberGenerator();
            Category category = Arrays.stream(Category.values())
                    .filter(result -> result.getNumber() == number)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리 번호입니다."));

            if (!isOverCount(category)) {
                counter.put(category, counter.getOrDefault(category, 0) + 1);
                return category;
            }
        }
    }

    public boolean isOverCount(Category category) {
        return counter.get(category) < MAX_COUNT;
    }

    private int randomNumberGenerator() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }
}
