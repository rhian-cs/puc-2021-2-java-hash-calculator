package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DefaultWindow extends JFrame {
  private static final int WINDOW_WIDTH = 480;
  private static final int WINDOW_HEIGHT = 480;

  public DefaultWindow(String title, JPanel contentPane) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setContentPane(contentPane);
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
