package lotto;

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
        int amount = getPurchaseAmount();
        List<Lotto> tickets = generateLottoTickets(amount);

        Lotto winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);

        Map<LottoWinningRank, Integer> result = service.getWinningResult(tickets, winningNumbers, bonusNumber);
        double statistics = service.calculateWinningStatistics(result, amount);
        view.outputWinningResult(result, statistics);
    }

    private int getPurchaseAmount() {
        while (true) {
            try {
                int amount = Integer.parseInt(view.inputPurchaseAmount());
                int purchaseCount = service.calculatePurchaseCount(amount);
                view.outputPurchaseCount(purchaseCount);
                return amount;
            } catch (NumberFormatException e) {
                ExceptionHandler.handle(e);
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    private List<Lotto> generateLottoTickets(int amount) {
        int purchaseCount = service.calculatePurchaseCount(amount);
        List<Lotto> tickets = service.generateLottoNumbers(purchaseCount);
        view.outputLottoNumbers(tickets);
        return tickets;
    }

    private Lotto getWinningNumbers() {
        while (true) {
            try {
                String[] input = view.inputWinningNumber().split(",");
                return service.parseWinningNumberInput(input);
            } catch (NumberFormatException e) {
                ExceptionHandler.handle(e);
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    private int getBonusNumber(Lotto winningNumbers) {
        while (true) {
            try {
                int bonusNumber = Integer.parseInt(view.inputBonusNumber());
                service.validateBonusNumber(bonusNumber, winningNumbers);
                return bonusNumber;
            } catch (NumberFormatException e) {
                ExceptionHandler.handle(e);
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handle(e);
            }
        }
    }
}
