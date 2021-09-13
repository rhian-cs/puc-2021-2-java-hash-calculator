package gui;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
  private static final String TITLE = "Hash Calculator";
  private static final int WINDOW_WIDTH = 480;
  private static final int WINDOW_HEIGHT = 240;

  public MainWindow() {
    super(TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setContentPane(new MainPanel());
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
