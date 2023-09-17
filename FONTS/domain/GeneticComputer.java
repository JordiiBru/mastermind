package domain;

import utils.*;
import domain.exceptions.*;

import java.util.*;

public class GeneticComputer implements Computer {

    // parameters of a code
    final private int maxSteps;
    final private int lengthCode;
    final private boolean repeatedColors;
    final private int maxColor;

    /**
     * Maximum number of possible solutions in a population.
     */
    static final private int population_size = 1000;

    /**
     * List to store the game history guesses.
     */
    private List<List<Integer> > game_history;

    /**
     * List with the current population.
     */
    private List<List<Integer>> population;

    /**
     * List with the fitness of every code from the population.
     */
    private List<Double> fitness;

    /**
     * List to store the game history feedback. Index represents the related turn.
     */
    private List<Pair<Integer,Integer> > prev_feedback;


    /**
     * Constructor for the GeneticComputer class.
     * @param maxSteps Maximum number of steps for the genetic algorithm (turns).
     * @param lengthCode Length of the code to be guessed.
     * @param emptyColors Flag indicating whether empty colors are permitted in the code.
     * @param repeatedColors Flag indicating whether repeated colors are permitted in the code.
     */
    public GeneticComputer(int maxSteps, int lengthCode, boolean emptyColors, boolean repeatedColors) {
        game_history = new ArrayList<>();
        population = new ArrayList<>();
        fitness = new ArrayList<>();
        prev_feedback = new ArrayList<>();

        this.maxSteps = maxSteps;
        this.lengthCode = lengthCode;
        this.repeatedColors = repeatedColors;
        if (emptyColors) maxColor = 8; //if empty colors are permitted then the maximum code is set to 8 (Empty)
        else maxColor = 7; //otherwise 7
    }

    //  vvvvv same as five guess computer vvvvv

    /**
     * @param code Code to check its options.
     * @return If the current code has repeated colors
     */
    private boolean hasRepeatedColors(List<Integer> code) {
        //Check if repeated colors
        HashSet<Integer> colors = new HashSet<>(code);
        return lengthCode > colors.size();
    }

    /**
     * @param code Code to check its options.
     * @return If the current code has empty spaces
     */
    private boolean hasEmptyColors(List<Integer> code) {
        for (Integer s : code) {
            if (Objects.equals(s, 8/*Empty*/)) return true;
        }
        return false;
    }

    /**
     * @param code Code to check its options.
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
     * @throws NotRepeatedColorsException When the option (repeatedColors) does not match the solution given.
     * @throws NotEmptyColorsException When the option (emptyColor) does not match the solution given.
     * @throws DifferentLengthCodeException When the option (lengthCode) does not match the solution given.
     * @throws IncorrectColorException When the option (invalidColor) does not match the solution given.
     */
    private void checkSolution(List<Integer> solution) throws NotRepeatedColorsException, NotEmptyColorsException,
            DifferentLengthCodeException, IncorrectColorException {
        if (maxColor==7 && hasEmptyColors(solution)) throw new NotEmptyColorsException();
        else if (!repeatedColors && hasRepeatedColors(solution)) throw new NotRepeatedColorsException();
        else if (solution.size() != lengthCode) throw new DifferentLengthCodeException();
        else if (hasOtherColor(solution)) throw new IncorrectColorException();
    }

    /**
     * Calculates the feedback given two codes
     * @param code Code to compare it with.
     * @param guess Guess to compare.
     * @return A pair with the number of correct colors in the correct position and the  number of correct colors in the incorrect position.
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

    /**
     * Method that given a feedback, tells if the feedback is color and position perfect.
     * @param fd Feedback.
     * @return True if the number of black pegs is equal to the length of the code, false otherwise.
     */
    private boolean isSolved (Pair<Integer,Integer> fd) {
        return fd.first() == lengthCode;
    }

    //  ^^^^ same as five guess computer ^^^^

