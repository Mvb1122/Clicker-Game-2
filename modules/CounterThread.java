package modules;
import modules.*;

public class CounterThread implements Runnable {
  // Make a counter to hold the number of automatic clicks.
  Counter autoClicks = new Counter(0);
  int clicksPerTick = 1;
  boolean loop = false;

    public void run() {
      System.out.println("Thread 2 Active!");

      do {
        // Try to sleep for 5 seconds, wake if interrupted.
        try {
          Thread.sleep(50000);
        } catch (InterruptedException e) {
          System.out.println("Thread 2, Awoken.");
        }
        if (loop) {
          // Once every tick, increase the counter by the value of clicksPerTick.
          for (int j = 0; j < clicksPerTick; j++) {
            autoClicks.increase();
          }
        } else {
          autoClicks.increaseBy(clicksPerTick);
        }

      } while (true);
    }

    public int getValue() {
      return autoClicks.getValue();
    }

    public void startLoop() {
      System.out.println("Loop started? " + autoClicks.getValue());
      this.loop = true;
    }

    public void increaseCPTBy(int ammount) {
      this.clicksPerTick += ammount;
    }

    public static void main(String args[]) {
      (new Thread(new CounterThread())).start();
    }
}