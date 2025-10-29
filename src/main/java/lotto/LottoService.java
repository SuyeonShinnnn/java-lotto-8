package lotto;

public class LottoService {
    public int calculatePurchaseCount(int amount) {
        if(amount < 1000) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1,000원 이상이어야 합니다.");
        }
        if(amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 로또는 1,000원 단위로 구매 가능합니다.");
        }
        return amount / 1000;
    }
}
