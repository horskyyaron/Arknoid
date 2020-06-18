package execution;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import execution.levels.LevelInformation;

import java.util.List;

public class GameFlow {

    private static final int FRAME_PER_SECOND = 60;

    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> levelInformationList;
    private GUI gui;


    public GameFlow(List<LevelInformation> lil) {
        this.levelInformationList = lil;
        this.gui = new GUI("Arkanoid", GameConstants.getWidth(), GameConstants.getHeight());
        this.runner = new AnimationRunner(this.gui, FRAME_PER_SECOND);
        this.keyboardSensor = this.gui.getKeyboardSensor();
    }

    public void runLevels(List<LevelInformation> levels) {

        Counter score = new Counter();
        Counter blocksCounter = null;
        Counter ballsCounter = null;

        for (LevelInformation levelInfo : levels) {

            blocksCounter = new Counter();
            ballsCounter = new Counter();

            GameLevel level = new GameLevel(levelInfo, score, ballsCounter, blocksCounter, this.keyboardSensor, this.runner, this.gui);

            level.initialize();

            while (blocksCounter.getValue() != 0 && ballsCounter.getValue() != 0) {
                level.run();
            }

            if (ballsCounter.getValue() == 0) {
                this.runner.run(new LosingScreen(this.keyboardSensor, score));
                break;
            }
        }

        if(ballsCounter.getValue() != 0) {
            this.runner.run(new WinningScreen(this.keyboardSensor, score));
        }
    }
}
