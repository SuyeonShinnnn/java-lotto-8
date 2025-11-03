package lotto;

public class LottoExceptionHandler {

    public static void handle(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.println(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
        else if (e instanceof IllegalArgumentException) {
            System.out.println(e.getMessage());
        }
        else {
            System.out.println("[ERROR] 예기치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
