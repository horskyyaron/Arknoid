import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {

    private List<Sprite> spriteElements;

    //constructors.
    public SpriteCollection(List<Sprite> spriteElements) {
        this.spriteElements = spriteElements;
    }

    public SpriteCollection() {
        this.spriteElements = new ArrayList<>();
    }

    public void addSprite(Sprite s) {
        this.spriteElements.add(s);
    }

    public void addSpritesList(List<Sprite> spriteList) {
        for (Sprite sprite: spriteList) {
            addSprite(sprite);
        }
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() throws Exception {
        for (Sprite s: this.spriteElements) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) throws Exception {
        for (Sprite s: this.spriteElements) {
            s.drawOn(d);
        }
    }

    public Sprite getSprite(int index) {
        return this.spriteElements.get(index);
    }
}