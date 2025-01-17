package lotto.view;

import java.util.Scanner;

public class Input {

    Scanner sc = new Scanner(System.in);

    public int scanLottoPurchaseAmount() {
        String purchaseAmount = sc.nextLine();

        if (!isNumeric(purchaseAmount)) {
            throw new IllegalArgumentException("[ERROR] 로또 구매 금액은 숫자로 입력하셔야 합니다.");
        }

        return Integer.parseInt(purchaseAmount);
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String scanChooseLottoNum() {
        String numbers = sc.nextLine();
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호를 입력해주세요.");
        }
        return numbers;
    }

    public int scanChooseBonusNum() {
        String number = sc.nextLine();

        if (!isNumeric(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }

        return Integer.parseInt(number);
    }
}
