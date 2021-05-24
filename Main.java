import modules.CounterRegister;
import modules.Item;
import javax.swing.*;
import java.awt.*;

public class Main extends Thread {

  public static void main(String[] args) {
    int CTStartAmount = 55;
    System.out.println("Active");
    // System.out.println("Is EDT thread?: " + SwingUtilities.isEventDispatchThread());
    
    JFrame main = new JFrame();
    main.setVisible(true);
    main.setTitle("Clicker Game 2");

    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton clickerButton = new JButton("Click me!");
    JButton updateButton = new JButton("Update");
    JButton increaseButton = new JButton("Increase");
    JButton decreaseButton = new JButton("Decrease");
    JButton CPTDisplay = new JButton("1");
    JButton hackerButton = new JButton("Activate hacker mode");

    clickerButton.setBounds(80, 100, 250, 40);
    updateButton.setBounds(80, 220, 250, 40);
    increaseButton.setBounds(80, 355, 125, 40);
    decreaseButton.setBounds(210, 355, 120, 40);
    CPTDisplay.setBounds(80, 310, 250, 40);
    hackerButton.setBounds(135, 425, 250, 40);


    main.add(clickerButton);
    main.add(updateButton);
    main.add(increaseButton);
    main.add(decreaseButton);
    main.add(CPTDisplay);
    main.add(hackerButton);

    updateButton.setVisible(false);
    increaseButton.setVisible(false);
    decreaseButton.setVisible(false);
    CPTDisplay.setVisible(false);
    hackerButton.setVisible(true);

    main.setSize(400, 500);
    main.setLayout(null);
    System.out.println("UI Drawn.");

    CounterRegister ctr = new CounterRegister();
    ctr.setActive(false);
    // ct.run();

// Create CounterThread.
    SwingWorker<Boolean, Integer> ct = new SwingWorker<Boolean, Integer>() {

      @Override
      protected Boolean doInBackground() throws Exception {
        System.out.println("Counter thread active.");
        // Thread.sleep(1000);
        do {
          //noinspection BusyWait
          Thread.sleep(1000);
          CPTDisplay.setText("" + ctr.getCPT() + "");
          if (ctr.getActivity()) {
            Integer i = ctr.getValue();
            i += ctr.getCPT();
            clickerButton.setText(ctr.getName());
            ctr.setValue(i);
          }

        } while (!isCancelled());
        return true;
      }
    };

// Create purchase logic.
    JFrame purchaseWindow = new JFrame();
    if (true) { // This just lets me hide the lines when I'm not working on this.
      purchaseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      purchaseWindow.setVisible(false);
      purchaseWindow.setTitle("Capitalism.");
      purchaseWindow.setSize(400, 500);
      purchaseWindow.setLayout(new GridLayout(0,1));
      purchaseWindow.setBounds(400, 0, 400, 500);

      // Create buttons
      for (int i = 1; i < 10; i++) {
        Item a = new Item( i * 40, "increase by " + i + " Cost: " + i * 40, i);
        JButton k = new JButton(a.name);
        purchaseWindow.add(k);

        k.addActionListener(e -> {
          // System.out.println("" + k.getText());
          if (ctr.getValue() > a.price) {
            ctr.increaseCPTBy(a.effect);
            ctr.setValue(ctr.getValue() - a.price);
          } else {
            SwingWorker timer = new SwingWorker() {
              @Override
              protected Object doInBackground() throws Exception {
                if (ctr.getValue() < a.price) {
                  k.setText("You can't afford this.");
                  Thread.sleep(5000);
                  k.setText(a.name);
                }
                return null;
              }
            };
            timer.execute();
          }
          // ctr.increaseCPTBy(a.effect);
        });
      }
    }

    ct.execute();
    // System.out.println("If you see this first, that means the thread is working.");

    clickerButton.addActionListener(e -> {
      int numClicks = ctr.getValue();
      ctr.setValue(numClicks + 1);

      if (numClicks >= CTStartAmount) {
        ctr.setActive(true);
      }
    });
  
    updateButton.addActionListener(e -> {
      // ctd.increaseCPTBy(0);
      ctr.interrupt();
      clickerButton.setText(ctr.getName());
    });

    increaseButton.addActionListener(e -> {
      ctr.increaseCPTBy(10);
      clickerButton.setText(ctr.getName());
      CPTDisplay.setText("" + ctr.getCPT() + "");
    });

    decreaseButton.addActionListener(e -> {
      ctr.increaseCPTBy(-10);
      clickerButton.setText(ctr.getName());
      CPTDisplay.setText("" + ctr.getCPT() + "");
    });

    hackerButton.addActionListener(e -> {
      // updateButton.setVisible(true);
      increaseButton.setVisible(true);
      decreaseButton.setVisible(true);
      CPTDisplay.setVisible(true);
      hackerButton.setVisible(false);
    });

    //noinspection InfiniteLoopStatement
    do {
      clickerButton.setText(ctr.getName());
      if (ctr.getValue() > CTStartAmount) {
        CPTDisplay.setVisible(true);
        // ctr.increaseByOne();
        purchaseWindow.setVisible(true);
      }

      if (ctr.getValue() < CTStartAmount) {
        clickerButton.setBounds(80, 100, 250, (40 + ctr.getValue() * 3));
      }
    } while (true);
  }
}