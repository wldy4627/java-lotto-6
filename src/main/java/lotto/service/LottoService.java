package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoService {
    List<Lotto> lottos = new ArrayList<>();

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
}
