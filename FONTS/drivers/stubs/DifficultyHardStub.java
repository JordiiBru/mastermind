package drivers.stubs;
public class DifficultyHardStub {

    public String DifficultyHard() {
        return "An empty instance of DifficultyHard has been created successfully. " + setValuesHard();
    }

    public String setValuesHard() {
        return "Hard default values set.";
    }

    public boolean getAlgorithm() {
        return true;
    }

    public boolean getRepeatedColors() {
        return false;
    }

    public int getNumberPositions() {
        return 7;
    }

    public boolean getEmptyColors() {
        return false;
    }

    public int getMaxTurns() {
        return 8;
    }

    public double getMultiplier() {
        return 8.0;
    }

}
