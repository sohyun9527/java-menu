package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Coaches {
    private final List<Coach> coaches;

    public Coaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public static Coaches of(String names) {
        List<String> coachNames = List.of(names.split(",", -1));
        List<Coach> group = new ArrayList<>();
        for (String name : coachNames) {
            group.add(Coach.of(name));
        }
        validateDuplicateName(group);
        return new Coaches(group);
    }

    private static void validateDuplicateName(List<Coach> group) {
        if (group.stream().distinct().count() != group.size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 코치명이 있습니다.");
        }
    }

    public List<Coach> getCoaches() {
        return coaches;
    }
}
