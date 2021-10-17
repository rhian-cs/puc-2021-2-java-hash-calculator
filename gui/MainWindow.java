package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame {
  private static final String TITLE = "Hash Calculator";
  private static final int WINDOW_WIDTH = 480;
  private static final int WINDOW_HEIGHT = 240;

  public MainWindow() {
    super(TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println("An error occurred when setting the native look and feel.");
      e.printStackTrace();
    }

    setContentPane(new MainPanel());
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
