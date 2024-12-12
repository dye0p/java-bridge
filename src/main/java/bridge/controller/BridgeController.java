package bridge.controller;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class BridgeController {

    private final InputView inputView;
    private final OutputView outputView;

    public BridgeController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWellComeMessage();
        List<String> bridge = tryCreateBridge();

        //새로운 게임 생성
        BridgeGame bridgeGame = new BridgeGame(bridge);

        //게임 진행
        while (!bridgeGame.end()) {

            //플레이어 칸 입력
            for (String round : bridge) {
                String movingCell = tryMovingCell();
            }

        }


    }

    private String tryMovingCell() {
        return requestRead(inputView::readMoving);
    }

    private List<String> tryCreateBridge() {
        return requestRead(() -> {
            int bridgeSize = inputView.readBridgeSize();

            BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
            return bridgeMaker.makeBridge(bridgeSize);
        });
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
