package modules;
import java.util.concurrent.atomic.*;

public class CounterRegister {
  // Make a counter to hold the number of automatic clicks.
  private AtomicInteger autoClicks = new AtomicInteger(1);
  public int clicksPerTick = 1;
  public boolean interrupt = false;
  boolean active = false;

  public void run() {
    this.active = true;
  }

  public boolean getActivity () {
    return this.active;
  }

  public void setActive(boolean val) {
    this.active = val;
  }

  public void interrupt() {
    this.interrupt = true;
    this.active = false;
    // System.out.println("Interrupted.");
  }

  public int getValue() {
    return autoClicks.get();
  }

  public void increaseByOne() {
    autoClicks.incrementAndGet();
  }

  public void increaseCPTBy(int amount) {
    clicksPerTick += amount;
    
    if (clicksPerTick == 0) {
      clicksPerTick = 1;
    }
    // System.out.println("CPT: " + clicksPerTick);
  }

  public int getCPT() {
    return clicksPerTick;
  }

  public void setValue(int value) {
    autoClicks.set(value);
  }
}