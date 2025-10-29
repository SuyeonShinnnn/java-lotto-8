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

    public List<Integer> parseInput(String[] input) {
        return Arrays.stream(input)
                .map(Integer::parseInt)
                .toList();
    }

    public Map<LottoWinningRank, Integer> getWinningResult(List<Lotto> tickets, List<Integer> winningNumbers, int bonusNumber) {
        Map<LottoWinningRank, Integer> result = new HashMap<>();

        for (Lotto ticket : tickets) {
            int matchCount = (int) ticket.getNumbers().stream()
                    .filter(winningNumbers::contains)
                    .count();
            boolean hasBonus = ticket.getNumbers().contains(bonusNumber);

            LottoWinningRank rank = LottoWinningRank.valueOf(matchCount, hasBonus);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return result;
    }

    public int determineRank(boolean hasBonus, int matchCount) {
        if (matchCount == 6) return 1;
        else if (matchCount == 5 && hasBonus) return 2;
        else if (matchCount == 5 && !hasBonus) return 3;
        else if (matchCount == 4) return 4;
        else if (matchCount == 3) return 5;
        return 0;
    }

    public double calculateWinningStatistics(Map<LottoWinningRank, Integer> result) {
        int totalTickets = result.values().stream().mapToInt(Integer::intValue).sum();

        long totalIncome = result.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().getReward() * e.getValue())
                .sum();

        return (double) totalIncome / totalTickets;
    }
}
