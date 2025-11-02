package lotto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class LottoView {
    public String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return readLine();
    }

    public String inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return readLine();
    }

    public String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
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

    public void outputWinningResult(Map<LottoWinningRank, Integer> result, double statistics) {
        System.out.println("당첨 통계");
        System.out.println("---");

        Comparator<LottoWinningRank> rankComparator = Comparator
                .comparingInt(LottoWinningRank::getMatchCount)
                .thenComparing(LottoWinningRank::hasBonus);

        Arrays.stream(LottoWinningRank.values())
                .sorted(rankComparator)
                .forEach(rank -> outputRankResult(rank, result));

        System.out.println("---");
        System.out.printf("총 수익률은 %.2f입니다.%n", statistics);
    }

    private void outputRankResult(LottoWinningRank rank, Map<LottoWinningRank, Integer> result) {
        System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                rank.getMatchCount(),
                rank.hasBonus() ? ", 보너스 볼 일치" : "",
                rank.getReward(),
                result.getOrDefault(rank, 0));
    }
}
