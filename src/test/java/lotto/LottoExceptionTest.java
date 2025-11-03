package lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoExceptionTest {
    LottoService service = new LottoService();

    @Test
    void 로또_구매_금액이_1000단위일때_정상_구매_개수_반환() {
        int count = service.calculatePurchaseCount(5000);
        assertEquals(5, count);
    }

    @Test
    void 로또_구매_금액이_1000원_미만일때_예외_발생() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            service.calculatePurchaseCount(999);
        });
        assertEquals(ErrorMessage.INVALID_PURCHASE_AMOUNT.getMessage(), e.getMessage());
    }

    @Test
    void 로또_구매_금액이_1000원_단위가_아닐때_예외_발생() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            service.calculatePurchaseCount(1500);
        });
        assertEquals(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage(), e.getMessage());
    }

    @Test
    void 보너스번호가_1에서45까지의_범위를_벗어나면_예외_발생() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            service.validateBonusNumber(0, new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        });
        assertEquals(ErrorMessage.INVALID_NUMBER_RANGE.getMessage(), e.getMessage());
    }

    @Test
    void 보너스번호가_당첨번호와_겹치면_예외_발생() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            service.validateBonusNumber(1, new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        });
        assertEquals(ErrorMessage.DUPLICATED_BONUS_NUMBER.getMessage(), e.getMessage());
    }
}