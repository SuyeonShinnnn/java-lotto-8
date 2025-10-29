package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    public int calculatePurchaseCount(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1,000원 이상이어야 합니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 로또는 1,000원 단위로 구매 가능합니다.");
        }
        return amount / 1000;
    }

    public List<Lotto> generateLottoNumbers(int purchaseCount) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < purchaseCount; i++) {
            Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            tickets.add(lotto);
        }
        return tickets;
    }
}
