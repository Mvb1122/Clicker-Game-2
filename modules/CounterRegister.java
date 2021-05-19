package modules;
import java.util.concurrent.atomic.*;

public class CounterRegister {
  // Make a counter to hold the number of automatic clicks.
  private AtomicInteger autoClicks = new AtomicInteger(1);
  public int clicksPerTick = 1;
  public boolean interrupt = false;
  public boolean active = false;

  public void run() {
    this.active = true;
  }

  public void startLoop() {
    /* TODO: Write code to check if ct is running. Return true if it is.
    System.out.print("Started.");
    for (int i = 0; i < 10; i ++) {
      try {
        Thread.sleep(1000);
        // i--;
        autoClicks.addAndGet(clicksPerTick);
      } catch (InterruptedException e) {
        autoClicks.addAndGet(clicksPerTick);
        System.out.println(e);
        break;
      }

      System.out.println("\nLooped " + autoClicks.get());
    }

     */
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
}