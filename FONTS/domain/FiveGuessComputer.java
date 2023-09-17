package domain;

import utils.Pair;
import domain.exceptions.*;

import java.util.*;

/**
 * @author marcel
 */
public class FiveGuessComputer implements Computer {
    /**
     * max number of turns
     */
    final private int maxSteps;
    /**
     * The length of the game codes
     */
    final private int lengthCode;
    /**
     * Tells if repeated colors are available
     */
    final private boolean repeatedColors;
    /**
     * maximum number of colors
     */
    private final int maxColor;

    /**
     *
     * @return If the current code has repeated colors
     */
    private boolean hasRepeatedColors(List<Integer> code) {
        //Check if repeated colors
        HashSet<Integer> colors = new HashSet<>(code);
        return lengthCode > colors.size();
    }

    /**
     *
     * @return If the current code has empty spaces
     */
    private boolean hasEmptyColors(List<Integer> code) {
        for (Integer s : code) {
            if (Objects.equals(s, 8/*Empty*/)) return true;
        }
        return false;
    }

    /**
     *
     * @param code Code to check
     * @return true if the code has different codes
     */
    private boolean hasOtherColor(List<Integer> code) {
        for (Integer color : code) {
            if (color > maxColor || color < 0) return true;
        }
        return false;
    }

    /**
     * This method checks if the solution code is compatible with the game configuration
     * @param solution Game solution code
     * @throws NotRepeatedColorsException
     * @throws NotEmptyColorsException
     * @throws DifferentLengthCodeException
     * @throws IncorrectColorException
     */
    private void checkSolution(List<Integer> solution) throws NotRepeatedColorsException, NotEmptyColorsException,
            DifferentLengthCodeException, IncorrectColorException {
        if (maxColor==7 && hasEmptyColors(solution)) throw new NotEmptyColorsException();
        else if (!repeatedColors && hasRepeatedColors(solution)) throw new NotRepeatedColorsException();
        else if (solution.size() != lengthCode) throw new DifferentLengthCodeException();
        else if (hasOtherColor(solution)) throw new IncorrectColorException();
    }


    /**
     * FiveGuessComputer constructor
     * @param maxSteps Maximum number of turns of the current game
     * @param lengthCode Code length
     * @param emptyColors True if empty spaces are permitted
     * @param repeatedColors True if repeated colors are permitted
     */
    public FiveGuessComputer(int maxSteps, int lengthCode, boolean emptyColors, boolean repeatedColors) {
        this.maxSteps = maxSteps;
        this.lengthCode = lengthCode;
        this.repeatedColors = repeatedColors;
        if (emptyColors) maxColor = 8; //if empty colors are permitted then the maximum code is set to 8 (Empty)
        else maxColor = 7; //otherwise 7
    }

    /**
     * This solve method corresponds with the Five Guess Algorithm of Mastermind. This algorithm is a worst case algoritmh
     * that will create every possible solution and by pruning the set will achive the solution code on an average of 5 guesses.
     * @return A list of codes, each one representing one turn or guess that the algorithm has submitted with the solution
     * code as the final element depending on the game result.
     */
    @Override
    public List<List<Integer>> solve(List<Integer> solution) throws DifferentLengthCodeException, IncorrectColorException,
            NotRepeatedColorsException, NotEmptyColorsException {

        // Check if the solution code is consistent with the game options.
        checkSolution(solution);
        //First we get the first guess, hardcoded one (better for the algorithm) or random
        List<Integer> guess = getFirstGuess();
        List<List<Integer>> turns = new ArrayList<>();
        turns.add(guess);//Add the first guess to the list of turns
        Pair<Integer,Integer> feedback;
        //The combination set that we will prune
        List<List<Integer>> combinations = createSet(), possibleCodes = combinations;
        int remainingTurns = maxSteps-1;//remaining turns minus the first guess

        while (!guess.isEmpty() && remainingTurns > 0) {
            feedback = compareCodes(guess, solution);//obtain the feedback of the first guess
            if (isSolved(feedback)) return turns;//if black pegs == length -> win
            //update the possible codes by eliminating the codes that doesn't ret the same feedback when comparing with guess
            possibleCodes = eliminateCodes(guess, feedback, possibleCodes);
            //now we get the next guess choosing the guess that has the greater number of minimal eliminations depending
            //on the feedback
            guess = getNextGuess(possibleCodes,combinations);
            turns.add(guess);//add the current guess to the feedback
            --remainingTurns;
        }
        return turns;
    }

