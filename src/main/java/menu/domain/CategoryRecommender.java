package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;

public class CategoryRecommender {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 5;

    public Category recommend(int number) {
        return Arrays.stream(Category.values())
                .filter(value -> value.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리 번호입니다."));
    }

    public int randomNumberGenerator() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }
}
