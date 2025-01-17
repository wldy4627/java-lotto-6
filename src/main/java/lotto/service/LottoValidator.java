package lotto.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoValidator {

    public void validatePurchaseAmount(int purchaseAmount) {
        if (!isDivisibleByThousand(purchaseAmount)) {
            throw new IllegalArgumentException("[ERROR] 로또 구매 금액은 1,000원 단위여야 합니다.");
        }
    }

    public boolean isDivisibleByThousand(int amount) {
        return amount % 1000 == 0;
    }

    public void validateRange(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public void validateDuplicate(List<Integer> chosenLottoNum) {
        Set<Integer> set  = new HashSet<>();
        for (Integer number : chosenLottoNum) {
            if (!set.add(number)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않아야 합니다.");
            }
        }
    }
}
