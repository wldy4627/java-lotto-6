package lotto.service;

public class LottoService {

    public void validatePurchaseAmount(int purchaseAmount) {
        if (!isDivisibleByThousand(purchaseAmount)) {
            throw new IllegalArgumentException("[ERROR] 로또 구매 금액은 1,000원 단위여야 합니다.");
        }
    }

    public boolean isDivisibleByThousand(int amount) {
        return amount % 1000 == 0;
    }
}
