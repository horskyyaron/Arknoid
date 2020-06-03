//ID: 204351670

package gameelemnts;

/**
 * The interface Hit notifier. will support methods of adding and removing a listener from a hit events.
 */
public interface HitNotifier {

    /**
     * Add hit listener to hit event.
     *
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener from hit event.
     *
     * @param hl the hit listener
     */
// Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
