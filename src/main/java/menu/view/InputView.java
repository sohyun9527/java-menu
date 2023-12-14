package menu.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_COACH_NAMES = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final String REQUEST_HATE_MENUS = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";


    public String readCoachNames() {
        System.out.println(REQUEST_COACH_NAMES);
        String input = getUserInput();

        validateEmptyLine(input);
        return input;
    }

    public String readHateMenus() {
        System.out.println(REQUEST_HATE_MENUS);

        return getUserInput();
    }

    private String getUserInput() {
        return Console.readLine();
    }

    private void validateEmptyLine(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 아무것도 입력하지 않았습니다.");
        }
    }
}