    /**
     * Solves the Mastermind game using a genetic algorithm.
     * @param solution The secret code to be guessed.
     * @return The game history as a list of guesses.
     * @throws Exception If the solution code is not consistent with the game options.
     */
    public List<List<Integer>> solve(List<Integer> solution) throws Exception {
        // Check if the solution code is consistent with the game options.
        checkSolution(solution);

        // create the starting population and set the first guess
        createStartingPopulation();
        List<Integer> guess = population.get(0);
        int remainingTurns = maxSteps;

        while (remainingTurns > 1) {
            // guess procedure
            game_history.add(guess);
            Pair<Integer, Integer> feedback = compareCodes(guess, solution);
            prev_feedback.add(feedback);
            if (isSolved(feedback)) return game_history;

            // creating the next population procedure with its elitism
            List<List<Integer>> newPopulation = new ArrayList<>();
            newPopulation.add(population.get(0));
            newPopulation.add(population.get(1));

            //evolve the current new population
            evolvePopulation(newPopulation);

            //calculate fitness and sort the population
            calculateAllFitness(this.population);
            this.population = sortPopulationByFitness(this.population);

            // set the best possible next guess for next turn
            guess = nextGuess();
            --remainingTurns;
        }
        // last turn in order to avoid repeating code
        game_history.add(guess);
        Pair<Integer, Integer> feedback = compareCodes(guess, solution);
        prev_feedback.add(feedback);

        return game_history;
    }

    /**
     * Creates the starting population for the genetic algorithm.
     */
    public void createStartingPopulation() {
        // if the length of the code is 2,3,4 or 5, we have hardcoded the "best" solution for feedback purposes.
        // Because at the starting population we will take the first one, which will be the best case. And later on, we will generate the following ones.
        if (lengthCode == 2) {population.add(Arrays.asList(0,1));}
        else if (lengthCode == 3) {population.add(Arrays.asList(0,0,1));}
        else if (lengthCode == 4) {population.add(Arrays.asList(0,0,1,1));}
        else if (lengthCode == 5) {population.add(Arrays.asList(0,0,1,1,2));}

        Random rand = new Random();
        int i = 1;

        while(i < population_size) {
            List<Integer> solution = new ArrayList<>();
            for (int it = 0; it < lengthCode; ++it) {
                int new_color = rand.nextInt(maxColor+1);
                while(!repeatedColors && solution.contains(new_color)) new_color = rand.nextInt(maxColor+1);
                solution.add(new_color);
            }
            population.add(solution);
            ++i;
        }
    }

    /**
     * Evolves the population by applying crossover and mutation operators.
     * @param newPopulation list of codes to be filled with other possible codes. It starts with two codes which are the parents of the population.
     * It ends up setting the global population to the new population generated.
     */
    public void evolvePopulation(List<List<Integer>> newPopulation) {

        // crossover procedure to generate possible codes
        for(int i = 0; i < newPopulation.size()-1; i=i+2) {
            permutation_full_crossover(newPopulation, newPopulation.get(i), newPopulation.get(i+1));
        }

        // mutate the current new population in different ways
        int size = 0;
        while(size < newPopulation.size() && newPopulation.size() < population_size) {
            for(int i = 0; i < newPopulation.size(); ++i) {
                if(Math.random() <= 0.5) {
                    mutate(newPopulation, newPopulation.get(i));
                } else {
                    inversion(newPopulation, newPopulation.get(i));
                }
            } ++size;
        }


        // generate random population until we fill population size or if there are not more possibilities
        //fillPopulation(newPopulation);

        this.population = newPopulation;
    }

