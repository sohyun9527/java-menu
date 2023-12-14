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
    private static final int MAX_RECOMMEND = 2;

    private final Map<Category, Integer> counter = new HashMap<>();
    private final List<Category> recommendCategories = new ArrayList<>();

    private Category getValidCategoryRecommend() {
        while (true) {
            Category category = getRandomCategory();
            if (!isOverRecommend(category)) {
                updateRecommendInfo(category);

                return category;
            }
        }
    }

    private void updateRecommendInfo(Category category) {
        recommendCategories.add(category);
        counter.put(category, counter.getOrDefault(category, 0) + 1);
    }

    public boolean isOverRecommend(Category category) {
        int count = counter.get(category);

        return count > MAX_RECOMMEND;
    }

    private Category getRandomCategory() {
        int randomNumber = randomNumberGenerator();

        return Arrays.stream(Category.values())
                .filter(category -> category.getNumber() == randomNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리 번호입니다."));
    }

    private int randomNumberGenerator() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }

    public List<Category> getRecommendCategories() {
        return recommendCategories;
    }
}
