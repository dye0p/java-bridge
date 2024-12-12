package bridge;

import bridge.model.GameStatus;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<String> bridge;
    private GameStatus gameStatus;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public String move(int round, String movingCell) {
        //해당 라운드에 해당 하는 다리와 이동할 칸을 비교한다.
        String bridgeCell = bridge.get(round);

        if (bridgeCell.equals(movingCell)) {
            return "O";
        }

        return "X";
    }

    public void end() {
        gameStatus = GameStatus.END;
    }

    public boolean isEnd() {
        return gameStatus == GameStatus.END;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String gameCommand) {
        return gameCommand.equals("R");
    }

    public List<String> getBridge() {
        return bridge;
    }
}
