package lotto;

public class LottoService {
    public boolean isValidPurchaseInput(int input) {
        if(input % 1000 == 0) return false;
        return true;
    }
}
