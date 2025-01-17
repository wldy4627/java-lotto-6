package lotto.view;

import lotto.Lotto;

import java.util.List;
import java.util.Map;

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

    public void printChooseLottoNumMessage() {
        System.out.println("\n" + Message.CHOOSE_LOTTO_NUMBERS.getMessage());
    }

    public void printChooseBonusNumMessage() {
        System.out.println("\n" + Message.CHOOSE_BONUS_NUMBERS.getMessage());
    }

    public void printLottoResult(Map<Integer, Integer> lottoResultMap, List<String> lottoResultStr) {
        System.out.println("\n당첨통계\n---");
        int i = 0;
        for (Integer key : lottoResultMap.keySet()) {
            System.out.println(lottoResultStr.get(i) + " - " + lottoResultMap.get(key) + "개");
            i++;
        }
    }

    public void printLottoProfit(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
