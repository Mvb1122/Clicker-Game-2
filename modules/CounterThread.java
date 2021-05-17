package modules;
import modules.*;

public class CounterThread implements Runnable {
  // Make a counter to hold the number of automatic clicks.
  Counter autoClicks = new Counter(0);
  int clicksPerTick = 1;
  boolean loop = true;

  public void run() {
    for (int i = 0; i < clicksPerTick; i++) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // Wake up.
      }
      this.increase();
    }
  }

  public void increase() {
    for (int j = 0; j < clicksPerTick; j++) {
      autoClicks.increase();
    }
  }

  public int getValue() {
    return autoClicks.getValue();
  }

  public void startLoop() {
    System.out.println("Thread 2 Active!");
    System.out.println("Loop started?\nValue of autoClicks: " + autoClicks.getValue());
    loop = true;
    // do {
      try {
        run(); 
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println("Loop broken.");
        // break;
      }
    // } while (true);
    System.out.println("Loop broken.");
  }

  public void increaseCPTBy(int ammount) {
    clicksPerTick += ammount; 
    
    if (clicksPerTick == 0) {
      clicksPerTick = 1;
    }
    System.out.println("CPT: " + clicksPerTick);
  }

  public int getCPT() {
    return clicksPerTick;
  }

  public static void main(String args[]) {
    (new Thread(new CounterThread())).start();
  }
}