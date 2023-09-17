package test;
import domain.Records;
import utils.Pair;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author jordi
 * We have to consider that we will never have an empty GameStats object because the gamestats rturning object will be always of a correct and finished game
 */
public class RecordsTest {

    private Records records;

    @Before
    public void setUp() {
        this.records = new Records();
    }

    @Test
    public void testInitRecords() {
        assertNotNull(records.getRecordsMap());
        assertEquals(6, records.getRecordsMap().size());

        Pair<String, Integer> emptyPair = new Pair<>();
        for (Pair<String, Integer> record : records.getRecordsMap().values()) {
            assertEquals(emptyPair.second(), record.second());
        }
    }

    @Test
    public void testUpdateRecords() {

        // first game
        records.updateRecords("player1", 100,30,8);

        // second game
        records.updateRecords("player2", 200,50,5);

        assertEquals(30, (int) records.getFastestRecord().second());
        assertEquals(50, (int) records.getSlowestRecord().second());
        assertEquals(200, (int) records.getHighScoreRecord().second());
        assertEquals(100, (int) records.getLowScoreRecord().second());
        assertEquals(8, (int) records.getMoreTurnsRecord().second());
        assertEquals(5, (int) records.getFewerTurnsRecord().second());
    }

}
