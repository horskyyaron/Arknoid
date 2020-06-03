package execution.listeners;

public class Counter {

    //field.
    private int value;

    public Counter() {
        this.value = 0;
    }

    // add number to current count.
    public void increase(int number) {
        this.value = this.value + number;
    };
    // subtract number from current count.
    public void decrease(int number) {
        this.value = this.value - number;
    }
    // get current count.
    public int getValue() {
        return this.value;
    }
}