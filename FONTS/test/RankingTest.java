package test;

import domain.Ranking;
import domain.exceptions.InvalidPlayerNameException;
import utils.Pair;

import org.junit.*;
import java.util.ArrayList;

import static org.junit.Assert.*;




public class RankingTest {
    private ArrayList<Pair<String,Integer>> rankingCB;
    private ArrayList<Pair<String,Integer>> rankingCM;

    /**
     * Set Up the Ranking test
     */
    @Before
    public void setUp() {
        rankingCB = new ArrayList<>();
        rankingCM = new ArrayList<>();
    }

    /**
     * Checks that the constructor of the Ranking class creates two empty ArrayLists for the CB and CM rankings, and that these lists are not null.
     */
    @Test
    public void testConstructor() {
        Ranking ranking = new Ranking();
        ArrayList<Pair<String,Integer>> rankingCB = ranking.getRankingCB();
        ArrayList<Pair<String,Integer>> rankingCM = ranking.getRankingCM();
        assertNotNull(rankingCB);
        assertNotNull(rankingCM);
        assertEquals(0, rankingCB.size());
        assertEquals(0, rankingCM.size());
    }

    /**
     * Checks that the constructor of the Ranking class creates the CB and CM rankings using the provided ArrayLists of PairRankings, and that these lists are correctly returned by the corresponding getters.
     */
    @Test
    public void testConstructorWithArguments() {
        rankingCB.add(new Pair<>("Player 1", 100));
        rankingCB.add(new Pair<>("Player 2", 200));
        rankingCM.add(new Pair<>("Player 3", 300));
        rankingCM.add(new Pair<>("Player 4", 400));
        Ranking ranking = new Ranking(rankingCB, rankingCM);
        assertEquals(rankingCB, ranking.getRankingCB());
        assertEquals(rankingCM, ranking.getRankingCM());
    }

    /**
     * Checks whether setRankingCM sets the CodeMaker ranking correctly.
     */
    @Test
    public void testSetRankingCM() {
        Ranking ranking = new Ranking();
        ArrayList<Pair<String, Integer>> rankingCM = new ArrayList<>();
        rankingCM.add(new Pair<>("Raul", 1100));
        rankingCM.add(new Pair<>("Jordi", 2100));
        ranking.setRankingCM(rankingCM);
        assertEquals(rankingCM, ranking.getRankingCM());
    }

    /**
     * Checks whether setRankingCB sets the CodeMaker ranking correctly.
     */
    @Test
    public void testSetRankingCB() {
        Ranking ranking = new Ranking();
        ArrayList<Pair<String, Integer>> rankingCB = new ArrayList<>();
        rankingCB.add(new Pair<>("Marcel", 1500));
        rankingCB.add(new Pair<>("Javier", 1200));
        ranking.setRankingCB(rankingCB);
        assertEquals(rankingCB, ranking.getRankingCB());
    }

    /**
     * Checks that the updateRankingCB method of the Ranking class correctly adds a new entry to the CB ranking when it is empty, a new entry with a large score and a new entry with a large playerName.
     * @throws InvalidPlayerNameException if the playerName is invalid
     */
    @Test
    public void testBasicUpdateRankingCB() throws InvalidPlayerNameException {
        Ranking ranking = new Ranking();
        //The score is always positive and an integer (checked in the GameStats class)
        ranking.updateRankingCB("Player 1", Integer.MAX_VALUE); //Test adding large score
        rankingCB.add(new Pair<>("Player 1", Integer.MAX_VALUE));
        ranking.updateRankingCB("This is a test to check that a playerName can be very large", 300); //Test adding large playerName
        rankingCB.add(new Pair<>("This is a test to check that a playerName can be very large", 300));
        ranking.updateRankingCB("Player 2", 200); //Test adding basic entry
        rankingCB.add(new Pair<>("Player 2", 200));
        assertEquals(rankingCB.size(), ranking.getRankingCB().size()); //Check the ranking size
        for (int i = 0; i < rankingCB.size(); i++) { //Added in this way taking into account that rankingCM is an ArrayList that does not update positions by best score. Order checked in other test.
            assertEquals(rankingCB.get(i), ranking.getRankingCB().get(i));
        }
    }

