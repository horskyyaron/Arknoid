//ID: 204351670

package execution;

/**
 * A basic counter. support methods of increasing and decreasing the counter value, and returning the value.
 */
public class Counter {

    //fields.
    private int value;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Increase the counter value.
     *
     * @param number current counter value.
     */
    public void increase(int number) {
        this.value = this.value + number;
    };

    /**
     * Decrease the counter value.
     *
     * @param number current counter value.
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * returns the counter value.
     *
     * @return counter value.
     */
    public int getValue() {
        return this.value;
    }
}