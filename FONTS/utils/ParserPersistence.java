package utils;

import java.util.*;

public class ParserPersistence {
    public static Ball_color parseColor(String color) {
        try {
            return Ball_color.valueOf(color);
        } catch (IllegalArgumentException e) {
            return Ball_color.Empty;
        }
    }

    public static List<String> convertMapToList(Map<String, Pair<String, Integer>> map) {
        List<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Pair<String, Integer>> entry : map.entrySet()) {
            String key = entry.getKey();
            Pair<String, Integer> pair = entry.getValue();
            String first = pair.first();
            String second = String.valueOf(pair.second());
            if(first == null || first.isEmpty()) {
                first = "null";
                second = "null";
            }
            String stringValue = key + ":" + first + ":" + second;
            resultList.add(stringValue);
        }
        return resultList;
    }

    public static Map<String, Pair<String, Integer>> convertListToMap(List<String> list) {
        Map<String, Pair<String, Integer>> resultMap = new HashMap<>();
        for (String str : list) {
            String[] parts = str.split(":");
            if (parts.length == 3) {
                String key = parts[0];
                String value1 = parts[1];
                String value2 = parts[2];
                int value;
                if(value2.equals("null")) value = -1;
                else value = Integer.parseInt(parts[2]);
                Pair<String, Integer> pair = new Pair<>(value1, value);
                resultMap.put(key, pair);
            }
        }
        return resultMap;
    }

    public static List<String> combineRankings(ArrayList<Pair<String, Integer>> rankingCB, ArrayList<Pair<String, Integer>> rankingCM) {
        List<String> combinedList = new ArrayList<>();

        // Add elements from rankingCM with the prefix "CM:"
        for (Pair<String, Integer> pair : rankingCM) {
            String element = "CM:" + pair.first() + ":" + pair.second();
            combinedList.add(element);
        }

        // Add elements from rankingCM with the prefix "CB:"
        for (Pair<String, Integer> pair : rankingCB) {
            String element = "CB:" + pair.first() + ":" + pair.second();
            combinedList.add(element);
        }

        return combinedList;
    }

    public static Pair<ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> splitRankings(List<String> combinedList) {
        ArrayList<Pair<String, Integer>> rankingCB = new ArrayList<>();
        ArrayList<Pair<String, Integer>> rankingCM = new ArrayList<>();

        for (String element : combinedList) {
            String[] parts = element.split(":");
            if (parts.length == 3) {
                String prefix = parts[0];
                String value1 = parts[1];
                int value2 = Integer.parseInt(parts[2]);

                Pair<String, Integer> pair = new Pair<>(value1, value2);

                if (prefix.equals("CB")) {
                    rankingCB.add(pair);
                } else if (prefix.equals("CM")) {
                    rankingCM.add(pair);
                }
            }
        }

        return new Pair<>(rankingCB, rankingCM);
    }

}
