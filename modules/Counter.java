package modules;

public class Counter {
  // Make the counter value.
  int counter;
  int startingValue;

  // Create the counter when the constructor is called.
  public Counter(int startingValue) {
    this.counter = startingValue;
    this.startingValue = startingValue;
  }

  // Increase the counter's value by one.
  public void increase() {
    counter ++;
  }

  // Increase the counter's value by a provided ammount
  public void increaseBy(int ammount) {
    this.counter += ammount;
  }

  // Return the value of the counter.
  public int getValue() {
    return counter;
  }
  
  public void setValue(int value) {
    counter = value;
  }
}