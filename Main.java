import javax.swing.*;
import java.awt.event.*;
import modules.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Active");
    
    JFrame f = new JFrame();
    f.setVisible(true);
    
    Counter clickCounter = new Counter(1);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton b = new JButton("Click me!");
    JButton c = new JButton("Hold on.");
    JButton increaseButton = new JButton("Increase");
    JButton decreaseButton = new JButton("Decrease");
    JButton CPTDisplay = new JButton("1");

    b.setBounds(80, 100, 250, 40);
    c.setBounds(80, 225, 250, 40);
    increaseButton.setBounds(80, 265, 125, 40);
    decreaseButton.setBounds(210, 265, 120, 40);
    CPTDisplay.setBounds(80, 310, 250, 40);


    f.add(b);
    f.add(c);
    f.add(increaseButton);
    f.add(decreaseButton);
    f.add(CPTDisplay);

    c.setVisible(false);
    increaseButton.setVisible(false);
    decreaseButton.setVisible(false);
    CPTDisplay.setVisible(false);

    f.setSize(400, 500);
    f.setLayout(null);
    System.out.println("UI Drawn.");

    CounterThread ct = new CounterThread();

    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int numClicks = clickCounter.getValue();
        String value = "" + clickCounter.getValue() + "";
        // System.out.println(clickCounter.getValue());
        if (numClicks < 25) {
          b.setBounds(80, 100, 250, (40 + clickCounter.getValue() * 3));
        }

        b.setText(value);
        clickCounter.increase();
        
        c.setText("" + ct.getValue() + "");

        if (numClicks >= 25) {
          ct.run();
          clickCounter.increaseBy(ct.getValue());
          // c.setVisible(true);
          increaseButton.setVisible(true);
          decreaseButton.setVisible(true);
          CPTDisplay.setVisible(true);
        }
        // System.out.println(ct.getValue());
      }
    });
  
    c.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // ct.startLoop();
        c.setText("" + ct.getValue() + "");
      }
    });

    increaseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ct.increaseCPTBy(1);
        c.setText("" + ct.getValue() + "");
        CPTDisplay.setText("" + ct.getCPT() + "");
      }
    });

    decreaseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ct.increaseCPTBy(-1);
        c.setText("" + ct.getValue() + "");
        CPTDisplay.setText("" + ct.getCPT() + "");
      }
    });
  }
}