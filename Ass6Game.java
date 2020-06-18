//ID: 204351670

import execution.AnimationRunner;
import execution.GameFlow;
import execution.GameLevel;
import execution.levels.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        levelInformationList.add(0,new LevelOne());
        levelInformationList.add(1,new LevelTwo());
        levelInformationList.add(2,new LevelThree());
        levelInformationList.add(3,new LevelFour());


        GameFlow gf = new GameFlow(levelInformationList);
        gf.runLevels(levelInformationList);
        exit(1);
    }
}
