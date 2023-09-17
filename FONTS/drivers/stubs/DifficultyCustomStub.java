package drivers.stubs;

public class DifficultyCustomStub {

    public String DifficultyCustom() {
        return "An empty instance of DifficultyCustom has been created successfully. " + setValuesCustom();
    }

    public String setValuesCustom() {
        return "Custom default values set.";
    }

    public String setAlgCus(boolean alg) {
        return "algorithm variable set to " + alg + ".";
    }

    public String setRepColCus(boolean rep_col) {
        return "repeated_colors variable set to " + rep_col + ".";
    }

    public String setNumPosCus(int num_pos){
        return "number_positions variable set to " + num_pos + ".";
    }

    public String setEmptyColCus(boolean emp_col) {
        return "empty_colors variable set to " + emp_col + ".";
    }

    public String setMaxTurCus(int max_tur) {
        return "max_turns variable set to " + max_tur + ".";
    }

    public String setMulCus() {
        return "multiplier variable updated correctly.";
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
