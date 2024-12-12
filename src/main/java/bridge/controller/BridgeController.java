package bridge.controller;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;
    private List<List<String>> moveMap;
    private int gameCount;
    private String successful;

    public BridgeController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameCount = 0;
        this.successful = "성공";
    }

    public void run() {
        outputView.printWellComeMessage();
        List<String> bridge = tryCreateBridge();

        //새로운 게임 생성
        BridgeGame bridgeGame = new BridgeGame(bridge);

        //게임 진행
        playGame(bridgeGame);

        if (moveMap.get(0).contains("X") || moveMap.get(1).contains("X")) {
            successful = "실패";
        }
    }

    private void playGame(BridgeGame bridgeGame) {
        while (!bridgeGame.isEnd()) { //종료 상태가 될 때 까지 진행

            gameCount++;
            moveMap = new ArrayList<>();
            List<String> upBridge = new ArrayList<>();
            List<String> downBridge = new ArrayList<>();

            moveMap.add(upBridge);
            moveMap.add(downBridge);

            //다리 길이만큼 반복
            for (int round = 0; round < bridgeGame.getBridge().size(); round++) {
                //플레이어 칸 입력
                String movingCell = tryMovingCell();
                String moveResult = bridgeGame.move(round, movingCell); //이동결과

                if (movingCell.equals("U")) {
                    upBridge.add(moveResult);
                    downBridge.add(" ");
                }

                if (movingCell.equals("D")) {
                    downBridge.add(moveResult);
                    upBridge.add(" ");
                }

                //현재 결과 출력
                outputView.printMap(moveMap);

                //재시작 입력 여부
                if (moveResult.equals("X")) {
                    String gameCommand = tryGameCommand();

                    if (bridgeGame.retry(gameCommand)) {
                        playGame(bridgeGame);
                    }

                    //종료
                    bridgeGame.end();
                    break;
                }
            }
            bridgeGame.end();
        }
    }


    private List<String> tryCreateBridge() {
        return requestRead(() -> {
            int bridgeSize = inputView.readBridgeSize();

            BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
            return bridgeMaker.makeBridge(bridgeSize);
        });
    }

    private String tryMovingCell() {
        return requestRead(inputView::readMoving);
    }

    private String tryGameCommand() {
        return requestRead(inputView::readGameCommand);
    }

    private <T> T requestRead(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
