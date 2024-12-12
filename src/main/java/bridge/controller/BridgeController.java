package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.model.BridgeGame;
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

        BridgeGame bridgeGame = new BridgeGame(bridge);
        playGame(bridgeGame);
        displayResult();
    }

    private void playGame(BridgeGame bridgeGame) {
        while (!bridgeGame.isEnd()) {
            gameCount++;
            initializeMoveMap();

            runRound(bridgeGame);
            bridgeGame.end();
        }
    }

    private void initializeMoveMap() {
        List<String> upBridge = new ArrayList<>();
        List<String> downBridge = new ArrayList<>();

        moveMap = new ArrayList<>();
        moveMap.add(upBridge);
        moveMap.add(downBridge);
    }

    private void runRound(BridgeGame bridgeGame) {
        for (int round = 0; round < bridgeGame.getBridge().size(); round++) {
            String movingCell = tryMovingCell();
            String moveResult = bridgeGame.move(round, movingCell);

            createCurrentMoveMap(movingCell, moveResult);
            outputView.printMap(moveMap);

            if (isFail(moveResult)) {
                String gameCommand = tryGameCommand();
                canRetry(bridgeGame, gameCommand);
                break;
            }
        }
    }

    private void createCurrentMoveMap(String movingCell, String moveResult) {
        if (movingCell.equals("U")) {
            moveMap.get(0).add(moveResult);
            moveMap.get(1).add(" ");
        }

        if (movingCell.equals("D")) {
            moveMap.get(1).add(moveResult);
            moveMap.get(0).add(" ");
        }
    }

    private boolean isFail(String moveResult) {
        return moveResult.equals("X");
    }

    private void canRetry(BridgeGame bridgeGame, String gameCommand) {
        if (bridgeGame.retry(gameCommand)) {
            playGame(bridgeGame);
        }
        bridgeGame.end();
    }

    private void displayResult() {
        successJudgement();
        outputView.printResult(moveMap, successful, gameCount);
    }

    private void successJudgement() {
        if (isNotSuccessful()) {
            successful = "실패";
        }
    }

    private boolean isNotSuccessful() {
        return moveMap.get(0).contains("X") || moveMap.get(1).contains("X");
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