    /**
     * This method returns the guess that will eliminate more codes in the worst case. This is done by taking the worst cases
     * where every code saves the maximum number of codes varying the possible feedback, and take the less bad, that is to say
     * the smaller number of saves. And once it got the best codes among the worst cases, it chooses one that can win the game,
     * if possible.
     * @param possibleCodes Set of possible codes that can be the game solution
     * @param combinations The whole set of permutations
     * @return The best worst case guess
     */
    private List<Integer> getNextGuess(List<List<Integer>> possibleCodes, List<List<Integer>> combinations) {
        //In this map we will count the codes that for every combination will "save" --> not eliminate, depending on the possible feedback response
        Map<Pair<Integer,Integer>, Integer> count = new HashMap<>();
        //This map will contain the every combination with the maximum codes it will save
        Map<List<Integer>,Integer> scores = new HashMap<>();
        List<List<Integer>> possibleGuesses;
        for (List<Integer> combination : combinations) {//iterate through the whole set of combinations
            for (List<Integer> codeToEliminate : possibleCodes) {//comparing every element of the possible codes with every combination
                Pair<Integer, Integer> comparison = compareCodes(combination, codeToEliminate);
                //if the count is already initialized we add it one
                if (count.containsKey(comparison)) count.put(comparison,count.get(comparison) + 1);
                //else we initialize it
                else count.put(comparison, 1);
            }
            scores.put(combination, getMaxCount(count));
            count.clear();//clear the score count in order to start the count of the next combination
        }
        int minMaxCount = minCount(scores);//we get the minimal number of codes that will be saved, between the maximum counts
        List<List<Integer>> possibleNextGuesses = new ArrayList<>();
        //If the maximum number of codes that will eliminate a combination is equal to the minimal maximum number of codes that can be eliminated
        //we take this code as a possible next guess
        for (Map.Entry<List<Integer>, Integer> entry : scores.entrySet()) if (entry.getValue() == minMaxCount) possibleNextGuesses.add(entry.getKey());

        for (List<Integer> nextGuess : possibleNextGuesses) {//we prioritize the possible guesses solution
            if (possibleCodes.contains(nextGuess)) return nextGuess;
        }
        for (List<Integer> nextGuess : possibleNextGuesses) {//before the not possible, because the next guess can be the definitive!!
            if (combinations.contains(nextGuess)) return nextGuess;
        }
        return null;//Cant get here
    }

    /**
     *
     * @param count
     * @return The maximum value
     */
    private int getMaxCount(Map<Pair<Integer,Integer>,Integer> count) {
        int maxValue = Integer.MIN_VALUE;
        for (int value : count.values()) if (value > maxValue) maxValue = value;
        return maxValue;
    }

    /**
     *
     * @param scores
     * @return The minimum value
     */
    private int minCount(Map<List<Integer>,Integer> scores) {
        int minValue = Integer.MAX_VALUE;
        for (int value : scores.values()) if (value < minValue) minValue = value;
        return minValue;
    }

    /**
     * Iterative set creation with all the possibilities given the game configurations
     * @return The set (List<List<Integer>>) with all the permutations
     */
    public List<List<Integer>> createSet() {
        List<List<Integer>> possibleCodes = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        while (!stack.isEmpty()) {
            List<Integer> code = stack.pop();//permutation
            if (code.size() == lengthCode) possibleCodes.add(code);
            else {
                for (int i = 0; i <= maxColor; ++i) {
                    if (repeatedColors || !code.contains(i)) {//repeated colors allowed or it doesn't already contain the same color
                        List<Integer> newCode = new ArrayList<>(code);
                        newCode.add(i);
                        stack.push(newCode);
                    }
                }
            }
        }
        return possibleCodes;
    }


