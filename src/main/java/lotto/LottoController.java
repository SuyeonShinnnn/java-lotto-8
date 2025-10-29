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
            int purchaseCount = amount % 1000;

            if(service.isValidPurchaseInput(amount)) {
                throw new IllegalArgumentException("[ERROR] 로또는 1,000원 단위로 구매 가능합니다.");
            }
        }catch (IllegalArgumentException e) {
            
        }
    }
}
