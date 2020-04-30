import biuoop.DrawSurface;

import java.util.List;

public class SpriteCollection {

    private List<Sprite> spriteElements;

    public void addSprite(Sprite s) {
        this.spriteElements.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() throws Exception {
        for (Sprite s: this.spriteElements) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: this.spriteElements) {
            s.drawOn(d);
        }
    }
}