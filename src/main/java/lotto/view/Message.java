package lotto.view;

public enum Message {
    LOTTO_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    CHOOSE_LOTTO_NUMBERS("당첨 번호를 입력해 주세요."),
    CHOOSE_BONUS_NUMBERS("보너스 번호를 입력해 주세요.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
