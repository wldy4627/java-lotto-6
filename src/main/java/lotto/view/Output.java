package lotto.view;

public class Output {

    public void printLottoPurchaseMessage() {
        System.out.println(Message.LOTTO_PURCHASE_AMOUNT.getMessage());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