    /**
     * Performs a permutation full crossover between two parent codes and adds the resulting offspring to the new population.
     * @param new_population List of codes to be filled with other possible codes.
     * @param code1 The first parent code for this specific crossover.
     * @param code2 The second parent code for this specific crossover.
     */
    public void permutation_full_crossover(List<List<Integer>> new_population, List<Integer> code1, List<Integer> code2) {
        List<Integer> possible_code1 = permutation_crossover(code1,code2);
        List<Integer> possible_code2 = permutation_crossover(code2,code1);
        if(!new_population.contains(possible_code1)) new_population.add(possible_code1);
        if(!new_population.contains(possible_code2)) new_population.add(possible_code2);
    }

    /**
     * Performs permutation crossover between two parent codes to generate a new code.
     * @param code1 The first parent code for this specific crossover.
     * @param code2 The second parent code for this specific crossover.
     * @return The new code generated by permutation crossover.
     */
    public List<Integer> permutation_crossover(List<Integer> code1, List<Integer> code2) {
        List<Integer> new_code = new ArrayList<>();
        int crossover_point = (int) (Math.random() * (lengthCode - 1));
        Set<Integer> used_numbers = new HashSet<>();
        for (int i = 0; i < crossover_point; i++) {
            Integer number = code1.get(i);
            new_code.add(number);
            used_numbers.add(number);
        }
        for (int i = crossover_point; i < lengthCode; i++) {
            Integer number = code2.get(i);
            if (!repeatedColors && used_numbers.contains(number)) number = giveRandomColor(new_code,i);
            used_numbers.add(number);
            new_code.add(number);
        }
        return new_code;
    }

    /**
     * Mutates a code by randomly changing some of its elements.
     * @param newPopulation The population to which the mutated code will be added.
     * @param code1 The code to be mutated.
     */
    public void mutate(List<List<Integer>> newPopulation,List<Integer> code1) {
        List<Integer> new_code = new ArrayList<>();
        double mutationRate = 1.0/this.lengthCode;
        for(int i = 0; i < this.lengthCode; ++i) {
            if(Math.random() < mutationRate)  {
                new_code.add(giveRandomColor(code1,i));
            } else {
                new_code.add(code1.get(i));
            }
        }
        if(new_code.equals(code1) || newPopulation.contains(new_code)) return;
        newPopulation.add(new_code);
    }

    /**
     * Performs an inversion on a code by swapping two random positions.
     * @param newPopulation The population to which the mutated code will be added.
     * @param code1 The code to be mutated.
     */
    public void inversion(List<List<Integer>> newPopulation,List<Integer> code1) {
        List<Integer> new_code = new ArrayList<>(lengthCode);
        new_code.addAll(code1);
        int position1 = (int) (Math.random() * (lengthCode));
        int position2 = (int) (Math.random() * (lengthCode));
        while(position1 == position2) position2 = (int) (Math.random() * (lengthCode));
        Integer tmp_color = code1.get(position1);
        new_code.set(position1,code1.get(position2));
        new_code.set(position2,tmp_color);
        if(new_code.equals(code1) || newPopulation.contains(new_code)) return;
        newPopulation.add(new_code);
    }

    /**
     * Calculates the fitness of all the solutions in the current population and stores them in the fitness list.
     * The fitness of a solution is calculated using the calculateSingleFitness method.
     * Clears the current fitness list before calculating the new fitness values.
     */
    public void calculateAllFitness(List<List<Integer>> codes) {
        fitness.clear();
        for (List<Integer> integers : codes) {
            double fitness = calculateSingleFitness(integers);
            this.fitness.add(fitness);
        }
    }

    /**
     * Calculates the fitness value of a single code based on the previous feedback received during the game.
     * It is calculated by comparing the code with the game history and measuring the difference between the expected feedback and the actual feedback.
     * @param code The code to calculate the fitness for.
     * @return The fitness value of the code.
     */
    public double calculateSingleFitness(List<Integer> code) {
        double fitness = 0.0;

        for (int i = 0; i < prev_feedback.size(); i++) {
            Pair<Integer, Integer> feedback = prev_feedback.get(i); //check the feedback on that specific turn
            int red = feedback.first();
            int white = feedback.second();

            Pair<Integer,Integer> actual_feedback = compareCodes(code, game_history.get(i)); // compare both codes to have an actual feedback
            int actual_red = actual_feedback.first();
            int actual_white = actual_feedback.second();

            int diff_red = Math.abs(red - actual_red);
            int diff_white = Math.abs(white - actual_white);

            fitness -= (2 * diff_red + diff_white); // red pins are more important
        }
        return fitness;
    }

