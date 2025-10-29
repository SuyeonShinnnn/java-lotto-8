package lotto;

import java.util.ArrayList;
import java.util.List;

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
            List<Lotto> tickets = service.generateLottoNumbers(purchaseCount);

        } catch (IllegalArgumentException e) {

        }
    }
}
