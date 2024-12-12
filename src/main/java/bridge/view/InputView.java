package bridge.view;

import bridge.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String NEXT_LINE = System.lineSeparator();

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println(NEXT_LINE + "다리의 길이를 입력해주세요.");
        String inputBridge = Console.readLine();

        return InputConverter.convertBridgeSize(inputBridge);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println(NEXT_LINE + "이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String inputMovingCell = Console.readLine();

        validateMoveCell(inputMovingCell);

        return inputMovingCell.trim();
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println(NEXT_LINE + "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        String inputCommand = Console.readLine();

        validateCommand(inputCommand);

        return inputCommand.trim();
    }

    private void validateCommand(String inputCommand) {
        if (!inputCommand.trim().equals("R") && !inputCommand.trim().equals("Q")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_COMMAND.getErrorMessage());
        }
    }

    private void validateMoveCell(String inputMovingCell) {
        if (!inputMovingCell.trim().equals("U") && !inputMovingCell.trim().equals("D")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MOVING_CELL.getErrorMessage());
        }
    }
}
