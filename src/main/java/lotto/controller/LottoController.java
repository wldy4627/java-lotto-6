package lotto.controller;

import lotto.Lotto;
import lotto.service.LottoResult;
import lotto.service.LottoService;
import lotto.view.Input;
import lotto.view.Output;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final Output output;
    private final Input input;
    private final LottoService lottoService;
    private final LottoResult lottoResult;

    public LottoController(Output output, Input input, LottoService lottoService, LottoResult lottoResult) {
        this.output = output;
        this.input = input;
        this.lottoService = lottoService;
        this.lottoResult = lottoResult;
    }

    public void run() {
        int purchaseAmount = getLottoPurchaseAmount();

        int lottoCnt = purchaseAmount / 1000;
        output.printLottoCnt(lottoCnt);

        List<Lotto> lottos = lottoService.getLottos(lottoCnt);
        output.printLottoNumbers(lottos);

        List<Integer> chosenLottoNum = chooseLottoNumbers();
        Map<Integer, Integer> lottoResultMap = lottoResult.computeLottoResult(chosenLottoNum, lottos);

        output.printLottoResult(lottoResultMap, initializeLottoResultStr());
        output.printLottoProfit(lottoResult.calculateLottoProfit(purchaseAmount, lottoResultMap));
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
                chosenLottoNum.add(lottoService.setBonusNumbers(input.scanChooseBonusNumbers(), chosenLottoNum));

                return chosenLottoNum;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<String> initializeLottoResultStr() {
        List<String> lottoResultStr = new ArrayList<>();

        lottoResultStr.add("3개 일치 (5,000원)");
        lottoResultStr.add("4개 일치 (50,000원)");
        lottoResultStr.add("5개 일치 (1,500,000원)");
        lottoResultStr.add("5개 일치, 보너스 볼 일치 (30,000,000원)");
        lottoResultStr.add("6개 일치 (2,000,000,000원)");

        return lottoResultStr;
    }
}
