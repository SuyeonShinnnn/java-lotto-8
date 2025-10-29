package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
        return IntStream.range(0, purchaseCount)
                .mapToObj(i -> new Lotto(
                        Randoms.pickUniqueNumbersInRange(1, 45, 6)
                                .stream()
                                .sorted()
                                .toList()
                )).toList();
    }

    public List<Integer> parseInput(String[] input) {
        return Arrays.stream(input)
                .map(Integer::parseInt)
                .toList();
    }
}
