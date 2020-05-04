//ID: 204351670

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class will create an object which will hold Sprite objects,
 * will supports methods of adding and drawing Sprites.
 * Will have method that 'tells' a sprite object that time has passed.
 */
public class SpriteCollection {
    //fields.
    private List<Sprite> spriteElements;

    //constructors.
    /**
     * constructor of the 'SpriteCollection' object.
     *
     * @param spriteElements a list of Sprites.
     */
    public SpriteCollection(List<Sprite> spriteElements) {
        this.spriteElements = spriteElements;
    }
    /**
     * constructor of the 'SpriteCollection' object.
     */
    public SpriteCollection() {
        this.spriteElements = new ArrayList<>();
    }

    /**
     * the function will add an input Sprite into the SpriteCollection.
     *
     * @param s a Sprite object.
     */
    public void addSprite(Sprite s) {
        this.spriteElements.add(s);
    }

    /**
     * the function will add an input list of Sprites into the SpriteCollection.
     *
     * @param spriteList a Sprite object.
     */
    public void addSpritesList(List<Sprite> spriteList) {
        for (Sprite sprite: spriteList) {
            addSprite(sprite);
        }
    }

    /**
     * the function will notify all the Sprites objects in the collection that
     * time has passed.
     *
     * @throws Exception if one of Sprites that has Point object in it, will
     *                   have negative values in one of the points.
     */
    public void notifyAllTimePassed() throws Exception {
        for (Sprite s: this.spriteElements) {
            s.timePassed();
        }
    }

    /**
     * the function will draw all the Sprites objects in the collection on the
     * input surface.
     *
     * @param surface the surface that the Sprites will be drawn on.
     * @throws Exception if one of Sprites that has Point object in it, will
     *                   have negative values in one of the points.
     */
    public void drawAllOn(DrawSurface surface) throws Exception {
        for (Sprite s: this.spriteElements) {
            s.drawOn(surface);
        }
    }

    /**
     * the function will return the i-th Sprite object in the collection.
     *
     * @param index index of the wanted Sprite object.
     * @return the i-th Sprite in the collection.
     */
    public Sprite getSprite(int index) {
        return this.spriteElements.get(index);
    }
}