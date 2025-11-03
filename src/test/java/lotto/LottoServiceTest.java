package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LottoServiceTest {
    private LottoService service;

    @BeforeEach
    void setup() {
        service = new LottoService();
    }

    @Test
    void 구매금액을_입력하면_구매개수를_반환한다() {
        int count = service.calculatePurchaseCount(5000);
        assertEquals(5, count);
    }

    @Test
    void 구매수량만큼_로또번호를_반환한다() {
        int purchaseCount = 5;
        List<Lotto> tickets = service.generateLottoNumbers(purchaseCount);

        assertEquals(purchaseCount, tickets.size());
        for (Lotto ticket : tickets) {
            assertEquals(6, ticket.getNumbers().size());
            assertTrue(ticket.getNumbers().stream().allMatch(n -> n >= 1 && n <= 45));
        }
    }

    @Test
    void 문자열로_입력된_값이_Lotto객체로_정상_변환된다() {
        String[] input = {"1", "2", "3", "4", "5", "6"};
        Lotto lotto = service.parseWinningNumberInput(input);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), lotto.getNumbers());
    }

    @Test
    void 보너스번호가_1에서45까지의_숫자이면서_당첨번호와_겹치지_않으면_예외가_발생하지_않는다() {
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertDoesNotThrow(() -> service.validateBonusNumber(7, winningNumbers));
    }

    @Test
    void 당첨결과_계산한다() {
        List<Lotto> tickets = List.of(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),       // 6개 일치
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)),       // 5개 + 보너스
                new Lotto(Arrays.asList(1, 2, 3, 4, 9, 10)),      // 4개 일치
                new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13))     // 3개 일치
        );
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Map<LottoWinningRank, Integer> result = service.getWinningResult(tickets, winningNumbers, bonusNumber);

        assertEquals(1, result.getOrDefault(LottoWinningRank.FIRST, 0));
        assertEquals(1, result.getOrDefault(LottoWinningRank.SECOND, 0));
        assertEquals(1, result.getOrDefault(LottoWinningRank.FOURTH, 0));
        assertEquals(1, result.getOrDefault(LottoWinningRank.FIFTH, 0));
    }

    @Test
    void 수익률_계산() {
        Map<LottoWinningRank, Integer> result = new HashMap<>();
        result.put(LottoWinningRank.FIRST, 1);
        double statistics = service.calculateWinningStatistics(result, 2000);

        assertEquals(100_000_000.0, statistics, 0.001);
    }
}
