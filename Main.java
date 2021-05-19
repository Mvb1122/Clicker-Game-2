import javax.swing.*;

import modules.*;

public class Main extends Thread {

  public static void main(String[] args) {
    System.out.println("Active");
    System.out.println("Is EDT thread?: " + SwingUtilities.isEventDispatchThread());
    
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

    updateButton.setVisible(false);
    increaseButton.setVisible(false);
    decreaseButton.setVisible(false);
    CPTDisplay.setVisible(false);

    f.setSize(400, 500);
    f.setLayout(null);
    System.out.println("UI Drawn.");

    CounterRegister ctr = new CounterRegister();
    // ct.run();


    SwingWorker<Boolean, Integer> ct = new SwingWorker<Boolean, Integer>() {
      private Object Exception;

      @Override
      protected Boolean doInBackground() throws Exception {
        System.out.println("Thread active.");
        Thread.sleep(1000);
        System.out.println("If you see this second, that means that the thread is working.");
        Integer i = 0;
        do {
          // System.out.println("Finished CT?");
          // Yes, I am busy-waiting here.
          // No, you're not allowed to judge me.
          i++;
          Thread.sleep(1000);
          clickerButton.setText("" + i);
          System.out.println(i);
          publish(i);
          setProgress(i);
        } while (!isCancelled());
        // System.out.println("Finished CT?");
        return true;
      }
    };
    System.out.println("If you see this first, that means the thread is working.");

    clickerButton.addActionListener(e -> {
      // ctr.interrupt(); // TODO: Find a way to interrupt counter loop.
      // TODO: Remove the above comment, since we moved that to a separate thread.
      int numClicks = ct.getProgress();
      // System.out.println(numClicks);
      String value = "" + numClicks + "";
      /*
       System.out.println(value);
       System.out.println(clickCounter.getValue());
      */
      if (numClicks == 0) {
        ct.execute();
      }

      if (numClicks < 25) {
        clickerButton.setBounds(80, 100, 250, (40 + ct.getProgress() * 3));
      }

      clickerButton.setText(value);

      // TODO: Make this do something correctly.
      if (numClicks == 20) {
        updateButton.setVisible(true);
        increaseButton.setVisible(true);
        decreaseButton.setVisible(true);
        CPTDisplay.setVisible(true);
        ctr.increaseByOne();
      } else if (numClicks == 21) {
        // SwingUtilities.invokeLater(() -> ct.run());

      } else {
        ctr.increaseByOne();
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
      // ctd.increaseCPTBy(0);
      ctr.interrupt();
      clickerButton.setText("" + ct.getProgress() + "");
    });

    increaseButton.addActionListener(e -> {
      ctr.increaseCPTBy(1);
      clickerButton.setText("" + ct.getProgress() + "");
      CPTDisplay.setText("" + ct.getProgress() + "");
    });

    decreaseButton.addActionListener(e -> {
      ctr.increaseCPTBy(-1);
      clickerButton.setText("" + ct.getProgress() + "");
      CPTDisplay.setText("" + ctr.getCPT() + "");
    });

    //noinspection InfiniteLoopStatement
    do {
      // ct.interrupt();
      clickerButton.setText("" + ct.getProgress() + "");
    } while (true);
  }
}