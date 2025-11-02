package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoService service;
    private final LottoView view;

    public LottoController() {
        this.service = new LottoService();
        this.view = new LottoView();
    }

    public void run() {
        try {
            int amount = Integer.parseInt(view.inputPurchaseAmount());
            int purchaseCount = service.calculatePurchaseCount(amount);

            view.outputPurchaseCount(purchaseCount);

            List<Lotto> tickets = service.generateLottoNumbers(purchaseCount);
            view.outputLottoNumbers(tickets);

            String[] inputWinningNumber = view.inputWinningNumber().split(",");
            List<Integer> winningNumbers = service.parseWinningNumberInput(inputWinningNumber);

            String inputBonusNumber = view.inputBonusNumber();
            int bonusNumber = Integer.parseInt(inputBonusNumber);

            Map<LottoWinningRank, Integer> result = service.getWinningResult(tickets, winningNumbers, bonusNumber);
            double winningStatistics = service.calculateWinningStatistics(result, amount);
            view.outputWinningResult(result, winningStatistics);
        } catch (IllegalArgumentException e) {

        }
    }
}
