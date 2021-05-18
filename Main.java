import javax.swing.*;
import java.awt.event.*;
import modules.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Active");
    
    JFrame f = new JFrame();
    f.setVisible(true);
    f.setTitle("Clicker Game: 2");

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton clickerButton = new JButton("Click me!");
    JButton updateButton = new JButton("Update");
    JButton increaseButton = new JButton("Increase");
    JButton decreaseButton = new JButton("Decrease");
    JButton CPTDisplay = new JButton("1");

    clickerButton.setBounds(80, 100, 250, 40);
    updateButton.setBounds(80, 225, 250, 40);
    increaseButton.setBounds(80, 265, 125, 40);
    decreaseButton.setBounds(210, 265, 120, 40);
    CPTDisplay.setBounds(80, 310, 250, 40);


    f.add(clickerButton);
    f.add(updateButton);
    f.add(increaseButton);
    f.add(decreaseButton);
    f.add(CPTDisplay);

    updateButton.setVisible(true);
    increaseButton.setVisible(false);
    decreaseButton.setVisible(false);
    CPTDisplay.setVisible(false);

    f.setSize(400, 500);
    f.setLayout(null);
    System.out.println("UI Drawn.");

    CounterThread ct = new CounterThread();
    // ct.run();

    clickerButton.addActionListener(e -> {
      ct.interrupt(); // TODO: Find a way to interrupt counter loop.
      int numClicks = ct.autoClicks.getValue();
      String value = "" + numClicks + "";
      // System.out.println(value);
      // System.out.println(clickCounter.getValue());

      if (numClicks < 25) {
        clickerButton.setBounds(80, 100, 250, (40 + ct.getValue() * 3));
      }

      clickerButton.setText(value);

      if (numClicks == 20) {
        updateButton.setVisible(true);
        increaseButton.setVisible(true);
        decreaseButton.setVisible(true);
        CPTDisplay.setVisible(true);
        ct.setPriority(Thread.MIN_PRIORITY);
        ct.run();
      } else {
        ct.increaseByOne();
      }
      /*
       ct.increaseByOne();
       System.out.println(ct.getValue());
      if (numClicks >= 20 && ct.active == false) {
        // ct.run();
      }
      */
    });
  
    updateButton.addActionListener(e -> {
      // ct.increase();
      ct.interrupt();;
      clickerButton.setText("" + ct.getValue() + "");
    });

    increaseButton.addActionListener(e -> {
      ct.increaseCPTBy(1);
      clickerButton.setText("" + ct.getValue() + "");
      CPTDisplay.setText("" + ct.getCPT() + "");
    });

    decreaseButton.addActionListener(e -> {
      ct.increaseCPTBy(-1);
      clickerButton.setText("" + ct.getValue() + "");
      CPTDisplay.setText("" + ct.getCPT() + "");
    });

    //noinspection InfiniteLoopStatement
    do {
      clickerButton.setText("" + ct.getValue() + "");
    } while (true);
  }
}