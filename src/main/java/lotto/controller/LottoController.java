package lotto.controller;

import lotto.service.LottoService;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {

    private final Output output;
    private final Input input;
    private final LottoService lottoService;

    public LottoController(Output output, Input input, LottoService lottoService) {
        this.output = output;
        this.input = input;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseAmount = getLottoPurchaseAmount();
    }

    private int getLottoPurchaseAmount() {
        while (true) {
            try {
                output.printLottoPurchaseMessage();
                int purchaseAmount = input.scanLottoPurchaseAmount();
                validatePurchaseAmount(purchaseAmount);
                return purchaseAmount;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (!lottoService.isDivisibleByThousand(purchaseAmount)) {
            throw new IllegalArgumentException("[ERROR] 로또 구매 금액은 1,000원 단위여야 합니다.");
        }
    }


}
