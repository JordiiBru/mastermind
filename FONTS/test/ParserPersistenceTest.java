package test;

import domain.Feedback;
import org.junit.Assert;
import org.junit.Test;
import utils.*;
import java.util.*;

public class ParserPersistenceTest {

    @Test
    public void testParseColor_ValidColor() {
        Ball_color result = ParserPersistence.parseColor("Red");
        Assert.assertEquals(Ball_color.Red, result);
    }

    @Test
    public void testConvertMapToList() {
        Map<String, Pair<String, Integer>> map = new HashMap<>();
        map.put("key1", new Pair<>("value1", 1));
        map.put("key2", new Pair<>("value2", 2));

        List<String> resultList = ParserPersistence.convertMapToList(map);

        Assert.assertEquals(2, resultList.size());
        Assert.assertTrue(resultList.contains("key1:value1:1"));
        Assert.assertTrue(resultList.contains("key2:value2:2"));
    }

    @Test
    public void testConvertListToMap() {
        List<String> list = new ArrayList<>();
        list.add("key1:value1:1");
        list.add("key2:value2:2");

        Map<String, Pair<String, Integer>> resultMap = ParserPersistence.convertListToMap(list);

        Assert.assertEquals(2, resultMap.size());

        Pair<String, Integer> pair1 = resultMap.get("key1");
        Assert.assertNotNull(pair1);
        Assert.assertEquals("value1", pair1.first());
        Assert.assertEquals(Integer.valueOf(1), pair1.second());

        Pair<String, Integer> pair2 = resultMap.get("key2");
        Assert.assertNotNull(pair2);
        Assert.assertEquals("value2", pair2.first());
        Assert.assertEquals(Integer.valueOf(2), pair2.second());
    }

    @Test
    public void testCombineRankings() {
        // Crear estructuras de prueba
        ArrayList<Pair<String, Integer>> rankingCB = new ArrayList<>();
        rankingCB.add(new Pair<>("CBPlayer1", 10));
        rankingCB.add(new Pair<>("CBPlayer2", 15));
        rankingCB.add(new Pair<>("CBPlayer3", 8));

        ArrayList<Pair<String, Integer>> rankingCM = new ArrayList<>();
        rankingCM.add(new Pair<>("CMPlayer1", 20));
        rankingCM.add(new Pair<>("CMPlayer2", 12));

        // Llamar al método combineRankings
        List<String> combinedList = ParserPersistence.combineRankings(rankingCB, rankingCM);

        // Verificar el resultado esperado
        List<String> expectedList = Arrays.asList("CM:CMPlayer1:20", "CM:CMPlayer2:12", "CB:CBPlayer1:10", "CB:CBPlayer2:15", "CB:CBPlayer3:8");
        Assert.assertEquals(expectedList, combinedList);
    }

    @Test
    public void testSplitRankings() {
        // Crear lista combinada de prueba
        List<String> combinedList = Arrays.asList("CM:CMPlayer1:20", "CM:CMPlayer2:12", "CB:CBPlayer1:10", "CB:CBPlayer2:15", "CB:CBPlayer3:8");

        // Llamar al método splitRankings
        Pair<ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> rankings = ParserPersistence.splitRankings(combinedList);
        ArrayList<Pair<String, Integer>> splitRankingCB = rankings.first();
        ArrayList<Pair<String, Integer>> splitRankingCM = rankings.second();

        // Verificar los resultados esperados para splitRankings
        ArrayList<Pair<String, Integer>> expectedCB = new ArrayList<>();
        expectedCB.add(new Pair<>("CBPlayer1", 10));
        expectedCB.add(new Pair<>("CBPlayer2", 15));
        expectedCB.add(new Pair<>("CBPlayer3", 8));

        ArrayList<Pair<String, Integer>> expectedCM = new ArrayList<>();
        expectedCM.add(new Pair<>("CMPlayer1", 20));
        expectedCM.add(new Pair<>("CMPlayer2", 12));

        Assert.assertEquals(expectedCB, splitRankingCB);
        Assert.assertEquals(expectedCM, splitRankingCM);
    }