    /**
     * Sorting the population depending on the fitness. First element is the best solution in the popoulation
     */
    public List<List<Integer>> sortPopulationByFitness(List<List<Integer>> codes) {
        for (int i = 0; i < fitness.size() - 1; i++) {
            for (int j = 0; j < fitness.size() - i - 1; j++) {
                if (fitness.get(j) < fitness.get(j + 1)) {
                    // swap fitness positions
                    Double tempFit = fitness.get(j);
                    fitness.set(j, fitness.get(j + 1));
                    fitness.set(j + 1, tempFit);
                    // swap same positions in population
                    List<Integer> tempPop = codes.get(j);
                    codes.set(j, codes.get(j + 1));
                    codes.set(j + 1, tempPop);
                }
            }
        }
        return codes;
    }

    /**
     * Method to take next best possible guess.
     * If there is a case where the next guess is already used, it will take the consecutive one.
     * @return the next best possible guess.
     */
    public List<Integer> nextGuess() {
        int index = 0; //
        while(game_history.contains(population.get(index))) ++index;
        return population.get(index);
    }

    /**
     * Method that returns a random possible color for a specific position of a code.
     * @param code Code to look for a new color.
     * @param pos Position of the color to be replaced.
     * @return A new_color that fits the parameters or the same initial color in order to prevent infinite loops.
     */
    public Integer giveRandomColor(List<Integer> code, int pos) {
        Random rand = new Random();
        int new_color = rand.nextInt(maxColor+1);
        int maxAttempts = 20;
        int attempts = 0;
        while (!repeatedColors && code.contains(new_color) && attempts < maxAttempts) {
            new_color = rand.nextInt(maxColor+1);
            ++attempts;
        }
        if(!repeatedColors && code.contains(new_color)) return code.get(pos); //if the loop took too long, have the same color
        return new_color;
    }

    /**
     *  Fills the population with randomly generated codes until the desired population size is reached.
     *  The method uses a maximum number of attempts to avoid getting stuck in an infinite loop.
     *  If the generated code already exists in the population, it will make additional attempts to generate a unique code.
     * @param newPopulation The population to be filled.
     */
    public void fillPopulation(List<List<Integer>> newPopulation) {
        int maxAttempts = population_size * 10;
        int attempts = 0;
        while (newPopulation.size() < population_size) {
            List<Integer> new_code = new ArrayList<>();
            for (int i = 0; i < lengthCode; i++) {
                int new_color = giveRandomColor(new_code,i);
                new_code.add(new_color);
            }
            if (!newPopulation.contains(new_code)) {
                newPopulation.add(new_code);
            } else {
                ++attempts;
            }
            if(attempts >= maxAttempts) break;
        }
    }

    // SETTERS AND GETTER FOR TESTING PURPOSES

    public void setFitness(List<Double> fitness) {
        this.fitness = fitness;
    }

    public void setPopulation(List<List<Integer>> new_populationlist) {
        this.population = new_populationlist;
    }

    public List<Double> getFitness() {
        return this.fitness;
    }

    public List<List<Integer>> getPopulation() {
        return this.population;
    }

    public void setGameHistory(List<List<Integer>> gh){
        this.game_history = gh;
    }

    public List<List<Integer>> getGameHistory() {
        return this.game_history;
    }

    public void setPrevFeedback(List<Pair<Integer, Integer>> pg){
        this.prev_feedback = pg;
    }

    public List<Pair<Integer, Integer>> getPrevFeedback() {
        return this.prev_feedback;
    }
}
