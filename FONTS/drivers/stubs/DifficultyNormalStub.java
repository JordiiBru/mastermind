package drivers.stubs;
public class DifficultyNormalStub {

    public String DifficultyNormal() {
        return "An empty instance of DifficultyNormal has been created successfully. " + setValuesNormal();
    }

    public String setValuesNormal() {
        return "Normal default values set.";
    }

    public boolean getAlgorithm() {
        return true;
    }

    public boolean getRepeatedColors() {
        return false;
    }

    public int getNumberPositions() {
        return 4;
    }

    public boolean getEmptyColors() {
        return false;
    }

    public int getMaxTurns() {
        return 8;
    }

    public double getMultiplier() {
        return 1.0;
    }

}