    /**
     * Method to generate the first guess of the algorithm
     * @return The first guess to try
     */
    public List<Integer> getFirstGuess() {
        List<Integer> firstGuess;
        if (!repeatedColors) firstGuess = randomGuess();
        else {//Hardcoded solutions, probed to be the best first guess depending on the lengthcode
            if (lengthCode == 2) firstGuess = new ArrayList<>(Arrays.asList(0,1));
            else if (lengthCode == 3) firstGuess = new ArrayList<>(Arrays.asList(0,0,1));
            else if (lengthCode == 4) firstGuess = new ArrayList<>(Arrays.asList(0,0,1,1));
            else if (lengthCode == 5) firstGuess = new ArrayList<>(Arrays.asList(0,0,1,1,2));
            else firstGuess = randomGuess();
        }
        return firstGuess;
    }

    /**
     * Method that generates a random code
     * @return Random code
     */
    private List<Integer> randomGuess() {
        List<Integer> randomGuess = new ArrayList<>();
        Random random = new Random();

        while (randomGuess.size() < lengthCode) {
            Integer color = random.nextInt(maxColor);
            if (repeatedColors || !randomGuess.contains(color)) randomGuess.add(color);
        }
        Collections.shuffle(randomGuess);
        return randomGuess;
    }

    /**
     * Method that given a feedback, tells if the feedback is color and position perfect
     * @param fd Feedback
     * @return True if the number of black pegs is equal to the length of the code, false otherwise
     */
    private boolean isSolved (Pair<Integer,Integer> fd) {
        return fd.first() == lengthCode;
    }

    /**
     * This method eliminates the impossible codes of the possibleCodes set
     * @param guess Current guess
     * @param fd Feedback received given the current guess
     * @param possibleCodes Set of outdated possible codes
     * @return The updated possibleCodes set
     */
    public List<List<Integer>> eliminateCodes (List<Integer> guess, Pair<Integer,Integer> fd,
                                               List<List<Integer>> possibleCodes) {
        ArrayList<List<Integer>> remainingCodes = new ArrayList<>();
        for (List<Integer> code : possibleCodes) {
            Pair<Integer,Integer> comparator = compareCodes(guess,code);
            if (fd.equals(comparator)) remainingCodes.add(code);//If the comparison of the code and the current guess equals the feedback
                                                                     //it means the code can be the solution
        }
        return remainingCodes;
    }

    /**
     * Calculates the feedback given two codes
     * @param code Code to compare it with
     * @param guess Guess to compare
     * @return A pair with the number of correct colors in the correct position and the  number of correct colors in the incorrect position
     */
    public Pair<Integer, Integer> compareCodes(List<Integer> code, List<Integer> guess) {
        int correctPosition = 0;
        int correctColor = 0;
        Map<Integer, Integer> codeMap = new HashMap<>();
        Map<Integer, Integer> guessMap = new HashMap<>();

        // Creating maps to count the occurrences of each color in the code and guess
        for (int i = 0; i < code.size(); i++) {
            int codeColor = code.get(i);
            int guessColor = guess.get(i);
            if (codeColor == guessColor) {
                correctPosition++;
            } else {
                codeMap.put(codeColor, codeMap.getOrDefault(codeColor, 0) + 1);
                guessMap.put(guessColor, guessMap.getOrDefault(guessColor, 0) + 1);
            }
        }

        // Counting the number of colors in incorrect positions
        for (Map.Entry<Integer, Integer> entry : guessMap.entrySet()) {
            int color = entry.getKey();
            int guessCount = entry.getValue();
            int codeCount = codeMap.getOrDefault(color, 0);
            correctColor += Math.min(guessCount, codeCount);
        }

        return new Pair<>(correctPosition, correctColor);
    }
}