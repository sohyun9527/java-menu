package menu.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_COACH_NAME = "코치의 이름을 입력해 주세요. (, 로 구분)";
    private static final String REQUEST_HATE_MENU = "%s(이)가 못 먹는 메뉴를 입력해 주세요.";


    public String readCoachNames() {
        System.out.println(REQUEST_COACH_NAME);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    public String readHateMenu(String name) {
        System.out.printf((REQUEST_HATE_MENU) + "%n", name);
        return getUserInput();
    }

    private void validateEmptyLine(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 아무것도 입력하지 않았습니다.");
        }
    }

    public String getUserInput() {
        return Console.readLine();
    }
}
