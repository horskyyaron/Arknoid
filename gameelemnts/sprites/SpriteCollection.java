package gameelemnts.sprites;//ID: 204351670

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * gameelemnts.sprites.SpriteCollection class will create an object which will hold gameelemnts.sprites.Sprite objects,
 * will supports methods of adding and drawing Sprites.
 * Will have method that 'tells' a sprite object that time has passed.
 */
public class SpriteCollection {
    //fields.
    private List<Sprite> spriteElements;

    //constructors.
    /**
     * constructor of the 'gameelemnts.sprites.SpriteCollection' object.
     *
     * @param spriteElements a list of Sprites.
     */
    public SpriteCollection(List<Sprite> spriteElements) {
        this.spriteElements = spriteElements;
    }
    /**
     * constructor of the 'gameelemnts.sprites.SpriteCollection' object.
     */
    public SpriteCollection() {
        this.spriteElements = new ArrayList<>();
    }

    /**
     * the function will add an input gameelemnts.sprites.Sprite into the gameelemnts.sprites.SpriteCollection.
     *
     * @param s a gameelemnts.sprites.Sprite object.
     */
    public void addSprite(Sprite s) {
        this.spriteElements.add(s);
    }

    /**
     * the function will add an input list of Sprites into the gameelemnts.sprites.SpriteCollection.
     *
     * @param spriteList a gameelemnts.sprites.Sprite object.
     */
    public void addSpritesList(List<Sprite> spriteList) {
        for (Sprite sprite: spriteList) {
            addSprite(sprite);
        }
    }

    /**
     * the function will notify all the Sprites objects in the collection that
     * time has passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite s: this.spriteElements) {
            s.timePassed();
        }
    }

    /**
     * the function will draw all the Sprites objects in the collection on the
     * input surface.
     *
     * @param d the surface that the Sprites will be drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: this.spriteElements) {
            s.drawOn(d);
        }
    }

    /**
     * the function will return the i-th gameelemnts.sprites.Sprite object in the collection.
     *
     * @param index index of the wanted gameelemnts.sprites.Sprite object.
     * @return the i-th gameelemnts.sprites.Sprite in the collection.
     */
    public Sprite getSprite(int index) {
        return this.spriteElements.get(index);
    }

    public List<Sprite> getSpriteElements() {
        return spriteElements;
    }
}