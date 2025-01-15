package lotto.service;

public class LottoService {

    public boolean isDivisibleByThousand(int amount) {
        return amount % 1000 == 0;
    }
}
