//ID: 204351670

package execution.animation;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import execution.levels.LevelInformation;
import execution.screens.KeyPressStoppableAnimation;
import execution.screens.LosingScreen;
import execution.screens.WinningScreen;

import java.util.List;

/**
 * The GameFlow class will run the entire game, changing level when needed, also
 * will end the game in case of winning or losing.
 */
public class GameFlow {

    private static final int FRAME_PER_SECOND = 60;

    //fields.
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> levelInformationList;
    private GUI gui;


    /**
     * Instantiates a new Game flow.
     *
     * @param lil the LevelInformation list containing all the levels to be played.
     */
    public GameFlow(List<LevelInformation> lil) {
        this.levelInformationList = lil;
        this.gui = new GUI("Arkanoid", Utils.getWidth(), Utils.getHeight());
        this.runner = new AnimationRunner(this.gui, FRAME_PER_SECOND);
        this.keyboardSensor = this.gui.getKeyboardSensor();
    }

    /**
     * Will exectue each level and navigate through levels, and win\lose situations.
     *
     * @param levels a list which holds the levels information.
     */
    public void runLevels(List<LevelInformation> levels) {

        //initializing the counters of the game.
        Counter score = new Counter();
        Counter blocksCounter = null;
        Counter ballsCounter = null;

        //go through the levels.
        for (LevelInformation levelInfo : levels) {

            blocksCounter = new Counter();
            ballsCounter = new Counter();

            //getting level information.
            GameLevel level = new GameLevel(levelInfo, score, ballsCounter, blocksCounter, this.keyboardSensor,
                    this.runner, this.gui);

            //initializing.
            level.initialize();

            //check for losing\winning situation. (if none, then continue the game)
            while (blocksCounter.getValue() != 0 && ballsCounter.getValue() != 0) {
                level.run();
            }

            //check for losing situation.
            if (ballsCounter.getValue() == 0) {
                //will run a losing screen.
                this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new LosingScreen(score)));
                break;
            }
        }
        //check for winning situation.
        if (ballsCounter.getValue() != 0) {
            //will run a winning screen.
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new WinningScreen(score)));
        }
    }
}
