
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
    DirectoryMD5HashCalculator.calculateAndOutputHashes("tmp/directory/");
  }

  private static void guiExample() throws Exception {
    new MainWindow();
  }
}
