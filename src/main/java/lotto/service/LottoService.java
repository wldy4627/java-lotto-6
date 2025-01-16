package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.*;

public class LottoService {

    public void validatePurchaseAmount(int purchaseAmount) {
        if (!isDivisibleByThousand(purchaseAmount)) {
            throw new IllegalArgumentException("[ERROR] 로또 구매 금액은 1,000원 단위여야 합니다.");
        }
    }

    public boolean isDivisibleByThousand(int amount) {
        return amount % 1000 == 0;
    }

    public List<Lotto> getLottos(int lottoCnt) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCnt; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }

        return lottos;
    }

    public List<Integer> setLottoNumbers(String numbers) {
        Integer[] lottoNumbersArray = Arrays.stream(numbers.split(","))
                                        .map(Integer::parseInt)
                                        .toArray(Integer[]::new);

        List<Integer> chosenLottoNum = new ArrayList<>();

        for (int lottoNumber : lottoNumbersArray) {
            validateRange(lottoNumber);
            chosenLottoNum.add(lottoNumber);
        }

        validateDuplicate(chosenLottoNum);

        return chosenLottoNum;
    }

    public int setBonusNumbers(int number, List<Integer> chosenLottoNum) {
        validateRange(number);

        chosenLottoNum.add(number);
        validateDuplicate(chosenLottoNum);

        return number;
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> chosenLottoNum) {
        Set<Integer> set  = new HashSet<>();
        for (Integer number : chosenLottoNum) {
            if (!set.add(number)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않아야 합니다.");
            }
        }
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

            if (matchCount == 6) {
                lottoResult.put(2000000000, lottoResult.get(2000000000) + 1);
            } else if (matchCount == 5 && bonusMatch) {
                lottoResult.put(30000000, lottoResult.get(30000000) + 1);
            } else if (matchCount == 5) {
                lottoResult.put(1500000, lottoResult.get(1500000) + 1);
            } else if (matchCount == 4) {
                lottoResult.put(50000, lottoResult.get(50000) + 1);
            } else if (matchCount == 3) {
                lottoResult.put(5000, lottoResult.get(5000) + 1);
            }
        }

        return lottoResult;
    }

    private void initializeLottoResult(Map<Integer, Integer> lottoResult) {
        lottoResult.put(5000, 0);
        lottoResult.put(50000, 0);
        lottoResult.put(1500000, 0);
        lottoResult.put(30000000, 0);
        lottoResult.put(2000000000, 0);
    }

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
