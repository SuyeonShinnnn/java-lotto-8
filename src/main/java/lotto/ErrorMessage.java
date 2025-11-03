package lotto;

public enum ErrorMessage {
    INVALID_PURCHASE_AMOUNT("[ERROR] 구매 금액은 1,000원 이상이어야 합니다."),
    INVALID_PURCHASE_UNIT("[ERROR] 로또는 1,000원 단위로만 구매 가능합니다."),
    INVALID_LOTTO_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("[ERROR] 중복된 로또 번호가 존재합니다."),
    INVALID_NUMBER_RANGE("[ERROR] 로또 번호는 1~45까지의 숫자여야 합니다."),
    DUPLICATED_BONUS_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
