package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.repository.Category;

public class CategoryRecommender {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 5;
    private static final int MAX_COUNT = 3;
    private Map<Category, Integer> counter = new HashMap<>();
    private List<Category> recommend = new ArrayList<>();

    public Category recommend() {
        while (true) {
            int number = randomNumberGenerator();
            Category category = getCategoryByNumber(number);
            int count = counter.getOrDefault(category, 0);

            if (count < MAX_COUNT) {
                counter.put(category, count + 1);
                recommend.add(category);
                return category;
            }
        }
    }

    private Category getCategoryByNumber(int number) {
        Category category = Arrays.stream(Category.values())
                .filter(result -> result.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리 번호입니다."));
        return category;
    }

    private int randomNumberGenerator() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }

    public List<Category> getRecommend() {
        return recommend;
    }
}
