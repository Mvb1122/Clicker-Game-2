package modules;
import modules.*;

public class CounterThread extends Thread {
  // Make a counter to hold the number of automatic clicks.
  public final Counter autoClicks = new Counter(0);
  int clicksPerTick = 1;
  boolean interrupt = false;
  public boolean active = false;

  public void run() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      // Do nothing.
    }
    this.active = true;
    this.startLoop();
  }

  public void startLoop() {
    System.out.print("Started.");
    for (int i = 0; i < 10; i += 0) {
      try {
        Thread.sleep(1000);
        i--;
        autoClicks.increaseBy(clicksPerTick);
      } catch (InterruptedException e) {
        autoClicks.increaseBy(clicksPerTick);
        break;
      }
      System.out.println("\nLooped " + autoClicks.getValue());
    }
  }

  public void interrupt() {
    this.interrupt = true;
    this.active = false;
    System.out.println("Interrupted.");
  }

  public int getValue() {
    return autoClicks.getValue();
  }

  public void increaseByOne() {
    autoClicks.increaseBy(1);
  }

  public void increaseCPTBy(int amount) {
    clicksPerTick += amount;
    
    if (clicksPerTick == 0) {
      clicksPerTick = 1;
    }
    System.out.println("CPT: " + clicksPerTick);
  }

  public int getCPT() {
    return clicksPerTick;
  }

  public static void main(String[] args) {
    (new Thread(new CounterThread())).start();
  }
}