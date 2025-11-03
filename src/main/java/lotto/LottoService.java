package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
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

    public List<Integer> parseWinningNumberInput(String[] input) {
        return Arrays.stream(input)
                .map(Integer::parseInt)
                .toList();
    }

    public void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다: ");
        }

        if(winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다");
        }
    }

    public Map<LottoWinningRank, Integer> getWinningResult(List<Lotto> tickets, List<Integer> winningNumbers, int bonusNumber) {
        Map<LottoWinningRank, Integer> result = new HashMap<>();
        for (Lotto ticket : tickets) {
            int matchCount = (int) ticket.getNumbers().stream()
                    .filter(winningNumbers::contains)
                    .count();
            if (matchCount < LottoWinningRank.FIFTH.getMatchCount()) continue;

            boolean hasBonus = ticket.getNumbers().contains(bonusNumber);

            LottoWinningRank rank = LottoWinningRank.valueOf(matchCount, hasBonus);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public double calculateWinningStatistics(Map<LottoWinningRank, Integer> result, int amount) {
        long totalIncome = result.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().getReward() * e.getValue())
                .sum();

        double statistics = (double) (totalIncome / amount) * 100;
        return statistics;
    }
}
