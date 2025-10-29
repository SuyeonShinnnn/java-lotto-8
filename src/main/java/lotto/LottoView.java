package lotto;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class LottoView {
    public String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return readLine();
    }

    public void outputPurchaseCount(int purchaseCount) {
        System.out.println(purchaseCount + "개를 구매했습니다.");
    }

    public void outputLottoNumbers(List<Lotto> lottos) {
        for(Lotto l: lottos) {
            System.out.println(l.getNumbers());
        }
    }
}
