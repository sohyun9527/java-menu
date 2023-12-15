package menu.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_COACH_NAME = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final String REQUEST_HATE_MENU = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";

    public String readCoachNames() {
        System.out.println(REQUEST_COACH_NAME);
        String input = getUserInput();
        validateBlank(input);

        return input;
    }

    public String readHateMenus() {
        System.out.println(REQUEST_HATE_MENU);
        return getUserInput();
    }

    public void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }

    public String getUserInput() {
        return Console.readLine();
    }
}
