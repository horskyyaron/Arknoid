package execution;

public class Counter {

    //field.
    private int value;

    public Counter() {
        this.value = 0;
    }

    // add number to current count.
    void increase(int number) {
        this.value = this.value + number;
    };
    // subtract number from current count.
    void decrease(int number) {
        this.value = this.value - number;
    }
    // get current count.
    int getValue() {
        return this.value;
    }
}