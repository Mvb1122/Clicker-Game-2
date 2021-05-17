import javax.swing.*;
import java.awt.event.*;
import modules.*;

public class Main {
  public static void main(String[] args) {
    JFrame f = new JFrame();
    Counter clickCounter = new Counter(1);

    JButton b = new JButton("Click me!");

    b.setBounds(80, 100, 250, 40);

    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String value = "" + clickCounter.getValue() + "";
        // System.out.println(clickCounter.getValue());
        b.setBounds(80, 100, 250, (40 + clickCounter.getValue()));
        b.setText(value);
        clickCounter.increase();
      }
    });

    f.add(b);

    f.setSize(400, 500);
    f.setLayout(null);
    f.setVisible(true);
  }
}