package bridge.view;

import bridge.exception.ErrorMessage;

public class InputConverter {

    public static int convertBridgeSize(String inputBridge) {
        return parseBridgeSize(inputBridge);
    }

    private static int parseBridgeSize(String inputBridge) {
        try {
            return Integer.parseInt(inputBridge.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BRIDGE_INPUT.getErrorMessage());
        }
    }
}
