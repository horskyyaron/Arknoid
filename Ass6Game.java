//ID: 204351670

import execution.GameFlow;
import execution.levels.LevelOne;
import execution.levels.LevelTwo;
import execution.levels.LevelThree;
import execution.levels.LevelFour;

import java.util.ArrayList;
import java.util.List;

import execution.levels.LevelInformation;

import static java.lang.System.exit;

/**
 * Ass3Game class will initialize and run Arkanoid game.
 */
public class Ass6Game {

    /**
     * main function.
     * will create a new Arkanoid game, initialize it and run it.
     *
     * @param args input from user. (will not impact the program)
     */
    public static void main(String[] args) {

        List<LevelInformation> levelInformationList = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "1":
                    levelInformationList.add(new LevelOne());
                    break;
                case "2":
                    levelInformationList.add(new LevelTwo());
                    break;
                case "3":
                    levelInformationList.add(new LevelThree());
                    break;
                case "4":
                    levelInformationList.add(new LevelFour());
                    break;
                default:
                    break;
            }
        }

        if (levelInformationList.size() == 0) {
            System.out.println("Hi, if you want to play the game you must enter \n"
                    + "some levels to play with. there are 4 level total, so legal argument will be the numbers"
                    + " 1 to 4 (1,2,3,4). \nAlso, you can enter a level more then once! e.g '1 1' will run level "
                    + "one twice!\n" + "What are you waiting for? time to play the game (like Triple H theme)");
        } else {

            GameFlow gameFlow = new GameFlow(levelInformationList);
            gameFlow.runLevels(levelInformationList);
            exit(1);
        }
    }
}
