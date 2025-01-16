package lotto.service;

import lotto.Lotto;

import java.util.List;
import java.util.Map;

public class LottoResult {

    private static final int MATCH_3_REWARD = 5000;
    private static final int MATCH_4_REWARD = 50000;
    private static final int MATCH_5_REWARD = 1500000;
    private static final int MATCH_5_BONUS_REWARD = 30000000;
    private static final int MATCH_6_REWARD = 2000000000;

    private void initializeLottoResult(Map<Integer, Integer> lottoResult) {
        lottoResult.put(MATCH_3_REWARD, 0);
        lottoResult.put(MATCH_4_REWARD, 0);
        lottoResult.put(MATCH_5_REWARD, 0);
        lottoResult.put(MATCH_5_BONUS_REWARD, 0);
        lottoResult.put(MATCH_6_REWARD, 0);
    }

    public Map<Integer, Integer> computeLottoResult(List<Integer> chosenLottoNum, List<Lotto> lottos, Map<Integer, Integer> lottoResult) {
        int bonusNum = chosenLottoNum.get(6);
        List<Integer> lottoNum = chosenLottoNum.subList(0, 6);

        initializeLottoResult(lottoResult);

        for (Lotto lotto : lottos) {
            List<Integer> purchasedLottoNum = lotto.getNumbers();
            int matchCount = (int) purchasedLottoNum.stream()
                    .filter(lottoNum::contains)
                    .count();

            boolean bonusMatch = purchasedLottoNum.contains(bonusNum);

            updateLottoResult(matchCount, bonusMatch, lottoResult);
        }

        return lottoResult;
    }

    private void updateLottoResult(int matchCount, boolean bonusMatch, Map<Integer, Integer> lottoResult) {
        if (matchCount == 6) {
            lottoResult.put(MATCH_6_REWARD, lottoResult.get(MATCH_6_REWARD) + 1);
        } else if (matchCount == 5 && bonusMatch) {
            lottoResult.put(MATCH_5_BONUS_REWARD, lottoResult.get(MATCH_5_BONUS_REWARD) + 1);
        } else if (matchCount == 5) {
            lottoResult.put(MATCH_5_REWARD, lottoResult.get(MATCH_5_REWARD) + 1);
        } else if (matchCount == 4) {
            lottoResult.put(MATCH_4_REWARD, lottoResult.get(MATCH_4_REWARD) + 1);
        } else if (matchCount == 3) {
            lottoResult.put(MATCH_3_REWARD, lottoResult.get(MATCH_3_REWARD) + 1);
        }
    }


    // 수익률 계산
    public Double calculateLottoProfit(int purchaseAmount, Map<Integer, Integer> lottoResult) {
        int totalProfit = calculateTotalProfit(lottoResult);

        return ((double) totalProfit / purchaseAmount) * 100;
    }

    private int calculateTotalProfit(Map<Integer, Integer> lottoResult) {
        int totalProfit = 0;

        for (Map.Entry<Integer, Integer> entry : lottoResult.entrySet()) {
            totalProfit += entry.getKey() * entry.getValue();
        }

        return totalProfit;
    }
}
