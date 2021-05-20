import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import modules.*;

public class Main extends Thread {

  public static void main(String[] args) {
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

    clickerButton.setBounds(80, 100, 250, 40);
    updateButton.setBounds(80, 220, 250, 40);
    increaseButton.setBounds(80, 265, 125, 40);
    decreaseButton.setBounds(210, 265, 120, 40);
    CPTDisplay.setBounds(80, 310, 250, 40);


    main.add(clickerButton);
    main.add(updateButton);
    main.add(increaseButton);
    main.add(decreaseButton);
    main.add(CPTDisplay);

    updateButton.setVisible(false);
    increaseButton.setVisible(false);
    decreaseButton.setVisible(false);
    CPTDisplay.setVisible(false);

    main.setSize(400, 500);
    main.setLayout(null);
    System.out.println("UI Drawn.");

    CounterRegister ctr = new CounterRegister();
    ctr.setActive(false);
    // ct.run();

// Create CounterThread.
    SwingWorker<Boolean, Integer> ct = new SwingWorker<Boolean, Integer>() {
      private Object Exception;

      @Override
      protected Boolean doInBackground() throws Exception {
        System.out.println("Counter thread active.");
        Thread.sleep(1000);
        // System.out.println("If you see this second, that means that the thread is working.");
        // Integer i = 0;
        // ctr.setActive(true);
        do {
          CPTDisplay.setText("" + ctr.getCPT() + "");
          if (ctr.getActivity()) {
            Thread.sleep(1000);
            Integer i = ctr.getValue();
            // System.out.println("Finished CT?");
            // Yes, I am busy-waiting here.
            // No, you're not allowed to judge me.
            i += ctr.getCPT();
            clickerButton.setText("" + i);
            // System.out.println(i);
            ctr.setValue(i);
            // setProgress(i);
          }

        } while (!isCancelled());
        // System.out.println("Finished CT?");
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

        k.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
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
          }
        });
      }
    }

    ct.execute();
    // System.out.println("If you see this first, that means the thread is working.");

    clickerButton.addActionListener(e -> {
      int numClicks = ctr.getValue();
      // System.out.println(numClicks);
      String value = "" + numClicks + "";
      clickerButton.setText(value);
      ctr.setValue(numClicks + 1);

      if (numClicks < 40) {
        clickerButton.setBounds(80, 100, 250, (40 + ctr.getValue() * 3));
        ctr.setActive(true);
      }

    });
  
    updateButton.addActionListener(e -> {
      // ctd.increaseCPTBy(0);
      ctr.interrupt();
      clickerButton.setText("" + ct.getProgress() + "");
    });

    increaseButton.addActionListener(e -> {
      ctr.increaseCPTBy(1);
      clickerButton.setText("" + ctr.getValue() + "");
      // CPTDisplay.setText("" + ctr.getCPT() + "");
    });

    decreaseButton.addActionListener(e -> {
      ctr.increaseCPTBy(-1);
      clickerButton.setText("" + ct.getProgress() + "");
      // CPTDisplay.setText("" + ctr.getCPT() + "");
    });

    //noinspection InfiniteLoopStatement
    do {
      clickerButton.setText("" + ctr.getValue() + "");
      if (ctr.getValue() > 40) {
        // updateButton.setVisible(true);
        increaseButton.setVisible(true);
        decreaseButton.setVisible(true);
        CPTDisplay.setVisible(true);
        // ctr.increaseByOne();
        purchaseWindow.setVisible(true);
      }
    } while (true);
  }
}