    /**
     * Checks that the updateRankingCB method of the Ranking class correctly handles the case when the CB ranking is full, by not adding a new entry with a low score and keeping the highest-scoring entries in the ranking.
     * @throws InvalidPlayerNameException if the playerName is invalid
     */
    @Test
    public void testUpdateRankingCB() throws InvalidPlayerNameException {
        Ranking ranking = new Ranking();
        // Test adding entry to a full ranking
        for (int i = 1; i <= 20; i++) {
            ranking.updateRankingCB("Player " + i, i * 100); // Scores alternate between 1 and 2
        }
        assertEquals(20, ranking.getRankingCB().size()); // Ranking should be full now
        ranking.updateRankingCB("Player 21", 50); // Add an entry with a low score
        assertEquals(20, ranking.getRankingCB().size()); // Ranking should still have 20 entries
        assertFalse(ranking.isInRankingCB("Player 21")); // The low-scoring entry (50) should not be in the ranking
        assertTrue(ranking.isInRankingCB("Player 20")); // The highest-scoring entry (2000) should still be in the ranking
        assertEquals(2000, ranking.getScoreCB("Player 20")); //Check that the score of Player 21 is correct
    }

    /**
     * Checks that the updateRankingCM method of the Ranking class correctly adds a new entry to the CM ranking when it is empty, a new entry with a large score and a new entry with a large playerName.
     * @throws InvalidPlayerNameException if the playerName is invalid
     */
    @Test
    public void testBasicUpdateRankingCM() throws InvalidPlayerNameException {
        Ranking ranking = new Ranking();
        //The score is always positive and an integer (checked in the GameStats class)
        ranking.updateRankingCM("Player 1", Integer.MAX_VALUE); //Test adding large score
        rankingCM.add(new Pair<>("Player 1", Integer.MAX_VALUE));
        ranking.updateRankingCM("This is a test to check that a playerName can be very large", 300); //Test adding large playerName
        rankingCM.add(new Pair<>("This is a test to check that a playerName can be very large", 300));
        ranking.updateRankingCM("Player 2", 200); //Test adding basic entry
        rankingCM.add(new Pair<>("Player 2", 200));
        assertEquals(rankingCM.size(), ranking.getRankingCM().size()); //Check the ranking size
        for (int i = 0; i < rankingCM.size(); i++) { //Added in this way taking into account that rankingCM is an ArrayList that does not update positions by best score. Order checked in other test.
            assertEquals(rankingCM.get(i), ranking.getRankingCM().get(i));
        }
    }

    /**
     * Checks that the updateRankingCM method of the Ranking class correctly handles the case when the CM ranking is full, by not adding a new entry with a low score and keeping the highest-scoring entries in the ranking.
     * @throws InvalidPlayerNameException if the playerName is invalid
     */
    @Test
    public void testUpdateRankingCM() throws InvalidPlayerNameException {
        Ranking ranking = new Ranking();
        // Test adding entry to a full ranking
        for (int i = 1; i <= 20; i++) {
            ranking.updateRankingCM("Player " + i, i * 100);
        }
        assertEquals(20, ranking.getRankingCM().size()); // Ranking should be full now
        ranking.updateRankingCM("Player 21", 50); // Add an entry with a low score
        assertEquals(20, ranking.getRankingCM().size()); // Ranking should still have 20 entries
        assertFalse(ranking.isInRankingCM("Player 21")); // The low-scoring entry (50) should not be in the ranking
        assertTrue(ranking.isInRankingCM("Player 20")); // The highest-scoring entry (2000) should still be in the ranking
        assertEquals(2000, ranking.getScoreCM("Player 20")); //Check that the score of Player 21 is correct
    }

    /**
     * Checks that the updateRankingCB method throws an InvalidPlayerNameException when the playerName is invalid.
     * @throws InvalidPlayerNameException if the playerName is invalid
     */
    @Test(expected = InvalidPlayerNameException.class)
    public void testInvalidPlayerNameUpdateRankingCB() throws InvalidPlayerNameException {
        Ranking ranking = new Ranking();
        ranking.updateRankingCB("", 200); //Test adding empty playerName in CodeBreaker ranking
        rankingCB.add(new Pair<>("", 200));
    }

    /**
     * Checks that the updateRankingCM method throws an InvalidPlayerNameException when the playerName is invalid.
     * @throws InvalidPlayerNameException if the playerName is invalid
     */
    @Test(expected = InvalidPlayerNameException.class)
    public void testInvalidPlayerNameUpdateRankingCM() throws InvalidPlayerNameException {
        Ranking ranking = new Ranking();
        ranking.updateRankingCM("", 5200); //Test adding empty playerName in CodeMaker ranking
        rankingCM.add(new Pair<>("", 5200));
    }
}

