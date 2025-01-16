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
}
