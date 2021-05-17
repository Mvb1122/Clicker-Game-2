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

    b.setBounds(80, 100, 250, 40);
    c.setBounds(80, 225, 250, 40);


    f.add(b);
    f.add(c);
    f.setSize(400, 500);
    f.setLayout(null);
    System.out.println("UI Drawn.");

    CounterThread ct = new CounterThread();
    ct.run();

    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String value = "" + clickCounter.getValue() + "";
        // System.out.println(clickCounter.getValue());
        if (clickCounter.getValue() < 25) {
          b.setBounds(80, 100, 250, (40 + clickCounter.getValue() * 3));
        }

        b.setText(value);
        clickCounter.increase();
        
        if (clickCounter.getValue() > 26) {
          String value2 = "" + ct.getValue() + "";

          c.setText(value2);

          ct.startLoop();
        }
        // System.out.println(ct.getValue());
      }
    });
  
    c.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ct.startLoop();
        c.setText("" + ct.getValue() + "");
      }
    });
  
  }
}