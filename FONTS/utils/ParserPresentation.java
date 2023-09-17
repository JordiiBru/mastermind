package utils;

import java.util.*;

public class ParserPresentation {
    public static List<Map.Entry<String, Integer>> convertToEntryList(ArrayList<Pair<String, Integer>> pairList) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>();
        for (Pair<String, Integer> pair : pairList) {
            Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(pair.first(), pair.second());
            entryList.add(entry);
        }
        return entryList;
    }

    public static Map<String, Map.Entry<String, Integer>> convertToEntryMap(Map<String, Pair<String, Integer>> pairMap) {
        Map<String, Map.Entry<String, Integer>> entryMap = new HashMap<>();
        for (Map.Entry<String, Pair<String, Integer>> entry : pairMap.entrySet()) {
            String key = entry.getKey();
            Pair<String, Integer> pair = entry.getValue();
            Map.Entry<String, Integer> mapEntry = new AbstractMap.SimpleEntry<>(pair.first(), pair.second());
            entryMap.put(key, mapEntry);
        }
        return entryMap;
    }

    public static String[][] convertBoardToString(Ball_color[][] board, int turn) {
        int rows = board.length;
        int cols = board[0].length;

        String[][] stringBoard = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == null) {
                break;
            }
            for (int j = 0; j < cols; j++) {
                stringBoard[i][j] = board[i][j].toString();
            }
        }
        return stringBoard;
    }

    public static String[] convertToStringArray(Ball_color[] code) {
        String[] returnValue = new String[code.length];
        for (int i = 0; i < returnValue.length; ++i) returnValue[i] = code[i].toString();
        return returnValue;
    }

    public static List<Map.Entry<Integer, Integer>> convertToEntryList(Pair<Integer, Integer>[] pairArray, int turn) {
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>();
        for (Pair<Integer, Integer> integerIntegerPair : pairArray) {
            if (integerIntegerPair == null) break;
            Pair<Integer, Integer> pair = integerIntegerPair;
            entryList.add(new AbstractMap.SimpleEntry<>(pair.first(), pair.second()));
        }


        return entryList;
    }

}
