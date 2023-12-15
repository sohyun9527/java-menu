package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;

public class MenuRecommender {


    public String shuffleAndRecommend(List<String> names) {
        return Randoms.shuffle(names).get(0);
    }

    public List<String> getMenuNamesByCategory(Category category) {
        return Arrays.stream(MenuBoard.values())
                .filter(menuBoard -> menuBoard.getCategory() == category)
                .findFirst()
                .map(MenuBoard::getMenuNames)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리입니다!"));
    }
}