    @Test
    public void testGame() {
        List<String> list = new ArrayList<>();
        list.add("jordi");

        // difficulty info
        list.add("false");
        list.add("false");
        list.add("false");
        list.add("4");
        list.add("false");
        list.add("4");

        // game stats info
        list.add("10");
        list.add("2");
        list.add("0");

        // solution info
        Ball_color[] secret_code = {Ball_color.Red,Ball_color.Green, Ball_color.Blue,Ball_color.Yellow};
        int i = 0;
        StringBuilder solution = new StringBuilder("{");
        while (i < secret_code.length) {
            solution.append(secret_code[i].toString()).append(",");
            i++;
        }
        solution.append("}");
        list.add(solution.toString());

        // board info
        Ball_color[][] board = new Ball_color[4][4];
        board[0] = new Ball_color[]{Ball_color.Red, Ball_color.Green, Ball_color.Blue, Ball_color.Purple};
        int numTurn = 2;

        for (int it = 0; it < board.length; it++) {
            Ball_color[] row = board[it];
            StringBuilder boardTurn = new StringBuilder("{");
            for (int j = 0; j < row.length; j++) {
                Ball_color color = row[j];
                String colorString;
                if (it < numTurn-1) {
                    colorString = color.toString();

                } else {
                    colorString = "null";
                }
                if(j+1 == row.length) boardTurn.append(colorString).append("}");
                else boardTurn.append(colorString).append(",");
            }
            list.add(boardTurn.toString());
        }

        // feedback board info
        Pair<Integer, Integer>[] feedback = new Pair[4];
        feedback[0] = new Pair<>(3, 0);
        for (int it = 0; it < feedback.length; it++) {
            Pair<Integer, Integer> pair = feedback[it];
            String feedbackString;
            if (it < numTurn-1) {
                feedbackString = "{" + pair.first() + "," + pair.second() + "}";
            } else {
                feedbackString = "{null,null}";
            }
            list.add(feedbackString);
        }

        List<String> data = list;
        int index = 0;

        // player info
        String player_name = data.get(index); ++index;

        // difficulty info
        String diffName = data.get(index); ++index;
        boolean diffAlgorithm = Boolean.parseBoolean(data.get(index)); ++index;
        boolean diffRepeated = Boolean.parseBoolean(data.get(index)); ++index;
        int diffNumPositions = Integer.parseInt(data.get(index)); ++index;
        boolean diffEmpty = Boolean.parseBoolean(data.get(index)); ++index;
        int diffMaxTurns = Integer.parseInt(data.get(index)); ++index;

        // game stats info
        int elapsedTime = Integer.parseInt(data.get(index)); ++index;
        int turn = Integer.parseInt(data.get(index)); ++index;
        int hintsUsed = Integer.parseInt(data.get(index)); ++index;

        // solution info
        String solution2 = data.get(index); ++index;
        String cropped_solution = solution.substring(1, solution.length() - 1); // we remove the { }
        String[] listSolutions = cropped_solution.split(",");
        Ball_color[] secret_code2 = new Ball_color[listSolutions.length];
        for (int it = 0; it < listSolutions.length; ++it) {
            secret_code2[it] = ParserPersistence.parseColor(listSolutions[it]);
        }

        //board info
        Ball_color[][] board2 = new Ball_color[diffMaxTurns][diffNumPositions];
        for (int it = 0; it < turn-1; ++it) {
            String rowString = data.get(index);
            String[] rowColors = rowString.substring(1, rowString.length() - 1).split(",");
            for (int j = 0; j < diffNumPositions; ++j) {
                board2[it][j] = ParserPersistence.parseColor(rowColors[j]);
            }
            ++index;
        }
        index = index + (diffMaxTurns - turn) +1;

        //feedback board info
        Pair<Integer, Integer>[] feedback2 = new Pair[diffMaxTurns];
        for (int it = 0; it < turn-1; ++it) {
            String feedbackString = data.get(index);
            String[] feedbackValues = feedbackString.substring(1, feedbackString.length() - 1).split(",");
            int first = Integer.parseInt(feedbackValues[0]);
            int second = Integer.parseInt(feedbackValues[1]);
            feedback2[it] = new Pair<>(first, second);
            ++index;
        }

        Assert.assertEquals(feedback,feedback2);
    }
}
