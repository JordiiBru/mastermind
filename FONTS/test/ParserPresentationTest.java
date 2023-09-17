package test;

import org.junit.Assert;
import org.junit.Test;
import utils.Ball_color;
import utils.Pair;
import utils.ParserPresentation;

import java.util.*;

import static org.junit.Assert.*;

public class ParserPresentationTest {
    @Test
    public void testConvertToEntryList() {
        ArrayList<Pair<String, Integer>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("A", 1));
        pairList.add(new Pair<>("B", 2));
        pairList.add(new Pair<>("C", 3));

        List<Map.Entry<String, Integer>> entryList = ParserPresentation.convertToEntryList(pairList);

        assertEquals(3, entryList.size());
        assertEquals("A", entryList.get(0).getKey());
        assertEquals(Integer.valueOf(1), entryList.get(0).getValue());
        assertEquals("B", entryList.get(1).getKey());
        assertEquals(Integer.valueOf(2), entryList.get(1).getValue());
        assertEquals("C", entryList.get(2).getKey());
        assertEquals(Integer.valueOf(3), entryList.get(2).getValue());
    }

    @Test
    public void testConvertToEntryMap() {
        Map<String, Pair<String, Integer>> pairMap = new HashMap<>();
        pairMap.put("Key1", new Pair<>("Value1", 1));
        pairMap.put("Key2", new Pair<>("Value2", 2));
        pairMap.put("Key3", new Pair<>("Value3", 3));

        Map<String, Map.Entry<String, Integer>> entryMap = ParserPresentation.convertToEntryMap(pairMap);

        assertEquals(3, entryMap.size());
        assertEquals("Value1", entryMap.get("Key1").getKey());
        assertEquals(Integer.valueOf(1), entryMap.get("Key1").getValue());
        assertEquals("Value2", entryMap.get("Key2").getKey());
        assertEquals(Integer.valueOf(2), entryMap.get("Key2").getValue());
        assertEquals("Value3", entryMap.get("Key3").getKey());
        assertEquals(Integer.valueOf(3), entryMap.get("Key3").getValue());
    }

    @Test
    public void testConvertBoardToString() {
        /*Ball_color[][] board = {
                {Ball_color.Red, Ball_color.Blue},
                {Ball_color.Green, Ball_color.Yellow}
        };*/

        Ball_color[][] board = new Ball_color[5][2];
        board[0] = new Ball_color[]{Ball_color.Red, Ball_color.Blue};
        board[1] = new Ball_color[]{Ball_color.Green, Ball_color.Yellow};
        String[][] stringBoard = ParserPresentation.convertBoardToString(board,3);

        assertEquals(5, stringBoard.length);
        assertEquals(2, stringBoard[0].length);
        assertEquals("Red", stringBoard[0][0]);
        assertEquals("Blue", stringBoard[0][1]);
        assertEquals("Green", stringBoard[1][0]);
        assertEquals("Yellow", stringBoard[1][1]);
    }

    @Test
    public void testConvertToEntryListWithPairArray() {
        Pair<Integer, Integer>[] pairArray = new Pair[5];

        pairArray[0] = new Pair<>(1, 2);
        pairArray[1] = new Pair<>(3, 4);
        pairArray[2] = new Pair<>(5, 6);

        List<Map.Entry<Integer, Integer>> entryList = ParserPresentation.convertToEntryList(pairArray,4);

        assertEquals(5, entryList.size());
        assertEquals(Integer.valueOf(1), entryList.get(0).getKey());
        assertEquals(Integer.valueOf(2), entryList.get(0).getValue());
        assertEquals(Integer.valueOf(3), entryList.get(1).getKey());
        assertEquals(Integer.valueOf(4), entryList.get(1).getValue());
        assertEquals(Integer.valueOf(5), entryList.get(2).getKey());
        assertEquals(Integer.valueOf(6), entryList.get(2).getValue());
    }
}
