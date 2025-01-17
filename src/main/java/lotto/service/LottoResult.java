package lotto.service;

import lotto.Lotto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private static final int MATCH_3_REWARD = 5000;
    private static final int MATCH_4_REWARD = 50000;
    private static final int MATCH_5_REWARD = 1500000;
    private static final int MATCH_5_BONUS_REWARD = 30000000;
    private static final int MATCH_6_REWARD = 2000000000;

    private final Map<Integer, Integer> lottoResultMap = new LinkedHashMap<>();

    public LottoResult() {
        initializeLottoResult();
    }

    private void initializeLottoResult() {
        lottoResultMap.put(MATCH_3_REWARD, 0);
        lottoResultMap.put(MATCH_4_REWARD, 0);
        lottoResultMap.put(MATCH_5_REWARD, 0);
        lottoResultMap.put(MATCH_5_BONUS_REWARD, 0);
        lottoResultMap.put(MATCH_6_REWARD, 0);
    }

    public Map<Integer, Integer> computeLottoResult(List<Integer> chosenLottoNum, List<Lotto> lottos) {
        int bonusNum = chosenLottoNum.get(6);
        List<Integer> lottoNum = chosenLottoNum.subList(0, 6);

        for (Lotto lotto : lottos) {
            List<Integer> purchasedLottoNum = lotto.getNumbers();
            int matchCount = (int) purchasedLottoNum.stream()
                    .filter(lottoNum::contains)
                    .count();

            boolean bonusMatch = purchasedLottoNum.contains(bonusNum);

            updateResult(matchCount, bonusMatch);
        }

        return lottoResultMap;
    }

    private void updateResult(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            lottoResultMap.put(MATCH_6_REWARD, lottoResultMap.get(MATCH_6_REWARD) + 1);
        } else if (matchCount == 5 && bonusMatch) {
            lottoResultMap.put(MATCH_5_BONUS_REWARD, lottoResultMap.get(MATCH_5_BONUS_REWARD) + 1);
        } else if (matchCount == 5) {
            lottoResultMap.put(MATCH_5_REWARD, lottoResultMap.get(MATCH_5_REWARD) + 1);
        } else if (matchCount == 4) {
            lottoResultMap.put(MATCH_4_REWARD, lottoResultMap.get(MATCH_4_REWARD) + 1);
        } else if (matchCount == 3) {
            lottoResultMap.put(MATCH_3_REWARD, lottoResultMap.get(MATCH_3_REWARD) + 1);
        }
    }

}
