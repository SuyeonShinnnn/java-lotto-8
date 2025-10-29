package lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoServiceTest {
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
        assertTrue(e.getMessage().contains("1,000원 이상"));
    }

    @Test
    void 로또_구매_금액이_1000원_단위가_아닐때_예외_발생() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->{
            service.calculatePurchaseCount(1500);
        });
        assertTrue(e.getMessage().contains("1,000원 단위"));
    }
}