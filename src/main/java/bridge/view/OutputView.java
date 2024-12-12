package bridge.view;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String NEXT_LINE = System.lineSeparator();

    public void printWellComeMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<List<String>> moveMap) {
        String formatMoveMap = OutputFormatter.formatMoveMap(moveMap);
        System.out.println(formatMoveMap);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(List<List<String>> moveMap, String successful, int gameCount) {
        System.out.println(NEXT_LINE + "최종 게임 결과");
        printMap(moveMap);

        System.out.println("게임 성공 여부: " + successful);
        System.out.println("총 시도한 횟수: " + gameCount);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
