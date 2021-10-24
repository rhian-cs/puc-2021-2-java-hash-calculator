import javax.swing.SwingUtilities;

import gui.MainWindow;
import hash_calculators.DirectoryMD5HashCalculator;
import hash_calculators.FileHashCalculator;

public class MainDebug {
  public static void main(String args[]) throws Exception {
    System.out.println("");
    long startTime = System.nanoTime();

    try {
      // fileExample();
      // directoryExample();
      guiExample();
    } catch (Exception e) {
      e.printStackTrace();
    }

    long stopTime = System.nanoTime();
    double seconds = (stopTime - startTime) * 0.000000001;

    System.out.println("\nExecution finished after " + seconds + " seconds.");
  }

  private static void fileExample() throws Exception {
    System.out.println(FileHashCalculator.getHash("tmp/bigfile", "MD5"));
  }

  private static void directoryExample() throws Exception {
    new DirectoryMD5HashCalculator("tmp/directory/").calculateAndOutputHashes();
  }

  private static void guiExample() throws Exception {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new MainWindow();
      }
    });
  }
}
