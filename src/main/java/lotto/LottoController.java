package lotto;

public class LottoController {
    private final LottoService service;
    private final LottoView view;

    public LottoController() {
        this.service = new LottoService();
        this.view = new LottoView();
    }

    public void run() {
        try{
            int amount = Integer.parseInt(view.inputPurchaseAmount());
            int purchaseCount = service.calculatePurchaseCount(amount);

        }catch (IllegalArgumentException e) {
            
        }
    }
}
