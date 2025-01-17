package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.Input;
import lotto.view.Output;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Output output = new Output();
        Input input = new Input();
        LottoService lottoService = new LottoService();

        LottoController lottoController = new LottoController(output, input, lottoService);
        lottoController.run();
    }
}
