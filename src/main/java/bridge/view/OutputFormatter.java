package bridge.view;

import java.util.List;
import java.util.StringJoiner;

public class OutputFormatter {

    private static final String NEXT_LINE = System.lineSeparator();

    public static String formatMoveMap(List<List<String>> moveMap) {
        StringJoiner sj = new StringJoiner(NEXT_LINE);

        List<String> upMap = moveMap.get(0);
        List<String> downMap = moveMap.get(1);

        String upJoin = String.join(" | ", upMap);
        String downJoin = String.join(" | ", downMap);

        sj.add("[ " + upJoin + " ]");
        sj.add("[ " + downJoin + " ]");

        return sj.toString();
    }
}
