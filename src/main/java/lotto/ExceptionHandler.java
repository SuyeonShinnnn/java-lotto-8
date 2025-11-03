package lotto;

public class ExceptionHandler {

    public static void handle(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.println("\n" + ErrorMessage.INVALID_INPUT_FORMAT.getMessage() + "\n");
        }
        else if (e instanceof IllegalArgumentException) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
        else {
            System.out.println("\n[ERROR] 예기치 못한 오류가 발생했습니다: " + e.getMessage() + "\n");
        }
    }
}
