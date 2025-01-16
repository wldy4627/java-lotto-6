package lotto.controller;

import lotto.service.LottoService;
import lotto.view.Input;
import lotto.view.Output;

import java.util.List;

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

        int lottoCnt = purchaseAmount / 1000;
        output.printLottoCnt(lottoCnt);
        output.printLottoNumbers(lottoService.getLottos(lottoCnt));

        List<Integer> chosenLottoNum = chooseLottoNumbers();

    }

    private int getLottoPurchaseAmount() {
        while (true) {
            try {
                output.printLottoPurchaseMessage();
                int purchaseAmount = input.scanLottoPurchaseAmount();

                lottoService.validatePurchaseAmount(purchaseAmount);

                return purchaseAmount;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> chooseLottoNumbers() {
        while (true) {
            try {
                output.printChooseLottoNumbersMessage();
                List<Integer> chosenLottoNum = lottoService.setLottoNumbers(input.scanChooseLottoNumbers());

                output.printChooseBonusNumbersMessage();
                chosenLottoNum.add(lottoService.setBonusNumbers(input.scanChooseBonusNumbers()));

                return chosenLottoNum;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }
}
