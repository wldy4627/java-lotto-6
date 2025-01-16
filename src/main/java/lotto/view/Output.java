package lotto.view;

import lotto.Lotto;

import java.util.List;

public class Output {

    public void printLottoPurchaseMessage() {
        System.out.println(Message.LOTTO_PURCHASE_AMOUNT.getMessage());
    }

    public void printLottoCnt(int lottoCnt) {
        System.out.println("\n" + lottoCnt + "개를 구매했습니다.");
    }
    public void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void printChooseLottoNumbersMessage() {
        System.out.println("\n" + Message.CHOOSE_LOTTO_NUMBERS.getMessage());
    }

    public void printChooseBonusNumbersMessage() {
        System.out.println("\n" + Message.CHOOSE_BONUS_NUMBERS.getMessage());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }


}
