package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRecommender {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 5;
    private static final int MAX_COUNT = 2;
    private List<Category> recommended = new ArrayList<>();
    private Map<Category, Integer> counter = new HashMap<>();

    public Category recommend() {
        while (true) {
            int randomNumber = randomNumberGenerate();
            Category category = getOneCategory(randomNumber);

            if (!isOverRecommend(category)) {
                updateCount(category);
                return category;
            }
        }
    }

    private void updateCount(Category category) {
        recommended.add(category);
        counter.put(category, counter.getOrDefault(category, 0) + 1);
    }

    private boolean isOverRecommend(Category category) {
        int count = counter.getOrDefault(category, 0);

        return count > MAX_COUNT;
    }

    public Category getOneCategory(int number) {
        return Arrays.stream(Category.values())
                .filter(value -> value.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리 번호입니다."));
    }

    public int randomNumberGenerate() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }

    public List<Category> getRecommended() {
        return recommended;
    }
}
