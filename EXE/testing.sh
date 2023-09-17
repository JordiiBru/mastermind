#!/bin/bash

echo "Seleccione el test que desea ejecutar:"
echo "1 -> BoardTest"
echo "2 -> CodeMakerComputerTest"
echo "3 -> DifficultyCustomTest"
echo "4 -> DifficultyHardTest"
echo "5 -> DifficultyNormalTest"
echo "6 -> FeedbackTest"
echo "7 -> FiveGuessComputerTest"
echo "8 -> GameStatsTest"
echo "9 -> PlayerTest"
echo "10 -> RankingTest"
echo "11 -> RecordsTest"
echo "12 -> GeneticComputerTest"
echo "all -> todos los tests" 

# lee la opcion y ejecuta el test deseado
read option

if [ "$option" == "1" ]; then
	java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.BoardTest
elif [ "$option" == "2" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.CodeMakerComputerTest
elif [ "$option" == "3" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.DifficultyCustomTest
elif [ "$option" == "4" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.DifficultyHardTest
elif [ "$option" == "5" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.DifficultyNormalTest
elif [ "$option" == "6" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.FeedbackTest
elif [ "$option" == "7" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.FiveGuessComputerTest
elif [ "$option" == "8" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.GameStatsTest
elif [ "$option" == "9" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.PlayerTest
elif [ "$option" == "10" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.RankingTest
elif [ "$option" == "11" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.RecordsTest
elif [ "$option" == "12" ]; then
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.GeneticComputerTest
elif [ "$option" == "all" ]; then
    echo "Testing: BoardTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.BoardTest
    echo "Testing: CodeMakerComputerTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.CodeMakerComputerTest
    echo "Testing: DifficultyCustomTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.DifficultyCustomTest
    echo "Testing: DifficultyHardTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.DifficultyHardTest
    echo "Testing: DifficultyNormalTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.DifficultyNormalTest
    echo "Testing: FeedbackTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.FeedbackTest
    echo "Testing: FiveGuessComputerTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.FiveGuessComputerTest
    echo "Testing: GameStatsTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.GameStatsTest
    echo "Testing: PlayerTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.PlayerTest
    echo "Testing: RankingTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.RankingTest
    echo "Testing: RecordsTest:"
    java -cp .:../FONTS/lib/* org.junit.runner.JUnitCore test.RecordsTest
else
    echo "Opcion invalida"
fi
