package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.*;

public class LottoService {
    private final LottoValidator validator = new LottoValidator();
    private final LottoProfitCalculator profitCalculator = new LottoProfitCalculator();

    public void validatePurchaseAmount(int purchaseAmount) {
        validator.validatePurchaseAmount(purchaseAmount);
    }

    public List<Lotto> generatorLottos(int lottoCnt) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCnt; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }

    public List<Integer> chooseLottoNum(String numbers) {
        List<Integer> chosenLottoNum = Arrays.stream(numbers.split(","))
                                        .map(Integer::parseInt)
                                        .toList();

        for (int lottoNumber : chosenLottoNum) {
            validator.validateRange(lottoNumber);
        }

        validator.validateDuplicate(chosenLottoNum);

        return chosenLottoNum;
    }

    public int setBonusNum(int bonusNum, List<Integer> chosenLottoNum) {
        validator.validateRange(bonusNum);

        List<Integer> updatedLottoNum = new ArrayList<>(chosenLottoNum);
        updatedLottoNum.add(bonusNum);
        validator.validateDuplicate(updatedLottoNum);

        return bonusNum;
    }

    public Map<Integer, Integer> computeLottoResult(List<Integer> chosenLottoNum, List<Lotto> lottos) {
        LottoResult lottoResult = new LottoResult();
        return lottoResult.computeLottoResult(chosenLottoNum, lottos);
    }

    public Double calculateProfit(int purchaseAmount, Map<Integer, Integer> lottoResult) {
        return profitCalculator.calculateProfit(purchaseAmount, lottoResult);
    }
}
