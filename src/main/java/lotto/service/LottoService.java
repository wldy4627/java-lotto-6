package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.*;

public class LottoService {
    List<Lotto> lottos = new ArrayList<>();
    Map<Integer, Integer> lottoResult = new LinkedHashMap<>();

    public void validatePurchaseAmount(int purchaseAmount) {
        if (!isDivisibleByThousand(purchaseAmount)) {
            throw new IllegalArgumentException("[ERROR] 로또 구매 금액은 1,000원 단위여야 합니다.");
        }
    }

    public boolean isDivisibleByThousand(int amount) {
        return amount % 1000 == 0;
    }

    public List<Lotto> getLottos(int lottoCnt) {

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
            validateLottoNumber(lottoNumber);
            chosenLottoNum.add(lottoNumber);
        }

        return chosenLottoNum;
    }

    public int setBonusNumbers(int number) {
        validateLottoNumber(number);

        return number;
    }

    private void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public Map<Integer, Integer> computeLottoResult(List<Integer> chosenLottoNum) {
        int bonusNum = chosenLottoNum.get(6);
        List<Integer> lottoNum = chosenLottoNum.subList(0, 6);

        initializeLottoResult();

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

    private void initializeLottoResult() {
        lottoResult.put(5000, 0);
        lottoResult.put(50000, 0);
        lottoResult.put(1500000, 0);
        lottoResult.put(30000000, 0);
        lottoResult.put(2000000000, 0);
    }

    public Double calculateLottoProfit(int purchaseAmount) {
        int totalProfit = calculateTotalProfit();

        return ((double) totalProfit / purchaseAmount) * 100;
    }

    private int calculateTotalProfit() {
        int totalProfit = 0;

        for (Map.Entry<Integer, Integer> entry : lottoResult.entrySet()) {
            totalProfit += entry.getKey() * entry.getValue();
        }

        return totalProfit;
    }
}
