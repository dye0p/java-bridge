package bridge.exception;

public enum ErrorMessage {

    ERROR_SIGNATURE("[ERROR] "),
    INVALID_BRIDGE_INPUT("다리 길이는 숫자만 입력할 수 있습니다."),
    INVALID_BRIDGE_SIZE("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    INVALID_MOVING_CELL("이동할 칸은 U 또는 D로 입력해야 합니다."),
    INVALID_COMMAND("재시도/종료 입력은 R 또는 Q로 입력해야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return ERROR_SIGNATURE.message + this.message;
    }
}
