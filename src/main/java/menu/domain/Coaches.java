package menu.domain;

import java.util.HashSet;
import java.util.List;

public class Coaches {
    private static final int MIN_COUNT = 2;
    private static final int MAX_COUNT = 5;
    private final List<Coach> coaches;

    public Coaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public static Coaches of(List<Coach> coaches) {
        validateDuplicateNames(coaches);
        validateCoachCount(coaches);

        return new Coaches(coaches);
    }

    private static void validateCoachCount(List<Coach> coaches) {
        if (coaches.size() > MAX_COUNT || coaches.size() < MIN_COUNT) {
            throw new IllegalArgumentException("[ERROR] 메뉴를 추천받을 코치의 인원은 2 ~ 5 명까지 가능합니다.");
        }
    }

    private static void validateDuplicateNames(List<Coach> coaches) {
        int uniqueCoaches = new HashSet<>(coaches).size();

        if (uniqueCoaches != coaches.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 코치가 존재합니다!");
        }
    }

    public List<Coach> getCoaches() {
        return coaches;
    }
}
