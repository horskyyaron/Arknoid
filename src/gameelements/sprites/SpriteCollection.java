//ID: 204351670

package gameelements.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
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
        // Make a copy of the Sprites before iterating over them.
        List<Sprite> elements = new LinkedList<>(this.spriteElements);
        // Notify all Sprites that time passed.
        for (Sprite s: elements) {
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
        // Make a copy of the Sprites before iterating over them.
        List<Sprite> elements = new LinkedList<>(this.spriteElements);
        //Draw all Sprites.
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

    /**
     * the function will return sprite list.
     *
     * @return the sprite list (the sprite collection)
     */
    public List<Sprite> getSpriteElements() {
        return spriteElements;
    }
}