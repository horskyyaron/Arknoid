package execution.backgrounds;

import biuoop.DrawSurface;
import execution.GameConstants;
import execution.levels.LevelInformation;
import execution.levels.LevelOne;
import gameelemnts.sprites.Sprite;
import geometry.Point;

import java.awt.*;

public class LevelOneBackground implements Sprite {

    private LevelInformation levelInformation = new LevelOne();


    @Override
    public void drawOn(DrawSurface d) {
        Point centerOfBlock = this.levelInformation.blocks().get(0).getCollisionRectangle().getCenterOfRec();
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
        d.setColor(Color.blue);
        d.drawCircle((int)centerOfBlock.getX(), (int)centerOfBlock.getY(), 50);
        d.drawCircle((int)centerOfBlock.getX(), (int)centerOfBlock.getY(), 70);
        d.drawCircle((int)centerOfBlock.getX(), (int)centerOfBlock.getY(), 90);
        d.drawLine((int)centerOfBlock.getX()-110,(int)centerOfBlock.getY(),(int)centerOfBlock.getX()+110, (int)centerOfBlock.getY());
        d.drawLine((int)centerOfBlock.getX(),(int)centerOfBlock.getY()-110,(int)centerOfBlock.getX(), (int)centerOfBlock.getY()+110);
        d.setColor(Color.BLACK);
        d.drawLine((int)centerOfBlock.getX()-30,(int)centerOfBlock.getY(),(int)centerOfBlock.getX()+30, (int)centerOfBlock.getY());
        d.drawLine((int)centerOfBlock.getX(),(int)centerOfBlock.getY()-30,(int)centerOfBlock.getX(), (int)centerOfBlock.getY()+30);

    }

    @Override
    public void timePassed() {

    }
}
