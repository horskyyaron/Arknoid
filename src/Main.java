//ID: 204351670

import execution.animation.GameFlow;
import execution.levels.LevelOne;
import execution.levels.LevelTwo;
import execution.levels.LevelThree;
import execution.levels.LevelFour;

import java.util.ArrayList;
import java.util.List;
import execution.levels.LevelInformation;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        System.out.println("enter levels:");

        List<LevelInformation> levels = generateLevelListFromArguments(args);
        GameFlow gameFlow = new GameFlow(levels);
        gameFlow.runLevels(levels);
        exit(1);
    }

    private static void generateDefaultLevelList(List<LevelInformation> levels) {
        levels.add(new LevelOne());
        levels.add(new LevelTwo());
        levels.add(new LevelThree());
        levels.add(new LevelFour());
    }
    private static ArrayList<LevelInformation> generateLevelListFromArguments(String[] args) {
        ArrayList<LevelInformation> levelsInfo = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "1":
                    levelsInfo.add(new LevelOne());
                    break;
                case "2":
                    levelsInfo.add(new LevelTwo());
                    break;
                case "3":
                    levelsInfo.add(new LevelThree());
                    break;
                case "4":
                    levelsInfo.add(new LevelFour());
                    break;
                default:
                    break;
            }
        }

        if (levelsInfo.size() == 0) {
            generateDefaultLevelList(levelsInfo);
        }

        return levelsInfo;
    }
}
